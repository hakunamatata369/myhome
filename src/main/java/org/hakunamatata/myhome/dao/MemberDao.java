package org.hakunamatata.myhome.dao;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hakunamatata.myhome.database.HibernateUtil;
import org.hakunamatata.myhome.exception.DataNotFoundException;
import org.hakunamatata.myhome.interfaces.iDao;
import org.hakunamatata.myhome.model.Member;

public class MemberDao implements iDao<Member> {

	public MemberDao() {
	}

	public Member save(Member member) {
		try {
			HibernateUtil.getCurrentSession().save(member);
		} catch (PersistenceException e) {
			throw new PersistenceException(e.getMessage());
		}
		return member;
	}

	public Member update(Member member) {
		try {
			HibernateUtil.getCurrentSession().update(member);
		} catch (PersistenceException e) {
			throw new PersistenceException(e.getMessage());
		}
		return member;
	}

	public Member getById(long id) {
		Member member = (Member) HibernateUtil.getCurrentSession().get(Member.class, id);
		if (member == null) {
			throw new DataNotFoundException("Member with id : " + id + " is not available");
		}
		return member;
	}

	public void delete(Member member) {
		HibernateUtil.getCurrentSession().delete(member);
	}

	public List<Member> getAll() {
		@SuppressWarnings("unchecked")
		List<Member> members = (List<Member>) HibernateUtil.getCurrentSession().createQuery("from Member").list();
		return members;
	}

	public void deleteAll() {
		List<Member> memberList = getAll();
		for (Member member : memberList) {
			delete(member);
		}
	}
}
