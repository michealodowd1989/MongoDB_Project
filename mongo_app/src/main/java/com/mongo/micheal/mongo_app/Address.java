package com.mongo.micheal.mongo_app;

public class Address {
	
	private String line1;
	private String line2;
	private String town;
	private String county;
	private String country;
	private String postcode;
	
	
	
	public Address(String line1, String line2, String town, String county, String country, String postcode) {
		super();
		this.line1 = line1;
		this.line2 = line2;
		this.town = town;
		this.county = county;
		this.country = country;
		this.postcode = postcode;
	}
	
	public String getLine1() {
		return line1;
	}
	public void setLine1(String line1) {
		this.line1 = line1;
	}
	public String getLine2() {
		return line2;
	}
	public void setLine2(String line2) {
		this.line2 = line2;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	@Override
	public String toString() {
		return "\nLine1: " + line1 + "\nLine2: " + line2 + "\nTown:  " + town + "\nCounty: " + county + "\nCountry: " + country +
			    "\nPostcode: " + postcode;
	}
	
	
	

}
