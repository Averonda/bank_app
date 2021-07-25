package bank_app;

public class Menu {
	private String userInput;
	
	public void initialLogin() {
		boolean succsessfulLogin = false;
		do {
			System.out.println("Please login with your username and password:\nUsername:");
			userInput = InputScanner.getInput();
			System.out.println("Password:");
			userInput += InputScanner.getInput();
			
			
		} while (succsessfulLogin == false);
	}

	public void displayMenu() {
	}

}
