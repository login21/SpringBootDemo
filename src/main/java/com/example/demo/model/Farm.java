package com.example.demo.model;

public class Farm {
	private Integer farmId;
	private String address;
	private Integer farmerId;
	
	
	public Farm(Integer farmId, String address, Integer farmerId) {
		super();
		this.farmId = farmId;
		this.address = address;
		this.farmerId = farmerId;
	}


	public Integer getFarmId() {
		return farmId;
	}


	public void setFarmId(Integer farmId) {
		this.farmId = farmId;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public Integer getFarmerId() {
		return farmerId;
	}


	public void setFarmerId(Integer farmerId) {
		this.farmerId = farmerId;
	}


	@Override
	public String toString() {
		return "Farm [farmId=" + farmId + ", address=" + address + ", farmerId=" + farmerId + "]";
	}

	
	
}
