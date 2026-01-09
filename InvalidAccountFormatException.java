package M4_Activity5;

public class InvalidAccountFormatException extends RuntimeException {

    String message;

    public InvalidAccountFormatException(String msg) {
        message = msg;
    }

    @Override
    public String getMessage() {
        return message;
    }
}