package org.hakunamatata.myhome.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Favourite {

    private long memberId;
    private long dataId;

    public Favourite() {
	super();
    }

    public Favourite(long memberId, long dataId) {
	super();
	this.memberId = memberId;
	this.dataId = dataId;
    }

    public long getMemberId() {
	return memberId;
    }

    public void setMemberId(long memberId) {
	this.memberId = memberId;
    }

    public long getDataId() {
	return dataId;
    }

    public void setDataId(long dataId) {
	this.dataId = dataId;
    }
}
