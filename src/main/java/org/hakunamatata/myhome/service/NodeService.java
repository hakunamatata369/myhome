package org.hakunamatata.myhome.service;

import java.util.List;

import org.hakunamatata.myhome.dao.NodeDao;
import org.hakunamatata.myhome.database.HibernateUtil;
import org.hakunamatata.myhome.model.Node;

public class NodeService {

	private static NodeDao nodeDao;

	public NodeService() {
		nodeDao = new NodeDao();
	}

	public Node addNode(Node node) {
		HibernateUtil.openCurrentSessionwithTransaction();
		node = nodeDao.save(node);
		HibernateUtil.closeCurrentSessionwithTransaction();
		return node;
	}

	public Node updateNode(Node node) {
		HibernateUtil.openCurrentSessionwithTransaction();
		nodeDao.update(node);
		HibernateUtil.closeCurrentSessionwithTransaction();
		return node;
	}

	public Node getNode(long id) {
		HibernateUtil.openCurrentSession();
		Node node = nodeDao.getById(id);
		HibernateUtil.closeCurrentSession();
		return node;
	}

	public void deleteNode(long id) {
		HibernateUtil.openCurrentSessionwithTransaction();
		Node node = nodeDao.getById(id);
		nodeDao.delete(node);
		HibernateUtil.closeCurrentSessionwithTransaction();
	}

	public List<Node> getAllNodes() {
		HibernateUtil.openCurrentSession();
		List<Node> nodes = nodeDao.getAll();
		HibernateUtil.closeCurrentSession();
		return nodes;
	}

	public void deleteAllNodes() {
		HibernateUtil.openCurrentSessionwithTransaction();
		nodeDao.deleteAll();
		HibernateUtil.closeCurrentSessionwithTransaction();
	}

	public NodeDao nodeDao() {
		return nodeDao;
	}

}
