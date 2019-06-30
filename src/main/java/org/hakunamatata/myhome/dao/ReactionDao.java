package org.hakunamatata.myhome.dao;

import java.util.List;

import javax.persistence.Query;

import org.hakunamatata.myhome.database.HibernateUtil;
import org.hakunamatata.myhome.interfaces.iDao;
import org.hakunamatata.myhome.model.Node;
import org.hakunamatata.myhome.model.Reaction;

public class ReactionDao implements iDao<Reaction> {

	private String hql = "from Reaction where reactedNode = :node";

	public ReactionDao() {
	}
	
	@SuppressWarnings("unchecked")
	public List<Reaction> getAll(Node node) {
		Query query = HibernateUtil.getCurrentSession().createQuery(hql);
		query.setParameter("node", node);
		return (List<Reaction>) query.getResultList();
	}
	
	public void deleteAll(Node node){
		List<Reaction> reactionList = getAll(node);
		for (Reaction reaction : reactionList) {
			delete(reaction);
		}
	}
}
