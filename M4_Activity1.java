package M4_Activity1;

public class M4_Activity1 {

	public static void main(String[] args) {
		System.out.println("=== Bank Account Name Display ===");
		
		// Valid Account
		
		BankAccount.testCase("ACC-001");
		
		// Invalid Account
		BankAccount.testCase("ACC-999");
		
		System.out.println("\n=== Program completed successfully! ===");

	}

}
