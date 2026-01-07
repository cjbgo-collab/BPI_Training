package M4_Activity2;
import java.text.DecimalFormat;
public class ATMSystem {
	
	 private static final DecimalFormat df = new DecimalFormat("0.00");
	// Account balance
	static double[] accounts = {10000, 15000, 20000};
	
	// Method to process withdrawal
	
	public static void processWithdrawal(String accountIndex, String accountInput) {
				
		try {
			// Convert inputs
			int index = Integer.parseInt(accountIndex);
			double amount = Double.parseDouble(accountInput);
			
			// Get current balance
			double balance = accounts[index];
			
		System.out.println("Account=" + accountIndex + ", Amount=" +  accountInput);
		System.out.println("Current balance: ₱" + df.format(balance));
		
			// Check funds
		if(amount > balance) {
			System.out.println("Withdrawal: ₱" + df.format(amount));
			System.out.println("Insufficient fund! Cannot withraw ₱" + df.format(amount));
		}
		else {
			accounts[index] = balance - amount;
			System.out.println("Withdrawal: ₱" + df.format(amount));
			System.out.println("New balance: ₱" + df.format(accounts[index]));
			System.out.println("Withdrawal sucessful!");
		}
		}
		catch (NumberFormatException e) {
			System.out.println("Accounts=" + accountIndex + ", Amount=" + accountInput);
			System.out.println("Error: Invalid input!");
			System.out.println("Please enter valid numbers.");
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Accounts=" + accountIndex + ", Amount=" + accountInput);
			System.out.println("Error: Account not found!");
			System.out.println("Invalid account index.");
		}
		catch (Exception e) {
			System.out.println("Transaction failed.");
		}
	}
}