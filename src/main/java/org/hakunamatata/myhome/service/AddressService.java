package org.hakunamatata.myhome.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hakunamatata.myhome.dao.AddressDao;
import org.hakunamatata.myhome.dao.MemberDao;
import org.hakunamatata.myhome.database.HibernateUtil;
import org.hakunamatata.myhome.exception.DataNotFoundException;
import org.hakunamatata.myhome.model.Address;
import org.hakunamatata.myhome.model.Member;

public class AddressService {

	private static MemberDao memberDao;
	private static AddressDao addressDao;

	public AddressService() {
		memberDao = new MemberDao();
		addressDao = new AddressDao();
	}

	public Collection<Address> getAllMemberAddresses(Long memberId) {

		Collection<Address> addresses = new ArrayList<>();

		HibernateUtil.openCurrentSession();
		Member member = memberDao.getById(memberId);
		if (member.getAddresses().size() == 0) {
			throw new DataNotFoundException("No Addresses are available for member with id:" + memberId);
		} else {
			addresses = member.getAddresses();
		}
		HibernateUtil.closeCurrentSession();
		return addresses;
	}

	public void addMemberAddress(Long memberId, Long addressId) {
		HibernateUtil.openCurrentSessionwithTransaction();
		Member member = memberDao.getById(memberId);
		Address address = addressDao.getById(addressId);
		member.getAddresses().add(address);
		memberDao.update(member);
		HibernateUtil.closeCurrentSessionwithTransaction();
	}

	public void removeMemberAddress(Long memberId, Long addressId) {
		HibernateUtil.openCurrentSessionwithTransaction();
		Member member = memberDao.getById(memberId);
		Address address = addressDao.getById(addressId);
		member.getAddresses().remove(address);
		memberDao.update(member);
		HibernateUtil.closeCurrentSessionwithTransaction();		
	}

	public Address addAddress(Address address) {
		HibernateUtil.openCurrentSessionwithTransaction();
		address = addressDao.save(address);
		HibernateUtil.closeCurrentSessionwithTransaction();
		return address;
	}

	public Address updateAddress(Address address) {
		HibernateUtil.openCurrentSessionwithTransaction();
		addressDao.update(address);
		HibernateUtil.closeCurrentSessionwithTransaction();
		return address;
	}

	public Address getAddress(long id) {
		HibernateUtil.openCurrentSession();
		Address address = addressDao.getById(id);
		HibernateUtil.closeCurrentSession();
		return address;
	}

	public void deleteAddress(long id) {
		HibernateUtil.openCurrentSessionwithTransaction();
		Address address = addressDao.getById(id);
		addressDao.delete(address);
		HibernateUtil.closeCurrentSessionwithTransaction();
	}

	public List<Address> getAllAddresses() {
		HibernateUtil.openCurrentSession();
		List<Address> addresses = addressDao.getAll();
		HibernateUtil.closeCurrentSession();
		return addresses;
	}

	public void deleteAllAddresses() {
		HibernateUtil.openCurrentSessionwithTransaction();
		addressDao.deleteAll();
		HibernateUtil.closeCurrentSessionwithTransaction();
	}

}
