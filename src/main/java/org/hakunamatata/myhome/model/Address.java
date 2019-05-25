package org.hakunamatata.myhome.model;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

	private String street;
	private String city;
	private String state;
	private String country;
	private int pincode;
	private int addressType;
	private String adressLabel;

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Address(String street, String city, String state, String country, int pincode, int addressType,
			String adressLabel) {
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
		this.addressType = addressType;
		this.adressLabel = adressLabel;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public int getAddressType() {
		return addressType;
	}
	public void setAddressType(int addressType) {
		this.addressType = addressType;
	}
	public String getAdressLabel() {
		return adressLabel;
	}
	public void setAdressLabel(String adressLabel) {
		this.adressLabel = adressLabel;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

}
