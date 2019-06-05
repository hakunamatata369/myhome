package org.hakunamatata.myhome.service;

import java.util.ArrayList;
import java.util.Collection;

import org.hakunamatata.myhome.dao.MemberDao;
import org.hakunamatata.myhome.dao.NodeDao;
import org.hakunamatata.myhome.database.HibernateUtil;
import org.hakunamatata.myhome.exception.DataNotFoundException;
import org.hakunamatata.myhome.model.Member;
import org.hakunamatata.myhome.model.Node;

public class FavouriteService {

	private static MemberDao memberDao;
	private static NodeDao nodeDao;

	public FavouriteService() {
		memberDao = new MemberDao();
		nodeDao = new NodeDao();
	}

	public Collection<Node> getAllFavourites(Long memberId) {

		Collection<Node> favourites = new ArrayList<>();

		HibernateUtil.openCurrentSession();
		Member member = memberDao.getById(memberId);
		if (member.getFavourites().size() == 0) {
			throw new DataNotFoundException("No Favourites are available for member with id:" + memberId);
		} else {
			favourites = member.getFavourites();
		}
		HibernateUtil.closeCurrentSession();
		return favourites;
	}

	public void addFavourite(Long memberId, Long favouriteId) {
		HibernateUtil.openCurrentSessionwithTransaction();
		Member member = memberDao.getById(memberId);
		Node node = nodeDao.getById(favouriteId);
		member.getFavourites().add(node);
		memberDao.update(member);
		HibernateUtil.closeCurrentSessionwithTransaction();
	}

	public void removeFavourite(Long memberId, Long favouriteId) {
		HibernateUtil.openCurrentSessionwithTransaction();
		Member member = memberDao.getById(memberId);
		Node node = nodeDao.getById(favouriteId);
		member.getFavourites().remove(node);
		memberDao.update(member);
		HibernateUtil.closeCurrentSessionwithTransaction();
	}
}
