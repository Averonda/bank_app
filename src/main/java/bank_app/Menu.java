package bank_app;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.AccountDAO;
import dao.LedgerDAO;
import dao.PendingAccountsDAO;
import dao.UserDAO;
import pojo.AccountData;
import pojo.LedgerData;
import pojo.PendingAccountData;
import pojo.UserData;

public class Menu {
	private String userName, password;
	UserDAO user = new UserDAO();
	UserData userData = new UserData();
	AccountDAO accountDAO = new AccountDAO();
	List<AccountData> accounts = new ArrayList<AccountData>();
	LedgerDAO ledgerDAO = new LedgerDAO();
	List<LedgerData> ledger = new ArrayList<LedgerData>();

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
						+ "2. Make a Deposit\n" + "3. Make a Withdrawl\n" + "4. Make a Transfer to an Account\n"
						+ "5. Approve pending Transfers\n" + "6. Apply for a New Account\n" + "0. Quit");
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
					makeDeposit(userData);
					break;
				case 3:
					makeWithdrawl(userData);
					break;
				case 4:
					makeTransfer(userData);
					break;
				case 5:
					approveTransfer(userData);
					break;
				case 6:
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

	private void viewAccounts(UserData data) {

//		Scanner scan = new Scanner(System.in);
		scan.useDelimiter(System.lineSeparator());

		displayAccounts(data);

		systemPause();
	}

	private void makeWithdrawl(UserData data) {

		changeBalance("withdrawl", data);
	}

	private void makeDeposit(UserData data) {

		changeBalance("deposit", data);
	}

	private void makeTransfer(UserData data) {

		String answer;
		boolean exit = false;
		scan.useDelimiter(System.lineSeparator());
		do {
			System.out.println(
					"Is this an External Transfer? (Y/N) \nYou will need the routing and account number of the External Account");
			answer = scan.next();
			answer = answer.toLowerCase();
			if (answer.contains("y")) {
				AccountData tempAccount = new AccountData();
				int debitAccount;
				double amtTransfered;
				System.out.println("Please enter the Routing Number of the Account you wish to transfer to:");
				answer = scan.next();
				try {
					tempAccount.setRoutingNum(Integer.parseInt(answer));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					System.out.println("Please enter a valid number!");
					break;
				}
//				tempAccount.setRoutingNum(scan.nextInt());
				System.out.println("Please enter the Account Number of the Account your wish to transfer to:");
				answer = scan.next();
				try {
					tempAccount.setAccountNum(Integer.parseInt(answer));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					System.out.println("Please enter a valid number!");
					break;
				}
				displayAccounts(data);
				System.out.println("Please enter the Account Number of the Account you wish to transfer from:");
//				debitAccount = scan.nextInt();
				answer = scan.next();
				try {
					debitAccount = Integer.parseInt(answer);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					System.out.println("Please enter a valid number!");
					break;
				}
				System.out.println("Please enter the Amount to transfer:");
//				amtTransfered = scan.nextDouble();
				answer = scan.next();
				try {
					amtTransfered = Double.parseDouble(answer);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					System.out.println("Please enter a valid number!");
					break;
				}
				System.out.println(amtTransfered + " will be transfered from account: " + debitAccount + " to \n"
						+ "external account: " + tempAccount.getRoutingNum() + " " + tempAccount.getAccountNum()
						+ "\nIs this correct? (Y/N)");
				answer = scan.next();
				answer = answer.toLowerCase();
				if (answer.contains("y")) {
					AccountData tempAct = new AccountData();
					tempAct = accountDAO.getSingleAccount(debitAccount, data);
					tempAct.setBalance(tempAct.getBalance() - amtTransfered);
					accountDAO.updateAccountBalance(tempAct);
					LedgerData ledger = new LedgerData(tempAccount, tempAct, data, amtTransfered);
					ledger.setExternalTransfer(true);
					ledger.setCustomerApproved(true);
					ledgerDAO.updateLedger(ledger);
					exit = true;
					systemPause();
				} else {
					exit = false;
				}
			} else {
				AccountData tempAccount = new AccountData();
				int debitAccount;
				double amtTransfered;
				displayAccounts(data);
				System.out.println("Please enter the Account Number of the Account your wish to transfer to:");
//				tempAccount.setAccountNum(scan.nextInt());
				answer = scan.next();
				try {
					tempAccount.setAccountNum(Integer.parseInt(answer));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					System.out.println("Please enter a valid number!");
					break;
				}
				System.out.println("Please enter the Account Number of the Account you wish to transfer from:");
//				debitAccount = scan.nextInt();
				answer = scan.next();
				try {
					debitAccount = Integer.parseInt(answer);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					System.out.println("Please enter a valid number!");
					break;
				}
				System.out.println("Please enter the Amount to transfer:");
//				amtTransfered = scan.nextDouble();
				answer = scan.next();
				try {
					amtTransfered = Double.parseDouble(answer);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					System.out.println("Please enter a valid number!");
					break;
				}
				System.out.println(amtTransfered + " will be transfered from account: " + debitAccount + " to \n"
						+ "internal account: " + tempAccount.getAccountNum() + "\nIs this correct? (Y/N)");
				answer = scan.next();
				answer = answer.toLowerCase();
				if (answer.contains("y")) {
					AccountData tempAct = new AccountData();
					tempAct = accountDAO.getSingleAccount(debitAccount, data);
					tempAccount = accountDAO.getSingleAccount(tempAccount.getAccountNum(), data);
					tempAct.setBalance(tempAct.getBalance() - amtTransfered);
					tempAccount.setBalance(tempAccount.getBalance() + amtTransfered);
					LedgerData ledger = new LedgerData(tempAccount, tempAct, data, amtTransfered);
					ledger.setExternalTransfer(false);
					ledger.setCustomerApproved(true);
					accountDAO.updateAccountBalance(tempAct);
					accountDAO.updateAccountBalance(tempAccount);
					ledgerDAO.updateLedger(ledger);
					exit = true;
					System.out.println("Transaction success!");
					displayAccounts(data);
					systemPause();

				}
			}

		} while (exit == false);

	}

	private void approveTransfer(UserData data) {

		int choice;
		String input;
		scan.useDelimiter(System.lineSeparator());
		ledger = ledgerDAO.getLedgerDataForCustApproval(data);
		if (ledger.size() == 0) {
			System.out.println("You have no pending approvals, returning to the main menu!");
		} else {
			displayLedger(ledger, data);
			System.out.println("Which transaction number would you like to approve?");
			input = scan.next();
			try {
				choice = Integer.parseInt(input);
				LedgerData tempLedger = new LedgerData();
				ledgerDAO.setLedgerCustApproved(choice, tempLedger.getLedgerRow(choice, ledger));
				AccountData tempAcct = accountDAO
						.getSingleAccount(tempLedger.getLedgerRow(choice, ledger).getAccountTo(), data);
				tempAcct.setBalance(tempAcct.getBalance() + tempLedger.getLedgerRow(choice, ledger).getAmtTransfered());
				accountDAO.updateAccountBalance(tempAcct);
				System.out.println("Transfer Approved!");
				displayAccounts(data);
				systemPause();

			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Transaction not found, please try again!");
			}
		}
	}

	private void createAccount(UserData data) {
		// TODO Auto-generated method stub
		String input;
		double amount = 0.0;
		PendingAccountsDAO padDAO = new PendingAccountsDAO();
		scan.useDelimiter(System.lineSeparator());

		System.out.println("What type of account would you like to create, Checking or Savings?");
		input = scan.next();
		System.out.println("How much would you like to deposit initially?");
		try {
			amount = scan.nextDouble();
		} catch (Exception e) {
			System.out.println("Please enter a valid amount!");
		}
		input.toLowerCase();
		if (input.contains("c")) {
			PendingAccountData pad = new PendingAccountData(data.getUserID(), amount, true);
			padDAO.createPA(pad);
		} else {
			PendingAccountData pad = new PendingAccountData(data.getUserID(), amount, false);
			padDAO.createPA(pad);
		}

		System.out.println("Your request has been submitted for review! Returning to the main menu.");
	}

	private void viewCustomerAccounts() {
		// TODO Auto-generated method stub
		displayAllAccounts();
		systemPause();
	}

	private void approveAccounts() {
		boolean quit = false;
		int i = 1;
		int input;
		PendingAccountsDAO padDAO = new PendingAccountsDAO();
		// TODO Auto-generated method stub
		do {
			List<PendingAccountData> padList = new ArrayList<PendingAccountData>(padDAO.getAllPending());
			if (padList.isEmpty()) {
				System.out.println("There aren't any pending accounts! Returning to the main menu.");
				quit = true;
			} else {

				System.out.println("Here is the list of pending approvals:");
				for (PendingAccountData pendingAccountData : padList) {
					System.out.println(i + ". " + "User ID: " + pendingAccountData.getUserID() + " | "
							+ "Initial Deposit: " + pendingAccountData.getInitialDeposit() + " | "
							+ "Checking Account: " + pendingAccountData.isChecking());
					i++;
				}

				System.out.println("Enter the number for the account you would like to approve: ");
				try {
					input = scan.nextInt();
					if (padList.get(input).isChecking()) {
						AccountData newAcct = new AccountData(padList.get(input));
						newAcct.setAccountNum(padDAO.getNextAccountNum() + 1);
						newAcct.setRoutingNum(100000001);
						newAcct.setSavings(false);
						LedgerData tempLedger = new LedgerData(newAcct);
						ledgerDAO.updateLedger(tempLedger);

					}else {
						AccountData newAcct = new AccountData(padList.get(input));
						newAcct.setAccountNum(padDAO.getNextAccountNum() + 1);
						newAcct.setRoutingNum(100000001);
						newAcct.setChecking(false);
						LedgerData tempLedger = new LedgerData(newAcct);
						ledgerDAO.updateLedger(tempLedger);
					}
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Please enter a valid selection!");
				}
			}
		} while (quit == false);
	}

	private void viewLedger() {
		// TODO Auto-generated method stub
		displayLedger(ledgerDAO.getFullLedger(), userData);
		systemPause();
	}

	private void displayLedger(List<LedgerData> ledgerList, UserData user) {

		System.out.println("Transactions: ");
		if (user.isEE() == true) {
			for (LedgerData ld : ledgerList) {
				System.out.println("Transaction ID: " + ld.getTransactionID() + " | " + "User ID: " + ld.getUserID()
						+ " | " + "Origin Routing Num: " + ld.getRoutingFrom() + " | " + "Origin Account Num: "
						+ ld.getAccountFrom() + "\n" + "Destination Routing Num: " + ld.getRoutingTo() + " | "
						+ "Destination Account Num: " + ld.getAccountTo() + " | " + "Ammount Transfered: "
						+ ld.getAmtTransfered() + "\n" + "External Transfer: " + ld.isExternalTransfer() + " | "
						+ "New Account: " + ld.isNewAccount() + " | " + "Cust Approved: " + ld.isCustomerApproved()
						+ " | " + "Time: " + ld.getTime()+"\n");
			}
		} else {
			for (LedgerData ld : ledgerList) {
				System.out.println("Transaction ID: " + ld.getTransactionID() + " | " + "Origin Routing Num: "
						+ ld.getRoutingFrom() + " | " + "Origin Account Num: " + ld.getAccountFrom() + "\n"
						+ "Destination Routing Num: " + ld.getRoutingTo() + " | " + "Destination Account Num: "
						+ ld.getAccountTo() + " | " + "Ammount Transfered: " + ld.getAmtTransfered() + "\n"
						+ "External Transfer: " + ld.isExternalTransfer() + " | " + "Cust Approved: "
						+ ld.isCustomerApproved() + " | " + "Time: " + ld.getTime()+"\n");
			}
		}

	}

	private void displayAccounts(UserData data) {

		accounts = accountDAO.getAccounts(data);
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
	}

	private void displayAllAccounts() {

		accounts = accountDAO.getAllAccounts();
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
	}

	private void systemPause() {
		System.out.println("\nPress Enter or Return to return to the menu.");
		if (scan.next().toLowerCase() == "y") {

		}
	}

	private void changeBalance(String type, UserData data) {

		scan.useDelimiter(System.lineSeparator());
		int changeAccount;
		double changeAmt;
		String temp;
		accounts = accountDAO.getAccounts(data);
		displayAccounts(data);
		boolean quit = false;

		do {
			System.out.println(
					"Which account would you like to access? \n(Please enter an account number or none to return to the menu)");
			temp = scan.next();
			try {
				changeAccount = Integer.parseInt(temp);
				try {
					for (AccountData accountData : accounts) {
						if (accountData.getAccountNum() == changeAccount) {
							System.out.println("Enter the amount to " + type + ":");
							try {
								changeAmt = scan.nextDouble();
								if (type == "withdrawl") {
									changeAmt = 0.0 - changeAmt;
								}
								if (changeAmt <= 0.0 && type == "deposit") {
									System.out.println("Please enter a valid amount to " + type + "!");
								} else if (changeAmt >= 0.0 && type == "withdrawl") {
									System.out.println("Please enter a valid amount to " + type + "!");
								} else {
									accountData.setBalance(changeAmt + accountData.getBalance());
									accountDAO.updateAccountBalance(accountData);
									System.out.println("Success! Your current balance in " + accountData.getAccountNum()
											+ " is $" + accountData.getBalance());
									break;

								}
							} catch (Exception e) {
								// TODO insert logger
								System.out.println("Please enter a valid amount to " + type + "!");
								e.printStackTrace();
							}
						} else {
							System.out.println("Please enter a valid account number!");
						}
					}
				} catch (Exception e) {
//			TODO: insert logger
					quit = true;
					System.out.println("Returning to the menu, something went wrong!");
				}
			} catch (Exception e) {
				quit = true;
				System.out.println("Returning to the menu!");
			}
		} while (quit == false);
	}

}
