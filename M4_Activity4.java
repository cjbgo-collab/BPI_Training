package M4_Activity4;

public class M4_Activity4 {

	  public static void main(String[] args) {
	        // Sample inputs
	        String[] testInputs = {
	            "1234567890",   // valid
	            "123",           // short
	            null            // null input
	        };

	        for (String input : testInputs) {
	     
	            try {
	               	AccountValidator.validateAccountNumber(input);
	            	System.out.print("Valid account: " + input);
	            	
	            } catch (NullPointerException npe) {
	                System.out.print("Error: " + npe.getMessage());
	            } catch (IllegalArgumentException iae) {
	                System.out.print("Error: " + iae.getMessage());
	            }
	            System.out.println();
	        }
	    }
	}