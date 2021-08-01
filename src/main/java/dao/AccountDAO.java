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

	public List<AccountData> getAccounts(UserData user) {
		List<AccountData> accountList = new ArrayList<AccountData>();
		try {
			// should be in try/finally block, but need to prevent con from returning null.
			con = DAO.getInstance().makeConnection();
			String query = "select * from account_info where user_id = ?";

			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, user.getUserID());

			ResultSet results = statement.executeQuery();

			while (results.next()) {
				AccountData ad = new AccountData(results.getInt(1), results.getInt(2), results.getInt(3),
						results.getInt(4), results.getBoolean(5), results.getBoolean(6), results.getDouble(7));
				accountList.add(ad);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			accountList = null;

		}

		return accountList;

	}

	public List<AccountData> getAllAccounts() {
		List<AccountData> accountList = new ArrayList<AccountData>();
		try {
			// should be in try/finally block, but need to prevent con from returning null.
			con = DAO.getInstance().makeConnection();
			String query = "select * from account_info";

			PreparedStatement statement = con.prepareStatement(query);

			ResultSet results = statement.executeQuery();

			while (results.next()) {
				AccountData ad = new AccountData(results.getInt(1), results.getInt(2), results.getInt(3),
						results.getInt(4), results.getBoolean(5), results.getBoolean(6), results.getDouble(7));
				accountList.add(ad);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			accountList = null;

		}

		return accountList;

	}

	public AccountData getSingleAccount(int accountNum, UserData user) {

		try {

			con = DAO.getInstance().makeConnection();

			String query = "select * from account_info where account_number = ? and user_id = ?";

			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, accountNum);
			statement.setInt(2, user.getUserID());

			ResultSet results = statement.executeQuery();

			results.next();
			AccountData ad = new AccountData(results.getInt(1), results.getInt(2), results.getInt(3), results.getInt(4),
					results.getBoolean(5), results.getBoolean(6), results.getDouble(7));

			return ad;
		} catch (Exception e) {
			return null;
		}
	}

	public void updateAccountBalance(AccountData account) {

		try {
			con = DAO.getInstance().makeConnection();
			PreparedStatement statement = con
					.prepareStatement("UPDATE public.account_info SET balance = ? WHERE account_id = ?");
			statement.setDouble(1, account.getBalance());
			statement.setInt(2, account.getAccountID());

			statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void createAccount(AccountData account) {
		try {
			con = DAO.getInstance().makeConnection();
			PreparedStatement statement = con.prepareStatement(
					"INSERT INTO public.account_info (user_id,routing_number,account_number,is_savings,is_checking,balance)"
							+ "VALUES (?,?,?,?,?,?)");
			statement.setInt(1, account.getUserID());
			statement.setInt(2, account.getRoutingNum());
			statement.setInt(3, account.getAccountNum());
			statement.setBoolean(4, account.isSavings());
			statement.setBoolean(5, account.isChecking());
			statement.setDouble(6, account.getBalance());
			

			statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
