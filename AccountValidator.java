package M4_Activity4;

public class AccountValidator {


    public static void validateAccountNumber(String accountNumber) {
        // Rule 1: null should throw NullPointerException
        if (accountNumber == null) {
            throw new NullPointerException("Cannot be null");
        }

        // Rule 2: length must be exactly 10
        if (accountNumber.length() != 10) {
            // Use IllegalArgumentException for invalid length
            throw new IllegalArgumentException("Must be 10 digits");
        }

    }
}