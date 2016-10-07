import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import DBUtil.AccountTransaction;
import DBUtil.CustomerAccount;
import DBUtil.DbAccount;
import DBUtil.DbCustomer;
import DBUtil.DbTransaction;

public class DbTest {

	@Test
	public void test() {
		Long numOfTransaction = DbTransaction.count();
		System.out.println("Total Number of Transaction:--->"+numOfTransaction);
		
		Long numOfDeposits = DbTransaction.countDeposits();
		System.out.println("Number of Deposits:--->"+numOfDeposits);
		
		BigDecimal totalDeposits = DbTransaction.totalDeposits();
		System.out.println("Total Deposits:--->"+totalDeposits);
		
		Long numOfWithdraw = DbTransaction.countWithdraw();
		System.out.println("Number of Withdraw:--->"+numOfWithdraw);
		
		BigDecimal totalFee = DbTransaction.totalFee();
		System.out.println("Total amount of Fees charged-->"+totalFee);
		
		Long numOfChecking = DbAccount.countChecking();
		System.out.println("Number of Checking accounts-->"+numOfChecking);
		
		Long numOfSaving = DbAccount.countSaving();
		System.out.println("Number of Saving accounts-->"+numOfSaving);
		
		System.out.println("List of customer name, address, city, state, zip and number of accounts and balances of accounts.");
		List<CustomerAccount> customerAccounts= DbCustomer.customerAccount();
		for(CustomerAccount ca:customerAccounts){
			
			System.out.println(ca.getCustomername()+"   "+ca.getAddress()+"   "+ca.getCity()+"   "+ca.getState()+"   "+ca.getZip()+"   "+ca.getNumberOfAccounts()+"   "+ca.getBalance());
		}
		
		System.out.println("List of all savings accounts showing customers and balances and number of transactions");
		List<AccountTransaction> accountTransaction= DbCustomer.accountTransaction("saving");
		for(AccountTransaction ca:accountTransaction){
			
			System.out.println(ca.getCustomername()+"   "+ca.getNumberOfTransactions()+"   "+ca.getAccountnumber()+"   "+ca.getBalance());
		}
		
	}

}
