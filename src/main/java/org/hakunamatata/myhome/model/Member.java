package org.hakunamatata.myhome.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Member {

    private long memberId;
    private short memberType;
    private String firstName;
    private String middleName;
    private String lastName;
    private long phone;
    private String emailId;
    private String address;
    private short gender;
    private Date dob;
    private Date doj;
    private List<Link> links = new ArrayList<>();

    public Member() {
	
    }
    
    public Member(long memberId, short memberType, String firstName, String middleName, String lastName, long phone,
	    String emailId, String address, short gender, Date dob, Date doj) {
	super();
	this.memberId = memberId;
	this.memberType = memberType;
	this.firstName = firstName;
	this.middleName = middleName;
	this.lastName = lastName;
	this.phone = phone;
	this.emailId = emailId;
	this.address = address;
	this.gender = gender;
	this.dob = dob;
	this.doj = doj;
    }

    public long getMemberId() {
	return memberId;
    }

    public void setMemberId(long memberId) {
	this.memberId = memberId;
    }

    public short getMemberType() {
	return memberType;
    }

    public void setMemberType(short memberType) {
	this.memberType = memberType;
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getMiddleName() {
	return middleName;
    }

    public void setMiddleName(String middleName) {
	this.middleName = middleName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public long getPhone() {
	return phone;
    }

    public void setPhone(long phone) {
	this.phone = phone;
    }

    public String getEmailId() {
	return emailId;
    }

    public void setEmailId(String emailId) {
	this.emailId = emailId;
    }

    public String getAddress() {
	return address;
    }

    public void setAddress(String address) {
	this.address = address;
    }

    public short getGender() {
	return gender;
    }

    public void setGender(short gender) {
	this.gender = gender;
    }

    public Date getDob() {
	return dob;
    }

    public void setDob(Date dob) {
	this.dob = dob;
    }

    public Date getDoj() {
	return doj;
    }

    public void setDoj(Date doj) {
	this.doj = doj;
    }

    public List<Link> getLinks() {
	return links;
    }

    public void setLinks(List<Link> links) {
	this.links = links;
    }

    public void addLink(String url, String rel) {
	Link link = new Link();
	link.setLink(url);
	link.setRel(rel);
	links.add(link);
    }
}
