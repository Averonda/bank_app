package bank_app;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.AccountDAO;
import dao.UserDAO;
import pojo.AccountData;
import pojo.UserData;

public class Menu {
	private String userName, password;
	UserDAO user = new UserDAO();
	UserData userData = new UserData();
	AccountDAO accountDAO = new AccountDAO();
	List<AccountData> accounts = new ArrayList<AccountData>();
	Scanner scan = new Scanner(System.in);

	public void initialLogin() throws SQLException {
		boolean succsessfulLogin = false;
		do {
			scan.useDelimiter(System.lineSeparator());
			System.out.println("Please login with your username and password:\nUsername:");
			userName = scan.next();
			// InputScanner.getInput();
			System.out.println("Password: ");
			password = scan.next();
//			if(password == null)
//				password = InputScanner.getInput();

			userData = user.signIn(userName, password);
			if (user != null)
				succsessfulLogin = true;

//			scan.close();
		} while (succsessfulLogin == false);

	}

	public void displayMenu() {
		int choice;
//		Scanner scan = new Scanner(System.in);
		scan.useDelimiter(System.lineSeparator());
//		try {
//			System.in.reset();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		if (userData.isEE() == false) {
			do {
				System.out.println("Welcome " + userData.getFirstName() + " " + userData.getLastName() + "\n"
						+ "Please make a selection from the following menu:\n" + "1. View Accounts\n"
						+ "2. Make a Withdrawl or Deposit\n" + "3. Make a Transfer to an External Account\n"
						+ "4. Apply for a New Account\n" + "0. Quit");
				// scan.skip(" ");
				// std::cin.ignore(1000, \n);
				// choice = stoi(std::cin.getln());
				if (scan.hasNext())
					choice = Integer.parseInt(scan.next());
				else
					choice = scan.nextInt();

				switch (choice) {
				case 1:
					viewAccounts(userData);
					break;
				case 2:
					makeDepoWith(userData);
					break;
				case 3:
					makeTransfer(userData);
					break;
				case 4:
					createAccount(userData);
					break;
				case 0:
					break;

				default:
					System.out.println("Please make a valid choice:");
					break;
				}
			} while (choice != 0);
		} else {
			do {
				System.out.println("Welcome " + userData.getFirstName() + " " + userData.getLastName() + "\n"
						+ "Please make a selection from the following menu:\n" + "1. View Customer Accounts\n"
						+ "2. Approve New Accounts\n" + "3. View the Transaction Record\n" + "0. Quit");

				choice = scan.nextInt();
				switch (choice) {
				case 1:
					viewCustomerAccounts();
					break;
				case 2:
					approveAccounts();
					break;
				case 3:
					viewLedger();
					break;
				case 0:
					break;

				default:
					System.out.println("Please make a valid choice:");
					break;
				}
			} while (choice != 0);
		}

		scan.close();

	}

	private void makeDepoWith(UserData data) {
		// TODO Auto-generated method stub
		// TODO make separate function displayAccounts()
		accounts = accountDAO.viewAccounts(data);
		int i=1;
		System.out.println("Your Accounts: ");
		for (AccountData accountData : accounts) {
			if (accountData.isChecking()) {
				System.out.println("Checking Account: \n"+i+". " + "Routing: " + accountData.getRoutingNum() + " | Account: "
						+ accountData.getAccountNum() + " | Balance: " + accountData.getBalance());
			} else {
				System.out.println("Savings Account: \n"+i+". " + "Routing: " + accountData.getRoutingNum() + " | Account: "
						+ accountData.getAccountNum() + " | Balance: " + accountData.getBalance());
			}
			i++;
		}
		System.out.println("Is this a deposit? Y/N");
		
	}

	private void viewAccounts(UserData data) {

//		Scanner scan = new Scanner(System.in);
		scan.useDelimiter(System.lineSeparator());

		// TODO make separate function displayAccounts()
		accounts = accountDAO.viewAccounts(data);
		System.out.println("Your Accounts: ");
		for (AccountData accountData : accounts) {
			if (accountData.isChecking()) {
				System.out.println("Checking Account: \n" + "Routing: " + accountData.getRoutingNum() + " | Account: "
						+ accountData.getAccountNum() + " | Balance: " + accountData.getBalance());
			} else {
				System.out.println("Savings Account: \n" + "Routing: " + accountData.getRoutingNum() + " | Account: "
						+ accountData.getAccountNum() + " | Balance: " + accountData.getBalance());
			}

		}
		System.out.println("\nWould you like to make a Deposit or Withdrawl? Y/N");
		if (scan.next().toLowerCase() == "y") {
			makeDepoWith(data);
		}

//		scan.close();

	}

	private void makeTransfer(UserData data) {
		// TODO Auto-generated method stub

	}

	private void createAccount(UserData data) {
		// TODO Auto-generated method stub

	}

	private void viewCustomerAccounts() {
		// TODO Auto-generated method stub

	}

	private void approveAccounts() {
		// TODO Auto-generated method stub

	}

	private void viewLedger() {
		// TODO Auto-generated method stub

	}

}
