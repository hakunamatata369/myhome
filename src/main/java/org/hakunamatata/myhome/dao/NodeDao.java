package org.hakunamatata.myhome.dao;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hakunamatata.myhome.database.HibernateUtil;
import org.hakunamatata.myhome.exception.DataNotFoundException;
import org.hakunamatata.myhome.interfaces.iDao;
import org.hakunamatata.myhome.model.Node;

public class NodeDao extends HibernateUtil implements iDao<Node> {

	@Override
	public void save(Node node) {
		try {
			HibernateUtil.getCurrentSession().save(node);
		} catch (PersistenceException e) {
			throw new PersistenceException(e.getMessage());
		}
	}

	@Override
	public void update(Node node) {
		try {
			HibernateUtil.getCurrentSession().update(node);
		} catch (PersistenceException e) {
			throw new PersistenceException(e.getMessage());
		}
	}

	@Override
	public Node getById(long id) {
		Node node = (Node) getCurrentSession().get(Node.class, id);
		if (node == null) {
			throw new DataNotFoundException("Node with id : " + id + " is not available");
		}
		return node;
	}

	@Override
	public void delete(Node node) {
		getCurrentSession().delete(node);
	}

	@Override
	public List<Node> getAll() {
		@SuppressWarnings("unchecked")
		List<Node> nodes = (List<Node>) getCurrentSession().createQuery("from Node").list();
		return nodes;
	}

	@Override
	public void deleteAll() {
		List<Node> nodeList = getAll();
		for (Node node : nodeList) {
			delete(node);
		}
	}

}
