package pojo;

import java.sql.Timestamp;
import java.util.List;

public class LedgerData {
	//TODO: build with ledgerDAO object, pojo for ledger data/info (transaction history)
	
	private int transactionID, userID, routingFrom, accountFrom, routingTo, accountTo, accountID;
	private boolean isExternalTransfer, isNewAccount, isCustomerApproved;
	private Timestamp time;
	private double amtTransfered;
	
	
	public LedgerData() {
		super();
	}
	
	public LedgerData(int transactionID, int userID, int routingFrom, int accountFrom, int routingTo, int accountTo,
			boolean isExternalTransfer, boolean isNewAccount, boolean isCustomerApproved, Timestamp time, double amtTransfered, int accountID) {
		super();
		this.transactionID = transactionID;
		this.userID = userID;
		this.routingFrom = routingFrom;
		this.accountFrom = accountFrom;
		this.routingTo = routingTo;
		this.accountTo = accountTo;
		this.isExternalTransfer = isExternalTransfer;
		this.isNewAccount = isNewAccount;
		this.isCustomerApproved = isCustomerApproved;
		this.time = time;
		this.amtTransfered = amtTransfered;
		this.accountID = accountID;
	}
	

	public LedgerData(AccountData dataTo, AccountData dataFrom, UserData user, double amtTransfered) {
		super ();
		this.userID = user.getUserID();
		this.routingFrom = dataFrom.getRoutingNum();
		this.accountFrom = dataFrom.getAccountNum();
		this.routingTo = dataTo.getRoutingNum();
		this.accountTo = dataTo.getAccountNum();
		this.amtTransfered = amtTransfered;
		this.accountID = dataFrom.getAccountID();
	}
	
	public LedgerData(AccountData ad) {
		super();
		this.userID = ad.getUserID();
		this.routingTo = ad.getRoutingNum();
		this.accountTo = ad.getAccountNum();
		this.amtTransfered = ad.getBalance();
		this.accountID = ad.getAccountID();
	}
	
	public LedgerData getLedgerRow(int transID, List<LedgerData> ledgerList) {
		
		for (LedgerData ledgerData : ledgerList) {
			if(transID == ledgerData.getTransactionID()) {
				return ledgerData;
			}
		}
		return null;
	}
	
	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	
	public double getAmtTransfered() {
		return amtTransfered;
	}

	public void setAmtTransfered(double amtTransfered) {
		this.amtTransfered = amtTransfered;
	}

	public int getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getRoutingFrom() {
		return routingFrom;
	}
	public void setRoutingFrom(int routingFrom) {
		this.routingFrom = routingFrom;
	}
	public int getAccountFrom() {
		return accountFrom;
	}
	public void setAccountFrom(int accountFrom) {
		this.accountFrom = accountFrom;
	}
	public int getRoutingTo() {
		return routingTo;
	}
	public void setRoutingTo(int routingTo) {
		this.routingTo = routingTo;
	}
	public int getAccountTo() {
		return accountTo;
	}
	public void setAccountTo(int accountTo) {
		this.accountTo = accountTo;
	}
	public boolean isExternalTransfer() {
		return isExternalTransfer;
	}
	public void setExternalTransfer(boolean isExternalTransfer) {
		this.isExternalTransfer = isExternalTransfer;
	}
	public boolean isNewAccount() {
		return isNewAccount;
	}
	public void setNewAccount(boolean isNewAccount) {
		this.isNewAccount = isNewAccount;
	}
	public boolean isCustomerApproved() {
		return isCustomerApproved;
	}
	public void setCustomerApproved(boolean isCustomerApproved) {
		this.isCustomerApproved = isCustomerApproved;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	
}
