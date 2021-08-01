package bank_app;

import java.sql.SQLException;

public class Main {
	public static void main(String[] args) throws SQLException {
		
		Menu menu = new Menu();
		
		menu.initialLogin();
		menu.displayMenu();
	}
}
