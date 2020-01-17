package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Order;
import com.example.demo.model.Orders;
import com.example.demo.service.OrderService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@RestController
@RequestMapping(path = "/orders")
public class OrderController {
	 
	 @Autowired
	 private OrderService orderService;
	 
	 
	 private static final Logger logger = LogManager.getLogger(OrderController.class);
	 
	    @PostMapping(path= "/placeOrder", consumes = "application/json", produces = "application/json")
	    public ResponseEntity<?> addOrder(@RequestBody Order order) 
	    {
	    	System.out.println("place order....");
	    	logger.info("Inside OrderController::addOrder(): "+order);
	         
	        String result = orderService.placeOrder(order);
	         
	        if (result == "success")
	        {
	        	return new ResponseEntity<Object>("Order Placed", HttpStatus.ACCEPTED);
	        }else if(result.contains("OrderExist"))
	        {
	        	return new ResponseEntity<Object>("Already order exist for this farm", HttpStatus.CONFLICT);
	        }else
	        {
	        	return new ResponseEntity<Object>("Error", HttpStatus.BAD_REQUEST);
	        }
	         
	       
	    }
	 
	    
	    @PostMapping(path= "/processOrder", consumes = "application/json", produces = "application/json")
	    public ResponseEntity<Object> processOrder(@RequestBody Order order) 
	    {
	    	logger.info("Inside OrderController::processOrder(): "+order);
	        String result = orderService.processOrder(order);
	        if(result == "success")
	        {
	        	return ResponseEntity.ok("Your order is placed");
	        }else
	        {
	        	return new ResponseEntity("Error", HttpStatus.BAD_REQUEST);
	        }
	        
	    }
	 

	    @GetMapping(path="/getAllOrders", produces = "application/json")
	    public Orders getOrders() 
	    {
	        return orderService.getAllOrders();
	    }
	 
	    
	    @GetMapping(path="/getOrdersByFarmerId/{farmerId}", produces = "application/json")
	    public ResponseEntity<?> getOrdersByFarmerId(@PathVariable Long farmerId) 
	    {
	    	System.out.println("***************" + farmerId);
	        
	        return new ResponseEntity<List<Order>>(orderService.getAllOrdersByFarmerID(farmerId), HttpStatus.OK);
	    }
	 
	    
	    @PutMapping(path= "/cancelOrder/{orderId}")
	    public ResponseEntity<?> cancelOrder(@RequestBody Order order, @PathVariable int orderId) {
	 
	    	orderService.cancelOrder(order);
	        return new ResponseEntity<Order>(order, HttpStatus.OK);
	    
	    }
	    
	 
}
