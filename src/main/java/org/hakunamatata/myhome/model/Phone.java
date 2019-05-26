package org.hakunamatata.myhome.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Phone {

	@Column(name = "phone_type")
	private int phoneType;

	@Column(name = "phone")
	private long phone;

	public Phone(int phoneType, long phone) {
		super();
		this.phoneType = phoneType;
		this.phone = phone;
	}

	public Phone() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(int phoneType) {
		this.phoneType = phoneType;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

}
