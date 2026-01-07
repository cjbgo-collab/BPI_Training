package M4_Activity1;

public class BankAccount {

	// Method to get account name safely
	public static String getAccountName(String accountNumber) {
		if ("ACC-001".equals(accountNumber)) {
			return "Juan Dela Cruz";
		}
		else if ("ACC-002".equals(accountNumber)){
			return "Maria Santos";
		}
		else {
			return null;
		}
			
	}
	
	public static void testCase(String accountNumber) {
		System.out.println("\nLooking up acoount: " + accountNumber);
		
		try {
			// Call method and assign result
			String name = getAccountName(accountNumber);
			
			// Convert to uppercase
			String upperName = name.toUpperCase();
			
			//Print account holder
			System.out.println("Account holder: " + upperName);
		}
		
		catch (NullPointerException e) {
			System.out.println("Error: Account not found!");
		}
	}
}