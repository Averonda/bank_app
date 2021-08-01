package dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Utility.Bank_AppConstants;

public class DAO {
	
	private static final Logger logger = LogManager.getLogger(DAO.class);

	private static DAO _instance = null;
	private Connection conn = null;

	protected DAO() {

	}

	public Connection makeConnection() throws SQLException {
		if (this.conn == null) {

			String configFilePath = System.getProperty(Bank_AppConstants.CONFIG_FILE);

			try (FileInputStream fis = new FileInputStream(configFilePath)) {
				
				logger.info("Config file path: "+configFilePath);

				Properties props = new Properties();
				props.load(fis);

				this.conn = DriverManager.getConnection(props.getProperty(Bank_AppConstants.DB_URL),
						props.getProperty(Bank_AppConstants.DB_USER), props.getProperty(Bank_AppConstants.DB_PASS));

			} catch (Exception e) {
				logger.warn("failed to make db connection");
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
