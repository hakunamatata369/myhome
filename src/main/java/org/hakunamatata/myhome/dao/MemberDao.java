package org.hakunamatata.myhome.dao;

import java.util.List;

import org.hakunamatata.myhome.interfaces.Dao;
import org.hakunamatata.myhome.model.Member;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class MemberDao implements Dao<Member> {

    private Session currentSession;
    
    private Transaction currentTransaction;
 
    public MemberDao() {
    }
 
    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }
 
    public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }
     
    public void closeCurrentSession() {
        currentSession.close();
    }
     
    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }
     
    private static SessionFactory getSessionFactory() {
    	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    	return sessionFactory;
    }
 
    public Session getCurrentSession() {
        return currentSession;
    }
 
    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }
 
    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }
 
    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }
 
    public void save(Member member) {
        getCurrentSession().save(member);
    }
 
    public void update(Member member) {
        getCurrentSession().update(member);
    }
 
    public Member getById(long id) {
    	Member member = (Member) getCurrentSession().get(Member.class, id);
        return member; 
    }
 
    public void delete(Member member) {
        getCurrentSession().delete(member);
    }
 
    @SuppressWarnings("unchecked")
    public List<Member> getAll() {
        List<Member> members = (List<Member>) getCurrentSession().createQuery("from Member").list();
        return members;
    }
 
    public void deleteAll() {
        List<Member> memberList = getAll();
        for (Member member : memberList) {
            delete(member);
        }
    }
}
