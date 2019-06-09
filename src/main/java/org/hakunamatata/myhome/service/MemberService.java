package org.hakunamatata.myhome.service;

import java.util.List;

import org.hakunamatata.myhome.dao.MemberDao;
import org.hakunamatata.myhome.database.HibernateUtil;
import org.hakunamatata.myhome.model.Member;

public class MemberService {

	private static MemberDao memberDao;

	public MemberService() {
		memberDao = new MemberDao();
	}

	public Member addMember(Member member) {
		HibernateUtil.openCurrentSessionwithTransaction();
		member = memberDao.save(member);
		HibernateUtil.closeCurrentSessionwithTransaction();
		return member;
	}

	public Member updateMember(Member member) {
		HibernateUtil.openCurrentSessionwithTransaction();
		memberDao.update(member);
		HibernateUtil.closeCurrentSessionwithTransaction();
		return member;
	}

	public Member getMember(long id) {
		HibernateUtil.openCurrentSession();
		Member member = memberDao.getById(id);
		HibernateUtil.closeCurrentSession();
		return member;
	}

	public void deleteMember(long id) {
		HibernateUtil.openCurrentSessionwithTransaction();
		Member member = memberDao.getById(id);
		memberDao.delete(member);
		HibernateUtil.closeCurrentSessionwithTransaction();
	}

	public List<Member> getAllMembers() {
		HibernateUtil.openCurrentSession();
		List<Member> members = memberDao.getAll();
		HibernateUtil.closeCurrentSession();
		return members;
	}

	public void deleteAll() {
		HibernateUtil.openCurrentSessionwithTransaction();
		memberDao.deleteAll();
		HibernateUtil.closeCurrentSessionwithTransaction();
	}

	public MemberDao memberDao() {
		return memberDao;
	}

}
