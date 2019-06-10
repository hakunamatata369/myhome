package org.hakunamatata.myhome.dao;

import java.util.List;

import org.hakunamatata.myhome.interfaces.iDao;
import org.hakunamatata.myhome.model.Member;

public class MemberDao implements iDao<Member> {

	private String hql = "from Member";

	public MemberDao() {
	}

	public Member getById(long id) {
		return iDao.super.getById(Member.class, id);
	}

	public List<Member> getAll() {
		return iDao.super.getAll(hql);
	}

	public void deleteAll() {
		iDao.super.deleteAll(hql);
	}
}
