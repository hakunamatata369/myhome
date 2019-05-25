package org.hakunamatata.myhome.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Name {

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "middle_name")
	private String MiddleName;

	@Column(name = "last_name")
	private String LastName;

	public Name() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Name(String firstName, String middleName, String lastName) {
		super();
		this.firstName = firstName;
		MiddleName = middleName;
		LastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return MiddleName;
	}

	public void setMiddleName(String middleName) {
		MiddleName = middleName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

}
