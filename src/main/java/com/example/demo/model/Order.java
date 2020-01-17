package com.example.demo.model;

import java.sql.Date;

public class Order {
	
	private Integer orderId;
    private String status;
    private String startDate;
    private String endDate;
    private Integer duration;
    private String comment;
    private Integer farmerId;
    private Integer farmId;
    
	public Order(Integer orderId, String status, String startDate, String endDate, Integer duration, String comment, Integer farmerId, Integer farmId) {
		super();
		this.orderId = orderId;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
		this.duration = duration;
		this.comment = comment;
		this.farmerId = farmerId;
		this.farmId = farmId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getFarmerId() {
		return farmerId;
	}

	public void setFarmerId(Integer farmerId) {
		this.farmerId = farmerId;
	}

	public Integer getFarmId() {
		return farmId;
	}

	public void setFarmId(Integer farmId) {
		this.farmId = farmId;
	}
    
	
    
}
