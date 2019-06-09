package org.hakunamatata.myhome.database;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hakunamatata.myhome.model.Address;
import org.hakunamatata.myhome.model.Comment;
import org.hakunamatata.myhome.model.House;
import org.hakunamatata.myhome.model.Member;
import org.hakunamatata.myhome.model.Name;
import org.hakunamatata.myhome.model.Node;
import org.hakunamatata.myhome.model.Phone;
import org.hakunamatata.myhome.model.Vehicle;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Database {

	private static Map<Long, Comment> comments = new HashMap<>();
	private static Map<Long, Member> members = new HashMap<>();

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

	public static void main(String args[]) {

		Name person1 = new Name("Admin", "middlename", "lastname");
		Name group1 = new Name("Default", null, "group");
		Member deluser = new Member((short) 0, person1, "admin@abc.com", (short) 0, new Date(), new Date());
		Member m1 = new Member((short) 0, person1, "admin@abc.com", (short) 0, new Date(), new Date());
		
			Vehicle v1 = new Vehicle(1, "Xylo", m1);
			m1.getOwnedVehicles().add(v1);
			
			long parentId = 2000;
			Node apartment = new Node(parentId, "9star hills apartment", 1,  "Admin", new Date(), null, null, 0, null);
			m1.getFavourites().add(apartment);
			
			Phone phone1 = new Phone(1,9876543);
			m1.getPhones().add(phone1);

		Member m2 = new Member((short) 0, group1, "defaultgroup@abc.com", (short) -1, new Date(), new Date());
		m2.getChilds().add(m1);

		Address home_addr = new Address("9star hills residency", "hyderabad", "telangana", "india", 500089, 1, "Home");
		Address work_addr = new Address("opentext", "hyderabad", "telangana", "india", 500081, 2, "Work");

		m1.getAddresses().add(home_addr);
		m1.getAddresses().add(work_addr);

		Address house_address = new Address("9star hills residency", "hyderabad", "telangana", "india", 500089, 1, "Home");
		House h1 = new House(parentId,"testhome1", 1,  "Admin", new Date(), null, null, 0, null);
		h1.setHouseAddress(house_address);
		h1.setHouseType(1);
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();

		session.beginTransaction();
		session.save(deluser);
		session.save(apartment);
		session.save(h1);
		//session.save(home_addr);
		//session.save(work_addr);
		//session.save(v1);
		//session.save(m1);
		session.persist(m1);
		session.save(m2);
		session.getTransaction().commit();
		session.close();
		
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		Member temp_member = (Member) session.get(Member.class,1L);
		Node node = (Node) session.get(Node.class, 2L);
		System.out.println(node.getName());
		System.out.println(temp_member.getName().toString());
		temp_member.setEmailId("abcd@abc.com");
		session.update(temp_member);
		//session.delete(temp_member);
		
		session.getTransaction().commit();
		session.close();
		
		
		//hql queries
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("select name from Member where memberId > 0L");
		query.setCacheable(true);
		query.setFirstResult(1);
		query.setMaxResults(5);
		List<Name> members_names = query.list();
		for( Name m : members_names) {
			System.out.println(m.toString());
		}
		
		
		Query query1 = session.createQuery("select max(memberId) from Member where memberId > ?1");
		query1.setParameter(1, 0L);
		System.out.println(query1.uniqueResult());
		
		
		Query query2 = session.createQuery("select name from Member where memberId > :userId");
		query2.setCacheable(true);
		query2.setParameter("userId", 0L);
		List<Name> members_names1 = query2.list();
		for( Name m : members_names1) {
			System.out.println(m.toString());
		}

		// duplicated for testing the query cache concept
		Query query2dup = session.createQuery("select name from Member where memberId > :userId");
		query2dup.setCacheable(true);
		query2dup.setParameter("userId", 0L);
		List<Name> members_names1dup = query2dup.list();
		for( Name m : members_names1dup) {
			System.out.println(m.toString());
		}
		
		Query query3 = session.getNamedQuery("Member.getNamebyId");
		query3.setParameter(1, 0L);
		List<Name> members_names2 = query3.list();
		for( Name m : members_names2) {
			System.out.println(m.toString());
		}
		
		Query query4 = session.getNamedQuery("Member.byFirstName");
		query4.setParameter(1, "Admin");
		List<Member> members4 = query4.getResultList();
		for( Member m : members4) {
			System.out.println(m.getName().toString());
		}
		
		//criteria api`s
		System.out.println("Criteria api results === > ");
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Member> criteria = cb.createQuery(Member.class);
		Root<Member> root = criteria.from(Member.class);
		criteria.select(root);
		
		List<Member> members5 = session.createQuery(criteria).getResultList();
		for( Member m : members5) {
			System.out.println(m.getName().toString());
		}
		
		
		session.getTransaction().commit();
		session.close();

	}
}
