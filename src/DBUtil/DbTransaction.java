package DBUtil;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Transaction;

public class DbTransaction {

	public static Transaction getUser(long transactionid) {
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		Transaction transaction = em.find(Transaction.class, transactionid);
		return transaction;
	}

	public static void insert(Transaction transaction) {
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		// System.out.println("DbBullhorn: begin transaction");
		try {
			trans.begin();
			em.persist(transaction);
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

	public static void update(Transaction transaction) {
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.merge(transaction);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void delete(Transaction transaction) {
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.remove(em.merge(transaction));
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
	
	public static Long count(){
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		String qString = "Select count(u) from Transaction u " ;
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
	
	public static Long countDeposits(){
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		String qString = "Select count(u) from Transaction u where u.transactiontype = 'deposit'" ;
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
	
	public static BigDecimal totalDeposits(){
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		String qString = "Select sum(u.amount) from Transaction u where u.transactiontype = 'deposit'" ;
		Query q = em.createQuery(qString, Transaction.class);
		List o=null;
		try {
			 o =  q.getResultList();
			 return (BigDecimal)o.get(0);
		} catch (NoResultException e) {
			System.out.println(e);
		} finally {
			em.close();
		}
		return (BigDecimal)o.get(0);
	}
	
	public static Long countWithdraw(){
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		String qString = "Select count(u) from Transaction u where u.transactiontype like '%withdraw%' or u.transactiontype like '%fee%'" ;
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
	
	public static BigDecimal totalFee(){
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		String qString = "Select sum(u.amount) from Transaction u where u.transactiontype like '%fee%'" ;
		Query q = em.createQuery(qString, Transaction.class);
		List o=null;
		try {
			 o =  q.getResultList();
			 return (BigDecimal)o.get(0);
		} catch (NoResultException e) {
			System.out.println(e);
		} finally {
			em.close();
		}
		return (BigDecimal)o.get(0);
	}
}
