package org.hakunamatata.myhome.database;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.hakunamatata.myhome.model.Address;
import org.hakunamatata.myhome.model.Comment;
import org.hakunamatata.myhome.model.Favourite;
import org.hakunamatata.myhome.model.House;
import org.hakunamatata.myhome.model.Member;
import org.hakunamatata.myhome.model.Name;
import org.hakunamatata.myhome.model.Node;
import org.hakunamatata.myhome.model.Phone;
import org.hakunamatata.myhome.model.Vehicle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Database {

	private static Map<Long, Comment> comments = new HashMap<>();
	private static Map<Long, Member> members = new HashMap<>();
	private static Map<Long, Favourite> favourites = new HashMap<>();

	public static Map<Long, Comment> getComments() {

		Comment c1 = new Comment(1, "Hello", 2000, "Admin", new Date(), "Admin", new Date());
		Comment c2 = new Comment(2, "Hi", 2000, "testuser1", new Date(), "testuser1", new Date());

		comments.put(1L, c1);
		comments.put(2L, c2);

		return comments;
	}

	public static Map<Long, Member> getMembers() {

		Name person1 = new Name("Admin", "middlename", "lastname");
		Name group1 = new Name("Default", null, "group");
		Member m1 = new Member((short) 0, person1, "admin@abc.com", (short) 0, new Date(), new Date());
		Member m2 = new Member((short) 0, group1, "defaultgroup@abc.com", (short) -1, new Date(), new Date());

		members.put(m1.getMemberId(), m1);
		members.put(m2.getMemberId(), m2);

		return members;
	}

	public static Map<Long, Favourite> getFavourites() {

		Favourite f1 = new Favourite(1000, 2000);
		Favourite f2 = new Favourite(1000, 2001);

		favourites.put(1L, f1);
		favourites.put(2L, f2);

		return favourites;
	}

	public static void main(String args[]) {

		Name person1 = new Name("Admin", "middlename", "lastname");
		Name group1 = new Name("Default", null, "group");
		Member m1 = new Member((short) 0, person1, "admin@abc.com", (short) 0, new Date(), new Date());
		
			Vehicle v1 = new Vehicle(1, "Xylo", m1);
			m1.getOwnedVehicles().add(v1);
			
			Node apartment = new Node("9star hills apartment", 1,  "Admin", new Date(), null, null, 0, null);
			m1.getFavourites().add(apartment);
			
			Phone phone1 = new Phone(1,9876543);
			m1.getPhones().add(phone1);

		Member m2 = new Member((short) 0, group1, "defaultgroup@abc.com", (short) -1, new Date(), new Date());
		m2.getChilds().add(m1);

		Address home_addr = new Address("9star hills residency", "hyderabad", "telangana", "india", 500089, 1, "Home");
		Address work_addr = new Address("opentext", "hyderabad", "telangana", "india", 500081, 2, "Work");

		m1.getAddresses().add(home_addr);
		m1.getAddresses().add(work_addr);

		House h1 = new House("testhome1", 1,  "Admin", new Date(), null, null, 0, null);
		h1.setHouseAddress(home_addr);
		h1.setHouseType(1);
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();

		session.beginTransaction();
		session.save(apartment);
		session.save(h1);
		//session.save(home_addr);
		//session.save(work_addr);
		// session.save(v1);
		// session.save(m1);
		session.persist(m1);
		session.save(m2);
		session.getTransaction().commit();
		session.close();
	}
}
