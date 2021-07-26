package pojo;

//import java.util.ArrayList;
//import java.util.List;

public class UserData {

//	private List<UserData> user = new ArrayList<UserData>();
	private int userID;
	private String userName, firstName, lastName;
	private boolean isEE;

//	public List<UserData> getUser() {
//		return user;
//	}
//	public void setUser(List<UserData> user) {
//		this.user = user;
//	}

	public UserData() {
		super();
	}

	public UserData(int userID, String userName, boolean isEE, String firstName, String lastName) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.isEE = isEE;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isEE() {
		return isEE;
	}

	public void setEE(boolean isEE) {
		this.isEE = isEE;
	}

}
