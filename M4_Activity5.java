package M4_Activity5;

public class M4_Activity5 {

    public static void main(String[] args) {

        System.out.println("=== Account Number Validation Test ====");

        test("Test 1: Valid account (1234567890)", "1234567890");
        test("Test 2: Too short (123)", "123");
        test("Test 3: Contains letters (12345ABCD9)", "12345ABCD9");
        test("Test 4: Contains space (1234 67890) ", "1234 67890");
        test("Test 5: Null value", null);
    }

    public static void test(String title, String value) {

        System.out.println("\n" + title);

        try {
            AccountValidator.validateAccountNumber(value);
        }
        catch (InvalidAccountNumberException e) {
            System.out.println("Warning: " + e.getMessage());
        }
        catch (InvalidAccountFormatException e) {
            System.out.println("Warning: " + e.getMessage());
        }
        catch (NullPointerException e) {
            System.out.println("Warning: " + e.getMessage());
        }
    }
}