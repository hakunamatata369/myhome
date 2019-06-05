package org.hakunamatata.myhome.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	private static Session currentSession;

	private static Transaction currentTransaction;

	public static Session openCurrentSession() {
		currentSession = getSessionFactory().openSession();
		return currentSession;
	}

	public static Session openCurrentSessionwithTransaction() {
		currentSession = getSessionFactory().openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}

	public static void closeCurrentSession() {
		currentSession.close();
	}

	public static void closeCurrentSessionwithTransaction() {
		currentTransaction.commit();
		currentSession.close();
	}

	public static synchronized SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}
		return sessionFactory;
	}

	public static Session getCurrentSession() {
		return currentSession;
	}

	public static Transaction getCurrentTransaction() {
		return currentTransaction;
	}

}
