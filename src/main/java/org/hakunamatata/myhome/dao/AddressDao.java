package org.hakunamatata.myhome.dao;

import java.util.List;

import org.hakunamatata.myhome.interfaces.iDao;
import org.hakunamatata.myhome.model.Address;

public class AddressDao implements iDao<Address> {

	private String hql = "from Address";

	public AddressDao() {
	}

	public Address getById(long id) {
		return iDao.super.getById(Address.class, id);
	}

	public List<Address> getAll() {
		return iDao.super.getAll(hql);
	}

	public void deleteAll() {
		iDao.super.deleteAll(hql);
	}
}
