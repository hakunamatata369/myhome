package org.hakunamatata.myhome.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Comment {

    private long commentId;
    private String comment;
    private long commentedOn;
    private String createdBy;
    private Date createdDate;
    private String modifiedBy;
    private Date modifiedDate;
    private List<Link> links = new ArrayList<>();
    
    
    public Comment() {

    }

    public Comment(long commentId, String comment, long commentedOn, String createdBy, Date createdDate,
	    String modifiedBy, Date modifiedDate) {
	super();
	this.commentId = commentId;
	this.comment = comment;
	this.commentedOn = commentedOn;
	this.createdBy = createdBy;
	this.createdDate = createdDate;
	this.modifiedBy = modifiedBy;
	this.modifiedDate = modifiedDate;
    }

    public long getCommentId() {
	return commentId;
    }

    public void setCommentId(long commentId) {
	this.commentId = commentId;
    }

    public String getComment() {
	return comment;
    }

    public void setComment(String comment) {
	this.comment = comment;
    }

    public long getCommentedOn() {
	return commentedOn;
    }

    public void setCommentedOn(long commentedOn) {
	this.commentedOn = commentedOn;
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

    public String getModifiedBy() {
	return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
	this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
	return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
	this.modifiedDate = modifiedDate;
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
