package org.hakunamatata.myhome.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "house")
@PrimaryKeyJoinColumn(name = "house_id", referencedColumnName = "data_id")
public class House extends Node {

	@Column(name = "house_type")
	private int houseType;

	@OneToOne
	@JoinColumn(name="house_address")
	private Address houseAddress;

	@Column(name = "lift")
	private boolean lift;

	@Column(name = "generator")
	private boolean generator;

	@Column(name = "gym")
	private boolean gym;

	@Column(name = "bore_connection")
	private boolean boreConnection;

	@Column(name = "municipal_connection")
	private boolean muncipalConnection;

	@Column(name = "indoor_games")
	private boolean indoorGames;

	@Column(name = "outdoor_games")
	private boolean outdoorGames;

	@Column(name = "function_hall")
	private boolean functionHall;

	@Column(name = "swimming_pool")
	private boolean swimmingPool;

	@Column(name = "guest_rooms")
	private boolean guest_rooms;

	public House() {
		super();
		// TODO Auto-generated constructor stub
	}

	public House(String name, int subType, String createdBy, Date createdDate, String modifiedby, Date modifieddate,
			int childCount, String extendedData) {
		super(name, subType, createdBy, createdDate, modifiedby, modifieddate, childCount, extendedData);
		// TODO Auto-generated constructor stub
	}

	public House(int houseType, Address houseAddress, boolean lift, boolean generator, boolean gym,
			boolean boreConnection, boolean muncipalConnection, boolean indoorGames, boolean outdoorGames,
			boolean functionHall, boolean swimmingPool, boolean guest_rooms) {
		super();
		this.houseType = houseType;
		this.houseAddress = houseAddress;
		this.lift = lift;
		this.generator = generator;
		this.gym = gym;
		this.boreConnection = boreConnection;
		this.muncipalConnection = muncipalConnection;
		this.indoorGames = indoorGames;
		this.outdoorGames = outdoorGames;
		this.functionHall = functionHall;
		this.swimmingPool = swimmingPool;
		this.guest_rooms = guest_rooms;
	}

	public int getHouseType() {
		return houseType;
	}

	public void setHouseType(int houseType) {
		this.houseType = houseType;
	}

	public Address getHouseAddress() {
		return houseAddress;
	}

	public void setHouseAddress(Address houseAddress) {
		this.houseAddress = houseAddress;
	}

	public boolean isLift() {
		return lift;
	}

	public void setLift(boolean lift) {
		this.lift = lift;
	}

	public boolean isGenerator() {
		return generator;
	}

	public void setGenerator(boolean generator) {
		this.generator = generator;
	}

	public boolean isGym() {
		return gym;
	}

	public void setGym(boolean gym) {
		this.gym = gym;
	}

	public boolean isBoreConnection() {
		return boreConnection;
	}

	public void setBoreConnection(boolean boreConnection) {
		this.boreConnection = boreConnection;
	}

	public boolean isMuncipalConnection() {
		return muncipalConnection;
	}

	public void setMuncipalConnection(boolean muncipalConnection) {
		this.muncipalConnection = muncipalConnection;
	}

	public boolean isIndoorGames() {
		return indoorGames;
	}

	public void setIndoorGames(boolean indoorGames) {
		this.indoorGames = indoorGames;
	}

	public boolean isOutdoorGames() {
		return outdoorGames;
	}

	public void setOutdoorGames(boolean outdoorGames) {
		this.outdoorGames = outdoorGames;
	}

	public boolean isFunctionHall() {
		return functionHall;
	}

	public void setFunctionHall(boolean functionHall) {
		this.functionHall = functionHall;
	}

	public boolean isSwimmingPool() {
		return swimmingPool;
	}

	public void setSwimmingPool(boolean swimmingPool) {
		this.swimmingPool = swimmingPool;
	}

	public boolean isGuest_rooms() {
		return guest_rooms;
	}

	public void setGuest_rooms(boolean guest_rooms) {
		this.guest_rooms = guest_rooms;
	}

}
