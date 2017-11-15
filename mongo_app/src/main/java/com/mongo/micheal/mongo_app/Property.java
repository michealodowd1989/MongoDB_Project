package com.mongo.micheal.mongo_app;

public class Property {
	private int id;
	private String houseType;
	private int numRoom;
	private double weeklyRent;
	private Address address;
	
	public Property(int id, String houseType, int numRoom, double weeklyRent, Address address) {
		super();
		this.id = id;
		this.houseType = houseType;
		this.numRoom = numRoom;
		this.weeklyRent = weeklyRent;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHouseType() {
		return houseType;
	}
	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
	public int getNumRoom() {
		return numRoom;
	}
	public void setNumRoom(int numRoom) {
		this.numRoom = numRoom;
	}
	public double getWeeklyRent() {
		return weeklyRent;
	}
	public void setWeeklyRent(double weeklyRent) {
		this.weeklyRent = weeklyRent;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "\nHouse Id: " + id + "\nHouseType: " + houseType + "\nNo.Room: " + numRoom + "\nWeekly Rent: " + weeklyRent + "\nAddress: "
				+ address.toString();
	}
	
	
	
}
