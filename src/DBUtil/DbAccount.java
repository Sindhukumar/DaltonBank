package DBUtil;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import model.Account;
import model.Transaction;

public class DbAccount {

	public static Account getUser(long accountid) {
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		Account account = em.find(Account.class, accountid);
		return account;
	}

	public static void insert(Account account) {
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		// System.out.println("DbBullhorn: begin transaction");
		try {
			trans.begin();
			em.persist(account);
			// System.out.println("DbBullhorn: commit transaction");
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("DbBullhorn: rollback transaction");
			trans.rollback();
		} finally {
			// System.out.println("DbBullhorn: close em");
			em.close();
		}
	}

	public static void update(Account account) {
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.merge(account);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void delete(Account account) {
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.remove(em.merge(account));
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
	
	public static Long countChecking(){
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		String qString = "Select count(u) from Account u where u.accounttype like '%checking%'" ;
		Query q = em.createQuery(qString, Transaction.class);
		List o=null;
		try {
			 o =  q.getResultList();
			 return (Long)o.get(0);
		} catch (NoResultException e) {
			System.out.println(e);
		} finally {
			em.close();
		}
		return (Long)o.get(0);
	}

	public static Long countSaving(){
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		String qString = "Select count(u) from Account u where u.accounttype like '%saving%'" ;
		Query q = em.createQuery(qString, Transaction.class);
		List o=null;
		try {
			 o =  q.getResultList();
			 return (Long)o.get(0);
		} catch (NoResultException e) {
			System.out.println(e);
		} finally {
			em.close();
		}
		return (Long)o.get(0);
	}


}
