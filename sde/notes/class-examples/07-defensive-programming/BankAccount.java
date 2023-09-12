public class BankAccount {
    private double balance;
    private TransactionHistory transactionHistory;

    public BankAccount() {
        this(0.0);
    }

    public BankAccount(double initialDeposit) {
        this.balance = initialDeposit;
        transactionHistory = new TransactionHistory();
    }

    public double getBalance() {
        return balance;
    }

    public TransactionHistory getTransactionHistory() {
        return transactionHistory;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.addTransaction(new Transaction(
                TransactionType.DEPOSIT, amount));
    }

    public void withdraw(double amount) {
        balance -= amount;
        transactionHistory.addTransaction(new Transaction(
                TransactionType.WITHDRAW, amount));
    }

    public void transferTo(BankAccount destination, double amount) {
        this.balance -= amount;
        this.transactionHistory.addTransaction(new Transaction(
                TransactionType.TRANSFER_FROM, amount));

        destination.balance += amount;
        destination.transactionHistory.addTransaction(new Transaction(
                TransactionType.TRANSFER_TO, amount));
    }
}
