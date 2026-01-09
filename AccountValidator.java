package M4_Activity5;

public class AccountValidator {

    public static void validateAccountNumber(String accountNumber)
            throws InvalidAccountNumberException {

        if (accountNumber == null) {
            throw new NullPointerException("Account number cannot be null");
        }

        for (int i = 0; i < accountNumber.length(); i++) {
            char c = accountNumber.charAt(i);
            if (!Character.isDigit(c)) {
                throw new InvalidAccountFormatException(
                        "Account number must contain only digits");
            }
        }

        if (accountNumber.length() != 10) {
            throw new InvalidAccountNumberException(
                    "Account number must be exactly 10 digits");
        }

        System.out.println("Valid account number: " + accountNumber);
    }
}