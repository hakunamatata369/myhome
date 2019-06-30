package org.hakunamatata.myhome.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@Entity
@Table(name = "comment")
@PrimaryKeyJoinColumn(name = "comment_id", referencedColumnName = "data_id")
public class Comment extends Node {

	@Column(name = "comment")
	@Lob
	private String comment;

	@OneToMany
	@JoinTable(name = "comment_members_tagged", joinColumns = @JoinColumn(name = "comment_id"), inverseJoinColumns = @JoinColumn(name = "member_id"))
	private Collection<Member> membersTagged = new ArrayList<>();

	@OneToMany
	@JoinTable(name = "comment_members_mentioned", joinColumns = @JoinColumn(name = "comment_id"), inverseJoinColumns = @JoinColumn(name = "member_id"))
	private Collection<Member> membersMentioned = new ArrayList<>();

	@OneToMany
	@JoinTable(name = "comment_attachments", joinColumns = @JoinColumn(name = "comment_id"), inverseJoinColumns = @JoinColumn(name = "attachment_id"))
	private Collection<Node> nodesAttached = new ArrayList<>();

	@Transient
	private List<Link> links = new ArrayList<>();

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(long parentId, String name, int subType, String createdBy, Date createdDate, String modifiedby,
			Date modifieddate, int childCount, String extendedData) {
		super(parentId, name, subType, createdBy, createdDate, modifiedby, modifieddate, childCount, extendedData);
		// TODO Auto-generated constructor stub
	}

	@XmlTransient
	public Collection<Member> getMembersTagged() {
		return membersTagged;
	}

	public void setMembersTagged(Collection<Member> membersTagged) {
		this.membersTagged = membersTagged;
	}

	@XmlTransient
	public Collection<Member> getMembersMentioned() {
		return membersMentioned;
	}

	public void setMembersMentioned(Collection<Member> membersMentioned) {
		this.membersMentioned = membersMentioned;
	}

	@XmlTransient
	public Collection<Node> getNodesAttached() {
		return nodesAttached;
	}

	public void setNodesAttached(Collection<Node> nodesAttached) {
		this.nodesAttached = nodesAttached;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
