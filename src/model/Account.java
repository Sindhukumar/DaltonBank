package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the ACCOUNTS database table.
 * 
 */
@Entity
@Table(name="ACCOUNTS")
@NamedQuery(name="Account.findAll", query="SELECT a FROM Account a")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long accountid;

	private BigDecimal accountnumber;

	private String accounttype;

	private BigDecimal balance;

	private String dateclosed;

	private String dateopened;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="CUSTOMERID")
	private Customer customer;

	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="account")
	private List<Transaction> transactions;

	public Account() {
	}

	public long getAccountid() {
		return this.accountid;
	}

	public void setAccountid(long accountid) {
		this.accountid = accountid;
	}

	public BigDecimal getAccountnumber() {
		return this.accountnumber;
	}

	public void setAccountnumber(BigDecimal accountnumber) {
		this.accountnumber = accountnumber;
	}

	public String getAccounttype() {
		return this.accounttype;
	}

	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}

	public BigDecimal getBalance() {
		return this.balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getDateclosed() {
		return this.dateclosed;
	}

	public void setDateclosed(String dateclosed) {
		this.dateclosed = dateclosed;
	}

	public String getDateopened() {
		return this.dateopened;
	}

	public void setDateopened(String dateopened) {
		this.dateopened = dateopened;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Transaction addTransaction(Transaction transaction) {
		getTransactions().add(transaction);
		transaction.setAccount(this);

		return transaction;
	}

	public Transaction removeTransaction(Transaction transaction) {
		getTransactions().remove(transaction);
		transaction.setAccount(null);

		return transaction;
	}

}