package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pojo.UserData;


public class UserDAO extends DAO{

	private Connection con;
	
	public UserData signIn(String userName, String password) throws SQLException {
		//Connection conn;
		UserData user = new UserData();
		try{
			//should be in try/finally block, but need to prevent con from returning null.
			con = DAO.get_instance().makeConnection();
			String query = "select * from user_creds where user_name = ? and user_password = ?";
			
			PreparedStatement statement = con.prepareStatement(query);
			
			statement.setString(1, userName);
			statement.setString(2, password);
			
			ResultSet results = statement.executeQuery();
			
			results.next();
			user.setUserID(results.getInt(1));
			user.setUserName(results.getString(2));
			results.getString(3);
			user.setEE(results.getBoolean(4));
			user.setFirstName(results.getString(5));
			user.setLastName(results.getString(6));
			
			
		} catch(SQLException e){
			e.printStackTrace();
			user = null;
		}
		
		
		return user;
	}
	
	
	

}
