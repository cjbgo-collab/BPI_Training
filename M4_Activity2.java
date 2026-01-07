package M4_Activity2;

public class M4_Activity2 {

	public static void main(String[] args) {
		
		System.out.println("=== ATM Withdrawal System ===");
		
	
		System.out.println("\n--- Test 1: Valid Withdrawal ---");
		ATMSystem.processWithdrawal("1", "5000");
		
		System.out.println("\n--- Test 2: Invalid Account Index ---");
		ATMSystem.processWithdrawal("abc", "5000");
		
		System.out.println("\n--- Test 3: Account Not Found ---");
		ATMSystem.processWithdrawal("10", "5000");
		
		System.out.println("\n--- Test 4: Insufficient Funds ---");
		ATMSystem.processWithdrawal("1", "20000");
		
		System.out.println("\n--- All tests completed! ---");

	}

}
