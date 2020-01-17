package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Order;
import com.example.demo.model.Orders;

public interface OrderService {
	
	public String placeOrder(Order order);

	public void cancelOrder(Order order);
	
	public String processOrder(Order order);
	
    public Orders getAllOrders();
    
    public List<Order> getAllOrdersByFarmerID(Long farmerId);
    
    public Order findOrderById(int orderId);
	
}
