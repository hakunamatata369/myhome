package org.hakunamatata.myhome.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "reactions")
public class Reaction implements Serializable {

	private static final long serialVersionUID = -5962425689393841726L;

	@Id
	@OneToOne
	@JoinColumn(name = "data_id")
	private Node reactedNode;

	@Id
	@OneToOne
	@JoinColumn(name = "member_id")
	private Member reactedBy;

	@Column(name = "reacted_date")
	private Date reactedDate;

	@Column(name = "reaction_type")
	private int reactionType;

	public Reaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reaction(Node reactedNode, Member reactedBy, Date reactedDate, int reactionType) {
		super();
		this.reactedNode = reactedNode;
		this.reactedBy = reactedBy;
		this.reactedDate = reactedDate;
		this.reactionType = reactionType;
	}

	public Node getReactedNode() {
		return reactedNode;
	}

	public void setReactedNode(Node reactedNode) {
		this.reactedNode = reactedNode;
	}

	public Member getReactedBy() {
		return reactedBy;
	}

	public void setReactedBy(Member reactedBy) {
		this.reactedBy = reactedBy;
	}

	public Date getReactedDate() {
		return reactedDate;
	}

	public void setReactedDate(Date reactedDate) {
		this.reactedDate = reactedDate;
	}

	public int getReactionType() {
		return reactionType;
	}

	public void setReactionType(int reactionType) {
		this.reactionType = reactionType;
	}
}
