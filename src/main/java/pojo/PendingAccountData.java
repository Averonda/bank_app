package pojo;

public class PendingAccountData {
	
	private int userID, pendingID;;
	private double initialDeposit;
	private boolean isChecking;
	
	
	public PendingAccountData() {
		super();
	}
	public PendingAccountData(int userID, double initialDeposit, boolean isChecking, int pendingID) {
		super();
		this.userID = userID;
		this.initialDeposit = initialDeposit;
		this.isChecking = isChecking;
		this.pendingID = pendingID;
	}
	public PendingAccountData(int userID, double initialDeposit, boolean isChecking) {
		super();
		this.userID = userID;
		this.initialDeposit = initialDeposit;
		this.isChecking = isChecking;
	}
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public double getInitialDeposit() {
		return initialDeposit;
	}
	public void setInitialDeposit(double initialDeposit) {
		this.initialDeposit = initialDeposit;
	}
	public boolean isChecking() {
		return isChecking;
	}
	public void setChecking(boolean isChecking) {
		this.isChecking = isChecking;
	}
	public int getPendingID() {
		return pendingID;
	}
	public void setPendingID(int pendingID) {
		this.pendingID = pendingID;
	}
	
}
