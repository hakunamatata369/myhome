package org.hakunamatata.myhome.service;

import java.util.List;

import org.hakunamatata.myhome.dao.ReactionDao;
import org.hakunamatata.myhome.database.HibernateUtil;
import org.hakunamatata.myhome.model.Node;
import org.hakunamatata.myhome.model.Reaction;

public class ReactionService {

	private ReactionDao reactionDao;
	
	public ReactionService() {
		reactionDao = new ReactionDao();
	}

	public List<Reaction> getAllReactions(Node node) {
		HibernateUtil.openCurrentSession();
		List<Reaction> reactions = reactionDao.getAll(node);
		HibernateUtil.closeCurrentSession();
		return reactions;
	}

	public void deleteAllReactions(Node node) {
		HibernateUtil.openCurrentSessionwithTransaction();
		reactionDao.deleteAll(node);
		HibernateUtil.closeCurrentSessionwithTransaction();
	}

	public Reaction postReaction(Reaction reaction) {
		HibernateUtil.openCurrentSessionwithTransaction();
		reaction = reactionDao.save(reaction);
		HibernateUtil.closeCurrentSessionwithTransaction();
		return reaction;
	}

	public Reaction updateReaction(Reaction reaction) {
		HibernateUtil.openCurrentSessionwithTransaction();
		reaction = reactionDao.update(reaction);
		HibernateUtil.closeCurrentSessionwithTransaction();
		return reaction;
	}
}
