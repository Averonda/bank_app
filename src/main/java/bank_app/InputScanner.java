package bank_app;

import java.util.Scanner;

public class InputScanner {
	private static String input;
	
	static String getInput() {
		
		Scanner scan = new Scanner(System.in);
		input = scan.toString();
		scan.close();
		return input;
	}
	
}
