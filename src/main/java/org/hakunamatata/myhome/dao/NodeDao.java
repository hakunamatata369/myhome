package org.hakunamatata.myhome.dao;

import java.util.List;

import org.hakunamatata.myhome.interfaces.iDao;
import org.hakunamatata.myhome.model.Node;

public class NodeDao implements iDao<Node> {

	private String hql = "from Node";

	public NodeDao() {
	}

	public Node getById(long id) {
		return iDao.super.getById(Node.class, id);
	}

	public List<Node> getAll() {
		return iDao.super.getAll(hql);
	}

	public void deleteAll() {
		iDao.super.deleteAll(hql);
	}

}
