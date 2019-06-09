package org.hakunamatata.myhome.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "node")
@Inheritance(strategy= InheritanceType.JOINED)
@XmlRootElement
public class Node {
	@Id
	@GeneratedValue
	@Column(name = "data_id")
	private long dataId;

	@Column(name = "parent_id")
	private long parentId;

	@Column(name = "name")
	private String name;

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
	@Lob
	private String extendedData;

	public Node() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Node(long parentId, String name, int subType, String createdBy, Date createdDate, String modifiedby, Date modifieddate,
			int childCount, String extendedData) {
		super();
		this.parentId = parentId;
		this.name = name;
		this.subType = subType;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedby = modifiedby;
		this.modifieddate = modifieddate;
		this.childCount = childCount;
		this.extendedData = extendedData;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getDataId() {
		return dataId;
	}

	public void setDataId(long dataId) {
		this.dataId = dataId;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
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
