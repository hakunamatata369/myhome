package org.hakunamatata.myhome.dao;

import java.util.List;

import javax.persistence.Query;

import org.hakunamatata.myhome.database.HibernateUtil;
import org.hakunamatata.myhome.interfaces.iDao;
import org.hakunamatata.myhome.model.Comment;

public class CommentDao implements iDao<Comment> {

	private String hql = "from Comment where parentId = :parentId";

	public CommentDao() {
	}

	public Comment getById(long parentId, long dataId) {
		hql += " and dataId = :dataId" ;
		Query query = HibernateUtil.getCurrentSession().createQuery(hql);
		query.setParameter("parentId", parentId);
		query.setParameter("dataId", dataId);
		return (Comment) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Comment> getAll(long parentId) {
		Query query = HibernateUtil.getCurrentSession().createQuery(hql);
		query.setParameter("parentId", parentId);
		return (List<Comment>) query.getResultList();
	}

	public void deleteAll(long parentId) {
		List<Comment> entityList = getAll(parentId);
		for (Comment entity : entityList) {
			delete(entity);
		}
	}
}
