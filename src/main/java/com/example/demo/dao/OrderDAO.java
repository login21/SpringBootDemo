package com.example.demo.dao;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.example.demo.controller.OrderController;
import com.example.demo.model.Order;
import com.example.demo.model.Orders;



@Repository
public class OrderDAO {
	
	private static Orders list = new Orders();
	private static final Logger logger = LogManager.getLogger(OrderController.class);
	
	private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	
    static
    {
        list.getOrderList().add(new Order(1, "Requested", "2020-01-16 00:00:00", " ", 3, "Reuested for new order", 1,3));
        list.getOrderList().add(new Order(2, "InProgress", "2020-01-16 00:00:00", " ", 3, "Reuested for new order", 2, 3));
        list.getOrderList().add(new Order(3, "Requested", "2020-01-16 00:00:00", " ", 6, "Reuested for new order", 4, 6));
    }
     
    public Orders getAllOrders() 
    {
        return list;
    }
     
    public String addOrder(Order order) {
    	try
    	{
    		list.getOrderList().add(order);
    		return "success";
    	}catch(Exception e)
    	{
    		logger.error("OrderDAO::addOrder(): Error "+e.getMessage());
    		return "error"+ e.getMessage();
    	}
        
    }
    
    public Order processOrder(Order order)
    {
    	try {
    		if(order.getStatus() == "Requested")
    		{
    			LocalDateTime dateStart = LocalDateTime.now();
    			order.setStartDate((sdf.format(dateStart)));
    			LocalDateTime dateStop = dateStart.plusHours(order.getDuration());
    			order.setEndDate((sdf.format(dateStop)));
    			order.setStatus("InProgress");
    			
    			//Update order based on order_id in db
    			//db query to update the order table with order.
    		}
    		return order;
    		
    	}catch(Exception e)
    	{
    		logger.error("OrderDAO::processOrder(): Error" + e.getMessage());
    		order.setStatus("Requested");
    		order.setComment("Failed to process order.");
    		return order;
    	}
    }
    
    public List<Order> getAllOrdersByFarmerID(Long farmerId) 
    {
    	System.out.println("hello..."+farmerId);
    	List<Order> list1 = list.getOrderList();
    	List<Order> returnList = new ArrayList<>();
    	
    	for (Order order : list1) {
    		if(order.getFarmerId() == farmerId.intValue() )
    		{
    			returnList.add(order);
    		}
    	    
    	}
        return  returnList;
    }
    
    
    public List<Order> getOrdersBetweenDates(LocalDateTime startDate, LocalDateTime endDate, int farmerId , int farmId)
    {
    	List<Order> returnList = new ArrayList<>();
    	//db query to return list of orders.
    	
    	//For now adding dummy data.
    	if(farmId == 3)
    		
    	{
    		returnList.add(1, new Order(1, "Requested", "2020-01-16 00:00:00", " ", 3, "Reuested for new order", 1,3));
    	}
    	
    	return returnList;
    }
    
    
    public Order findOrderById(int orderId) 
    {
    	
    	System.out.println("hello..."+orderId);
    	List<Order> list1 = list.getOrderList();
    	
    	for (Order order : list1) {
    		if(order.getOrderId() == orderId)
    		{
    			return order;
    		}
    	    
    	}
    	
    	return null;
        
    }
    
    public String cancelOrder(Order order)
    {
    	//update into db with update query and based on result return success or error.
    	return "success";
    }

}
