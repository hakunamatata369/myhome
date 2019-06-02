package org.hakunamatata.myhome.service;

import java.util.List;

import org.hakunamatata.myhome.dao.MemberDao;
import org.hakunamatata.myhome.model.Member;

public class MemberService {

	private static MemberDao memberDao;

	public MemberService() {
		memberDao = new MemberDao();
	}

	public void addMember(Member member) {
		memberDao.openCurrentSessionwithTransaction();
		memberDao.save(member);
		memberDao.closeCurrentSessionwithTransaction();
	}

	public void updateMember(Member member) {
		memberDao.openCurrentSessionwithTransaction();
		memberDao.update(member);
		memberDao.closeCurrentSessionwithTransaction();
	}

	public Member getMember(long id) {
		memberDao.openCurrentSession();
		Member member = memberDao.getById(id);
		memberDao.closeCurrentSession();
		return member;
	}

	public void deleteMember(long id) {
		memberDao.openCurrentSessionwithTransaction();
		Member member = memberDao.getById(id);
		memberDao.delete(member);
		memberDao.closeCurrentSessionwithTransaction();
	}

	public List<Member> getAllMembers() {
		memberDao.openCurrentSession();
		List<Member> members = memberDao.getAll();
		memberDao.closeCurrentSession();
		return members;
	}

	public void deleteAll() {
		memberDao.openCurrentSessionwithTransaction();
		memberDao.deleteAll();
		memberDao.closeCurrentSessionwithTransaction();
	}

	public MemberDao memberDao() {
		return memberDao;
	}

}
