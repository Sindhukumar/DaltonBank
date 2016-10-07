package DBUtil;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

public class AccountTransaction implements Serializable {
	Long NumberOfTransactions;
	BigDecimal balance;
	String customername;
	BigDecimal accountnumber;

	public Long getNumberOfTransactions() {
		return NumberOfTransactions;
	}

	public void setNumberOfTransactions(Long numberOfTransactions) {
		NumberOfTransactions = numberOfTransactions;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public BigDecimal getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(BigDecimal accountnumber) {
		this.accountnumber = accountnumber;
	}

	private static final long serialVersionUID = 1L;

}