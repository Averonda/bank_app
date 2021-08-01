package dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import Utility.Bank_AppConstants;

public class DAO {

	private static DAO _instance = null;
	private Connection conn = null;

	public static boolean verifyConnection(String DB, String userName, String Password) {
		try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
				"Frontier9")) {
			return true;
		} catch (SQLException e) {
			// TODO: create logging
			return false;
		}
	}

	protected DAO() {

	}

	public Connection makeConnection() throws SQLException {
		if (this.conn == null) {

			String configFilePath = System.getProperty(Bank_AppConstants.CONFIG_FILE);

			try (FileInputStream fis = new FileInputStream(configFilePath)) {

				Properties props = new Properties();
				props.load(fis);

				this.conn = DriverManager.getConnection(props.getProperty(Bank_AppConstants.DB_URL),
						props.getProperty(Bank_AppConstants.DB_USER), props.getProperty(Bank_AppConstants.DB_PASS));

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return this.conn;
	}

	public static DAO getInstance() {
		if (_instance == null)
			_instance = new DAO();
		return _instance;
	}

}
