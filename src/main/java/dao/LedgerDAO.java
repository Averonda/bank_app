package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojo.LedgerData;
import pojo.UserData;

public class LedgerDAO extends DAO {

	private Connection con;
//TODO: create db
	// create search function (limit by date?)
	// implementation, allow bank ee to look at transaction logs

	public void updateLedger(LedgerData ledgerItem) {
		try {
			con = DAO.getInstance().makeConnection();
			PreparedStatement statement = con.prepareStatement("INSERT INTO transaction_ledger"
					+ "(user_id, routing_num_from, account_num_from, routing_num_to, account_num_to, external_transfer,"
					+ " is_new_account, is_customer_approved, amount_transfered, account_id)"
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
			statement.setInt(1, ledgerItem.getUserID());
			statement.setInt(2, ledgerItem.getRoutingFrom());
			statement.setInt(3, ledgerItem.getAccountFrom());
			statement.setInt(4, ledgerItem.getRoutingTo());
			statement.setInt(5, ledgerItem.getAccountTo());
			statement.setBoolean(6, ledgerItem.isExternalTransfer());
			statement.setBoolean(7, ledgerItem.isNewAccount());
			statement.setBoolean(8, ledgerItem.isCustomerApproved());
			statement.setDouble(9, ledgerItem.getAmtTransfered());
			statement.setInt(10, ledgerItem.getAccountID());

			statement.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public List<LedgerData> getFullLedger() {
		List<LedgerData> ledgerList = new ArrayList<LedgerData>();

		try {
			con = DAO.getInstance().makeConnection();
			PreparedStatement statement = con.prepareStatement(
					"select * from transaction_ledger");
			

			ResultSet results = statement.executeQuery();

			while (results.next()) {
				LedgerData ld = new LedgerData(results.getInt(1), results.getInt(2), results.getInt(3),
						results.getInt(4), results.getInt(5), results.getInt(6), results.getBoolean(7),
						results.getBoolean(8), results.getBoolean(9), results.getTimestamp(10), results.getDouble(11), results.getInt(12));
				ledgerList.add(ld);
			}
			return ledgerList;

		} catch (SQLException e) {
			// TODO log
			e.printStackTrace();
			return null;
		}

	}

	public List<LedgerData> getLedgerDataForCustApproval(UserData user) {
		List<LedgerData> ledgerList = new ArrayList<LedgerData>();

		try {
			con = DAO.getInstance().makeConnection();
			PreparedStatement statement = con.prepareStatement(
					"select * from transaction_ledger where user_id = ? and is_customer_approved = ?;");
			statement.setInt(1, user.getUserID());
			statement.setBoolean(2, false);

			ResultSet results = statement.executeQuery();

			while (results.next()) {
				LedgerData ld = new LedgerData(results.getInt(1), results.getInt(2), results.getInt(3),
						results.getInt(4), results.getInt(5), results.getInt(6), results.getBoolean(7),
						results.getBoolean(8), results.getBoolean(9), results.getTimestamp(10), results.getDouble(11), results.getInt(13));
				ledgerList.add(ld);
			}
			return ledgerList;

		} catch (SQLException e) {
			// TODO log
			e.printStackTrace();
			return null;
		}

	}
	
	public void setLedgerCustApproved(int selection, LedgerData ledger) {
		try {
			con = DAO.getInstance().makeConnection();
			PreparedStatement statement = con.prepareStatement(
					"update transaction_ledger set is_customer_approved = true where transaction_id = ?;");
			
			statement.setInt(1, selection);

			statement.execute();
			ledger.setCustomerApproved(true);
			updateLedger(ledger);


		} catch (SQLException e) {
			// TODO log
			e.printStackTrace();
		}
	}
	
	

}
