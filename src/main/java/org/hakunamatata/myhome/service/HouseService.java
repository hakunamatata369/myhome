package org.hakunamatata.myhome.service;

import java.util.List;

import org.hakunamatata.myhome.dao.HouseDao;
import org.hakunamatata.myhome.database.HibernateUtil;
import org.hakunamatata.myhome.model.House;

public class HouseService {

	private static HouseDao houseDao;

	public HouseService() {
		houseDao = new HouseDao();
	}

	public House addHouse(House house) {
		HibernateUtil.openCurrentSessionwithTransaction();
		house = houseDao.save(house);
		HibernateUtil.closeCurrentSessionwithTransaction();
		return house;
	}

	public House updateHouse(House house) {
		HibernateUtil.openCurrentSessionwithTransaction();
		houseDao.update(house);
		HibernateUtil.closeCurrentSessionwithTransaction();
		return house;
	}

	public House getHouse(long id) {
		HibernateUtil.openCurrentSession();
		House house = houseDao.getById(id);
		HibernateUtil.closeCurrentSession();
		return house;
	}

	public void deleteHouse(long id) {
		HibernateUtil.openCurrentSessionwithTransaction();
		House house = houseDao.getById(id);
		houseDao.delete(house);
		HibernateUtil.closeCurrentSessionwithTransaction();
	}

	public List<House> getAllHouses() {
		HibernateUtil.openCurrentSession();
		List<House> houses = houseDao.getAll();
		HibernateUtil.closeCurrentSession();
		return houses;
	}

	public void deleteAllHouses() {
		HibernateUtil.openCurrentSessionwithTransaction();
		houseDao.deleteAll();
		HibernateUtil.closeCurrentSessionwithTransaction();
	}

	public HouseDao houseDao() {
		return houseDao;
	}
}
