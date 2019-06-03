package org.hakunamatata.myhome.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "vehicle")
@XmlRootElement(name = "vehicle")
public class Vehicle {

	@Id
	@GeneratedValue
	@Column(name = "vehicle_id")
	private long vehicleId;

	@Column(name = "vehicle_type")
	private int vehicleType;

	@Column(name = "vehicle_name")
	private String vehicleName;

	@ManyToOne
	@JoinColumn(name="vehicle_owner")
	private Member vehicleOwner;

	public Vehicle(int vehicleType, String vehicleName, Member vehicleOwner) {
		super();
		this.vehicleType = vehicleType;
		this.vehicleName = vehicleName;
		this.vehicleOwner = vehicleOwner;
	}

	public Vehicle() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getVehicle_id() {
		return vehicleId;
	}

	public void setVehicle_id(long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public int getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(int vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public Member getVehicle_owner() {
		return vehicleOwner;
	}

	public void setVehicle_owner(Member vehicleOwner) {
		this.vehicleOwner = vehicleOwner;
	}

}
