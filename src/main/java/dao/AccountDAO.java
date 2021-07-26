package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojo.AccountData;
import pojo.UserData;

public class AccountDAO extends DAO {

	private Connection con;

	public List<AccountData> viewAccounts(UserData user) {
		List<AccountData> accountList = new ArrayList<AccountData>();
		try {
			// should be in try/finally block, but need to prevent con from returning null.
			con = DAO.get_instance().makeConnection();
			String query = "select * from account_info where user_id = ?";

			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, user.getUserID());

			ResultSet results = statement.executeQuery();

			while(results.next()) {
				AccountData ad = new AccountData(results.getInt(1), results.getInt(2), results.getInt(3), results.getInt(4),
						results.getBoolean(5),results.getBoolean(6), results.getDouble(7));
				accountList.add(ad);
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
			accountList = null;
			
		}

		return accountList;

	}
}
