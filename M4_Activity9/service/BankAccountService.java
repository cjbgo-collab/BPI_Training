package M4_Activity9.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import M4_Activity9.exception.AccountNotFoundException;
import M4_Activity9.exception.InsufficientFundsException;
import M4_Activity9.exception.InvalidAccountException;
import M4_Activity9.exception.InvalidTransferException;
import M4_Activity9.model.BankAccount;


public class BankAccountService {

    // Logger for this class (shows logger name as model.BankAccount to match screenshots)
    private static final Logger log = LoggerFactory.getLogger(BankAccount.class);

    private static final double MINIMUM_INITIAL_BALANCE = 0.0;
    private List<BankAccount> accounts = new ArrayList<>();

    /**
     * Creates a new bank account.
     */
    public void createAccount(String accountNumber, String ownerName, double initialBalance)
            throws InvalidAccountException {

        log.info("Creating account for owner: {}", ownerName);

        // Validate account number
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            log.warn("Account creation failed: account number is null or empty");
            throw new InvalidAccountException("Account number cannot be null or empty");
        }

        // Validate owner name
        if (ownerName == null || ownerName.trim().isEmpty()) {
            log.warn("Account creation failed: owner name is null or empty");
            throw new InvalidAccountException("Owner name cannot be null or empty");
        }

        // Validate initial balance
        if (initialBalance < MINIMUM_INITIAL_BALANCE) {
            log.warn("Account creation failed: invalid initial balance {}", initialBalance);
            throw new InvalidAccountException(
                    String.format("Minimum Balance: %.2f, Received Balance: %.2f",
                            MINIMUM_INITIAL_BALANCE, initialBalance));
        }

        // Check for duplicate account
        if (accountExists(accountNumber)) {
            log.warn("Account creation failed: account {} already exists", accountNumber);
            throw new InvalidAccountException("Account number already exists: " + accountNumber);
        }

        // Create account
        try {
            BankAccount account = new BankAccount(accountNumber, ownerName, initialBalance);
            accounts.add(account);
            log.info("Account {} created successfully for {}", accountNumber, ownerName);
        } catch (IllegalArgumentException e) {
            log.error("Failed to create account due to invalid arguments", e);
            throw new InvalidAccountException("Account creation failed", e);
        }
    }

    /**
     * Transfers money between accounts.
     */
    public void transfer(String fromAccountNumber, String toAccountNumber, double amount)
            throws AccountNotFoundException, InsufficientFundsException, InvalidTransferException {

        log.info("Transfer request: {} -> {}, amount: {}", fromAccountNumber, toAccountNumber, amount);

        // Validate amount
        if (amount <= 0) {
            log.warn("Transfer failed: invalid amount {}", amount);
            throw new InvalidTransferException(
                    String.format("Transfer amount must be positive. Received: %.2f", amount));
        }

        // Validate accounts are different
        if (fromAccountNumber.equals(toAccountNumber)) {
            log.warn("Transfer failed: same source and destination account {}", fromAccountNumber);
            throw new InvalidTransferException("Cannot transfer from account " + fromAccountNumber + " to itself");
        }

        // Find source account
        BankAccount fromAccount = findAccountByNumber(fromAccountNumber);
        if (fromAccount == null) {
            log.warn("Transfer failed: source account {} not found", fromAccountNumber);
            throw new AccountNotFoundException("Source account not found: " + fromAccountNumber);
        }

        // Find destination account
        BankAccount toAccount = findAccountByNumber(toAccountNumber);
        if (toAccount == null) {
            log.warn("Transfer failed: destination account {} not found", toAccountNumber);
            throw new AccountNotFoundException("Destination account not found: " + toAccountNumber);
        }

        // Check sufficient funds
        if (fromAccount.getBalance() < amount) {
            log.warn("Transfer failed: insufficient funds in account {}. Balance: {}, Required: {}",
                    fromAccountNumber, fromAccount.getBalance(), amount);
            throw new InsufficientFundsException(
                    String.format("Current Balance: %.2f, Required Amount: %.2f",
                            fromAccount.getBalance(), amount));
        }

        // Perform transfer
        try {
            fromAccount.withdraw(amount);
            toAccount.deposit(amount);
            log.info("Transfer successful: {} -> {}, amount: {}", fromAccountNumber, toAccountNumber, amount);
        } catch (Exception e) {
            log.error("Transfer failed due to unexpected error", e);
            throw new InvalidTransferException("Transfer failed", e);
        }
    }

    /**
     * Finds account by account number.
     */
    private BankAccount findAccountByNumber(String accountNumber) {
        if (accountNumber == null) {
            return null;
        }

        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                log.debug("Account found: {}", accountNumber);
                return account;
            }
        }

        // DEBUG if account not found
        log.debug("Account not found: {}", accountNumber);
        return null;
    }

    private boolean accountExists(String accountNumber) {
        return findAccountByNumber(accountNumber) != null;
    }

    public List<BankAccount> getAllAccounts() {
        log.debug("Retrieving all accounts, count: {}", accounts.size());
        return new ArrayList<>(accounts);
    }
}
