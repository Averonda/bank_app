package bank_app;

import java.sql.SQLException;

import bank_app.Menu;
import pojo.UserData;

public class Main {
	public static void main(String[] args) throws SQLException {
		
		Menu menu = new Menu();
		
		menu.initialLogin();
		menu.displayMenu();
	}
}
