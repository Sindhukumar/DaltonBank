package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

public class CustomerAccount implements Serializable {
	Long NumberOfAccounts;
	BigDecimal balance;
	String customername, address, city, state;
	BigDecimal zip;

	public Long getNumberOfAccounts() {
		return NumberOfAccounts;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public BigDecimal getZip() {
		return zip;
	}

	public void setZip(BigDecimal zip) {
		this.zip = zip;
	}

	public void setNumberOfAccounts(Long numberOfAccounts) {
		NumberOfAccounts = numberOfAccounts;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	private static final long serialVersionUID = 1L;

}