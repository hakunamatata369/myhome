package org.hakunamatata.myhome.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "node")
public class Node {
	@Id
	@GeneratedValue
	@Column(name = "data_id")
	private int dataId;

	@Column(name = "subtype")
	private int subType;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "modified_by")
	private String modifiedby;

	@Column(name = "modified_date")
	private Date modifieddate;

	@Column(name = "child_count")
	private int childCount;

	@Column(name = "extended_data")
	private String extendedData;

	public Node() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Node(int subType, String createdBy, Date createdDate, String modifiedby, Date modifieddate, int childCount,
			String extendedData) {
		super();
		this.subType = subType;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedby = modifiedby;
		this.modifieddate = modifieddate;
		this.childCount = childCount;
		this.extendedData = extendedData;
	}

	public int getDataId() {
		return dataId;
	}

	public void setDataId(int dataId) {
		this.dataId = dataId;
	}

	public int getSubType() {
		return subType;
	}

	public void setSubType(int subType) {
		this.subType = subType;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedby() {
		return modifiedby;
	}

	public void setModifiedby(String modifiedby) {
		this.modifiedby = modifiedby;
	}

	public Date getModifieddate() {
		return modifieddate;
	}

	public void setModifieddate(Date modifieddate) {
		this.modifieddate = modifieddate;
	}

	public int getChildCount() {
		return childCount;
	}

	public void setChildCount(int childCount) {
		this.childCount = childCount;
	}

	public String getExtendedData() {
		return extendedData;
	}

	public void setExtendedData(String extendedData) {
		this.extendedData = extendedData;
	}

}
