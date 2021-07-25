package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DAO {

	public static boolean verifyConnection(String DB, String userName, String Password) {
		try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
				"Frontier9")){
					return true;
				}catch (SQLException e) {
					// TODO: create logging
					return false;
				}
	} 
	
	public abstract Connection makeConnection(String DB, String userName, String password);
	
	public abstract ResultSet getDBData(String DB, String userName, String password, String tableName, String... columns);
	
	public abstract void setDBData(String DB, String userName, String password, String tableName, String... columns);
	
	
	
}
//{	
//		try (Connection conn = DriverManager.getConnection("jdbc:postgresql:/localhost:5432/postgres", "postgres",
//				"Frontier9")){ // "Try with Resources" block, will auto close so no need for finally block
//
//				// step 2 get statement object
//				Statement stmt = conn.createStatement();
//
//				// get resultset object
//				ResultSet rs = stmt.executeQuery("SELECT lastname FROM employee"); {
//
//			// will iterate through resultSet, rs, gathering String objects from name field
//			// in DB and outputting them in the console
//			while (rs.next()) {
//				String name = rs.getString("lastname");
//				System.out.println(name);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//}
