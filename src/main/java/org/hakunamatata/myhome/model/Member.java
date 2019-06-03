package org.hakunamatata.myhome.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.hibernate.annotations.Type;

@XmlRootElement
@Entity
@SelectBeforeUpdate
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "member")
@NamedQuery(name = "Member.getNamebyId", query = "select name from Member where memberId = ?1")
@NamedNativeQuery(name= "Member.byFirstName", query = "select * from member where first_name = ?1", resultClass = Member.class)
public class Member {

	@Id
	@GeneratedValue
	@Column(name = "member_id")
	private long memberId;

	@Column(name = "member_type")
	private short memberType;

	@Embedded
	private Name name;

	@ElementCollection(fetch = FetchType.EAGER)
	@JoinTable(name = "phone", joinColumns = @JoinColumn(name = "member_id"))
	@GenericGenerator(name = "sequence_gen", strategy = "sequence")
	@CollectionId(columns = { @Column(name = "phone_id") }, generator = "sequence_gen", type = @Type(type = "long"))
	private Collection<Phone> phones = new ArrayList<>();

	@Column(name = "email_id")
	private String emailId;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "member_addresses", joinColumns = @JoinColumn(name = "member_id"), inverseJoinColumns = @JoinColumn(name = "address_id"))
	private Collection<Address> addresses = new ArrayList<>();

	@Column(name = "gender")
	private short gender;

	@Column(name = "date_of_birth")
	private Date dob;

	@Column(name = "date_of_joinning")
	private Date doj;

	@OneToMany
	@JoinTable(name = "favorites", joinColumns = @JoinColumn(name = "member_id"), inverseJoinColumns = @JoinColumn(name = "data_id"))
	private Collection<Node> favourites = new ArrayList<>();

	@OneToMany(mappedBy = "vehicleOwner", cascade = CascadeType.ALL)
	private Collection<Vehicle> ownedVehicles = new ArrayList<>();

	@ManyToMany
	@JoinTable(name = "member_relations", joinColumns = @JoinColumn(name = "member_id"), inverseJoinColumns = @JoinColumn(name = "child_id "))
	private Collection<Member> childs = new ArrayList<>();

	@Transient
	private List<Link> links = new ArrayList<>();

	public Member() {

	}

	public Member(short memberType, Name name, String emailId, short gender, Date dob, Date doj) {
		super();
		this.memberType = memberType;
		this.name = name;
		this.emailId = emailId;
		this.gender = gender;
		this.dob = dob;
		this.doj = doj;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
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

	public Collection<Phone> getPhones() {
		return phones;
	}

	public void setPhones(Collection<Phone> phones) {
		this.phones = phones;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
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

	@XmlTransient
	public Collection<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Collection<Address> addresses) {
		this.addresses = addresses;
	}

	@XmlTransient
	public Collection<Node> getFavourites() {
		return favourites;
	}

	public void setFavourites(Collection<Node> favourites) {
		this.favourites = favourites;
	}

	@XmlTransient
	public Collection<Vehicle> getOwnedVehicles() {
		return ownedVehicles;
	}

	public void setOwnedVehicles(Collection<Vehicle> ownedVehicles) {
		this.ownedVehicles = ownedVehicles;
	}

	@XmlTransient
	public Collection<Member> getChilds() {
		return childs;
	}

	public void setChilds(Collection<Member> childs) {
		this.childs = childs;
	}
}
