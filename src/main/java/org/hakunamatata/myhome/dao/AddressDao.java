package org.hakunamatata.myhome.dao;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hakunamatata.myhome.database.HibernateUtil;
import org.hakunamatata.myhome.exception.DataNotFoundException;
import org.hakunamatata.myhome.model.Address;

public class AddressDao {

	public Address save(Address address) {
		try {
			HibernateUtil.getCurrentSession().save(address);
		} catch (PersistenceException e) {
			throw new PersistenceException(e.getMessage());
		}
		return address;
	}

	public Address update(Address address) {
		try {
			HibernateUtil.getCurrentSession().update(address);
		} catch (PersistenceException e) {
			throw new PersistenceException(e.getMessage());
		}
		return address;
	}

	public Address getById(Long addressId) {
		Address address = (Address) HibernateUtil.getCurrentSession().get(Address.class, addressId);
		if (address == null) {
			throw new DataNotFoundException("Address with id : " + addressId + " is not available");
		}
		return address;
	}

	public void delete(Address address) {
		HibernateUtil.getCurrentSession().delete(address);
	}

	public List<Address> getAll() {
		@SuppressWarnings("unchecked")
		List<Address> addresses = (List<Address>) HibernateUtil.getCurrentSession().createQuery("from Address").list();
		return addresses;
	}

	public void deleteAll() {
		List<Address> addressList = getAll();
		for (Address address : addressList) {
			delete(address);
		}
	}
}
