package org.hakunamatata.myhome.dao;

import java.util.List;

import org.hakunamatata.myhome.interfaces.iDao;
import org.hakunamatata.myhome.model.House;

public class HouseDao implements iDao<House> {

	private String hql = "from House";

	public HouseDao() {
	}

	public House getById(long id) {
		return iDao.super.getById(House.class, id);
	}

	public List<House> getAll() {
		return iDao.super.getAll(hql);
	}

	public void deleteAll() {
		iDao.super.deleteAll(hql);
	}
}
