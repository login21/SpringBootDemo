package com.example.demo.model;

public class Farmer {
	
	private Integer farmerId;
    private String name;
    private String emailId;
    private String address;
    
	public Farmer(Integer farmerId, String name,String emailId, String address) {
		super();
		this.farmerId = farmerId;
		this.name = name;
		this.emailId = emailId;
		this.address = address;
	}

	public Integer getFarmerId() {
		return farmerId;
	}

	public void setFarmerId(Integer farmerId) {
		this.farmerId = farmerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
    
	
    

}
