package com.example.demo.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.controller.OrderController;
import com.example.demo.dao.OrderDAO;
import com.example.demo.model.Order;
import com.example.demo.model.Orders;

@Service
public class OrderServiceImpl implements OrderService {
	
	 @Autowired
	 private OrderDAO orderDao;
	
	private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private static final Logger logger = LogManager.getLogger(OrderController.class);
    
    
    @Override
	public String placeOrder(Order order) {
    	
    	String result = "";
    	//first check all orders for same farmid and farmerid which falls between order start date and end date.
    	LocalDateTime orderStartDate = LocalDateTime.parse((order.getStartDate()), dtf);
    	LocalDateTime orderEndDate = orderStartDate.plusHours(order.getDuration());
		order.setEndDate((dtf.format(orderEndDate)));
		
		//get all orders from db for between start and end date and for same farmer id and farm id.
		List<Order> placedorders = orderDao.getOrdersBetweenDates(orderStartDate,orderEndDate, order.getFarmerId() , order.getFarmId() );
    	
		if(placedorders.size() > 0)
		{
			result = "OrderExist";
			
		}else {
			
			Integer id = getAllOrders().getOrderList().size() + 1;
	        order.setOrderId(id);
	        order.setStatus("Requested");
	        //adding new order.
	        result = orderDao.addOrder(order);
		}
    	 
		return result;
	}
    
    
    @Override
	public String processOrder(Order order) {
    	Order processedOrder = orderDao.processOrder(order);
    	if(processedOrder.getStatus() == "InProgress")
    	{
    		return "success";
    	}else
    	{
    		return "error during request processing";
    	}
		
	}

	

	@Override
	public void cancelOrder(Order order) {
		try
		{
			Order currentOrder =  orderDao.findOrderById(order.getOrderId());
	    	if (currentOrder == null) {
	            logger.info("This order does not exist in system");
	        }else
	        {
	        	if(order.getStatus() != "InProgress" || order.getStatus() != "Delivered")
	    		{
	    			 order.setStatus("Cancelled");
	    			 Date date = new Date();
	    			 order.setEndDate(sdf.format(date));
	    			 order.setComment("Cancelling order");
	    		}
	    		
	    		//update order status in db.
	    		String result = orderDao.cancelOrder(order);
	    		System.out.println("Order is cancelled" + result);
	        }  	
			
		
		}catch(Exception e)
		{
			logger.error("OrderServiceImpl::cancelOrder():error " +e);
		}
		
		
	}


	@Override
	public Orders getAllOrders() {
		// TODO Auto-generated method stub
		Orders list = orderDao.getAllOrders();
		return list;
	}


	@Override
	public List<Order> getAllOrdersByFarmerID(Long farmerId) {
		// TODO Auto-generated method stub
		List<Order> returnList = orderDao.getAllOrdersByFarmerID(farmerId);
		return returnList;
	}


	@Override
	public Order findOrderById(int orderId) {
		Order currentOrder = orderDao.findOrderById(orderId);
		return currentOrder;
	}

	

}
