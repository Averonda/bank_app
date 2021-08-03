package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojo.PendingAccountData;

public class PendingAccountsDAO extends DAO {
	
	private Connection con;
	
	public void createPA(PendingAccountData pad) {
		try{
			con = DAO.getInstance().makeConnection();
			String query = "insert into pending_accts values (?, ?, ?)";
			
			PreparedStatement statement = con.prepareStatement(query);
			
			statement.setInt(1, pad.getUserID());
			statement.setDouble(2, pad.getInitialDeposit());
			statement.setBoolean(3, pad.isChecking());
			
			statement.executeUpdate();
			
			
			
		} catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	public List<PendingAccountData> getAllPending(){
		List<PendingAccountData> paccountList = new ArrayList<PendingAccountData>();
		try {
			// should be in try/finally block, but need to prevent con from returning null.
			con = DAO.getInstance().makeConnection();
			String query = "select * from pending_accts";

			PreparedStatement statement = con.prepareStatement(query);

			ResultSet results = statement.executeQuery();

			while (results.next()) {
				PendingAccountData pad = new PendingAccountData(results.getInt(1), results.getDouble(2), results.getBoolean(3));
				paccountList.add(pad);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			paccountList = null;

		}

		return paccountList;

	}
	
	public int getNextAccountNum() {
		
		try {
			con = DAO.getInstance().makeConnection();
			String query = "select MAX(account_number) from account.info";

			PreparedStatement statement = con.prepareStatement(query);

			ResultSet results = statement.executeQuery();
			
			return results.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
}
