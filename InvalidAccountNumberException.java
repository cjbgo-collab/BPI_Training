package M4_Activity5;

public class InvalidAccountNumberException extends Exception {

    String message;

    public InvalidAccountNumberException(String msg) {
        message = msg;
    }

    @Override
    public String getMessage() {
        return message;
    }
}