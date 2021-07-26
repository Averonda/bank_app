package pojo;

//import java.util.ArrayList;
//import java.util.List;

public class AccountData {
	
	//private List<AccountData> account = new ArrayList<AccountData>();
	private int accountID, userID, routingNum, accountNum;
	private boolean isSavings, isChecking;
	private double balance;
	
	
//	public List<AccountData> getAccount() {
//		return account;
//	}
//	public void setAccount(List<AccountData> account) {
//		this.account = account;
//	}
	
	public AccountData() {
		super();
	}
	
	public AccountData(int accountID, int userID, int routingNum, int accountNum, boolean isSavings, boolean isChecking,
			double balance) {
		super();
		this.accountID = accountID;
		this.userID = userID;
		this.routingNum = routingNum;
		this.accountNum = accountNum;
		this.isSavings = isSavings;
		this.isChecking = isChecking;
		this.balance = balance;
	}
	
	public int getAccountID() {
		return accountID;
	}
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getRoutingNum() {
		return routingNum;
	}
	public void setRoutingNum(int routingNum) {
		this.routingNum = routingNum;
	}
	public int getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(int accountNum) {
		this.accountNum = accountNum;
	}
	public boolean isSavings() {
		return isSavings;
	}
	public void setSavings(boolean isSavings) {
		this.isSavings = isSavings;
	}
	public boolean isChecking() {
		return isChecking;
	}
	public void setChecking(boolean isChecking) {
		this.isChecking = isChecking;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
}
