package M4_Activity7;

@FunctionalInterface
public interface BankTestOperation {

	void execute() throws InvalidAmountException, InsufficientFundsException;
}