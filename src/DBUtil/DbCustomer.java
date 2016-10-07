package DBUtil;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.math.BigDecimal;
import model.Customer;
import model.Transaction;

public class DbCustomer {

	public static Customer getUser(long customerid) {
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		Customer customer = em.find(Customer.class, customerid);
		return customer;
	}

	public static void insert(Customer customer) {
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		// System.out.println("DbBullhorn: begin transaction");
		try {
			trans.begin();
			em.persist(customer);
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

	public static void update(Customer customer) {
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.merge(customer);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void delete(Customer customer) {
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.remove(em.merge(customer));
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static List<CustomerAccount> customerAccount() {
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		String qString = "select cus.customername, cus.address, cus.city, cus.state, cus.zip, count(acc.accountid) , sum(acc.balance) from Customer cus left join cus.accounts acc group by cus.customername,cus.customername, cus.address, cus.city, cus.state, cus.zip";
		Query q = em.createQuery(qString, Transaction.class);
		List<CustomerAccount> customerAccountList = new ArrayList<CustomerAccount>();
		List<Object[]> l = null;
		try {
			l = q.getResultList();
			for (Object[] o : l) {
				CustomerAccount customerAccount = new CustomerAccount();
				customerAccount.setCustomername((String) o[0]);
				customerAccount.setAddress((String) o[1]);
				customerAccount.setCity((String) o[2]);
				customerAccount.setState((String) o[3]);
				customerAccount.setZip((BigDecimal) o[4]);
				customerAccount.setNumberOfAccounts((Long) o[5]);
				customerAccount.setBalance((BigDecimal) o[6]);
				customerAccountList.add(customerAccount);
			}

		} catch (NoResultException e) {
			System.out.println(e);
		} finally {
			em.close();
		}
		return customerAccountList;
	}

	public static List<AccountTransaction> accountTransaction(String type) {
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		String qString = "select acc.accountnumber, cus.customername, acc.balance, count(tra.transactionid) from Account  acc "+
"left join acc.customer cus "+
"left join acc.transactions tra where acc.accounttype = 'saving' group by acc.accountnumber, cus.customername, acc.balance";
		Query q = em.createQuery(qString, Transaction.class);
		List<AccountTransaction> accountTransactionList = new ArrayList<AccountTransaction>();
		List<Object[]> l = null;
		try {
			l = q.getResultList();
			for (Object[] o : l) {
				AccountTransaction accountTransaction = new AccountTransaction();
				accountTransaction.setAccountnumber((BigDecimal) o[0]);
				accountTransaction.setCustomername((String) o[1]);
				accountTransaction.setBalance((BigDecimal) o[2]);
				accountTransaction.setNumberOfTransactions((Long) o[3]);
				accountTransactionList.add(accountTransaction);
			}

		} catch (NoResultException e) {
			System.out.println(e);
		} finally {
			em.close();
		}
		return accountTransactionList;
	}
}
