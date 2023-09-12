import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalance_initialDefault() {
        var bankAccount = new BankAccount();

        assertEquals(0, bankAccount.getBalance());
    }

    @Test
    void getBalance_initialSpecified() {
        var bankAccount = new BankAccount(10);

        assertEquals(10, bankAccount.getBalance());
    }

    @Test
    void getHistory_initiallyEmpty() {
        var bankAccount = new BankAccount();
        var transactionHistory = bankAccount.getTransactionHistory();

        assertEquals(0, transactionHistory.size());
    }

    @Test
    void deposit() {
        var bankAccount = new BankAccount(10);
        bankAccount.deposit(5.0);

        assertEquals(15.0, bankAccount.getBalance(), 1e-9);

        var transactionHistory = bankAccount.getTransactionHistory();
        assertEquals(1, transactionHistory.size());
        var transaction = transactionHistory.getTransaction(0);
        assertEquals(TransactionType.DEPOSIT, transaction.transactionType());
        assertEquals(5.0, transaction.amount());
    }

    @Test
    void withdraw() {
        var bankAccount = new BankAccount(10);
        bankAccount.withdraw(5);

        assertEquals(5.0, bankAccount.getBalance(), 1e-9);

        var transactionHistory = bankAccount.getTransactionHistory();
        assertEquals(1, transactionHistory.size());
        var transaction = transactionHistory.getTransaction(0);
        assertEquals(TransactionType.WITHDRAW, transaction.transactionType());
        assertEquals(5.0, transaction.amount());
    }

    @Test
    void transferTo() {
        var sourceBankAccount = new BankAccount(10);
        var destinationBankAccount = new BankAccount(20);
        sourceBankAccount.transferTo(destinationBankAccount, 5);

        assertEquals(5.0, sourceBankAccount.getBalance(), 1e-9);
        assertEquals(25.0, destinationBankAccount.getBalance(), 1e-9);

        var sourceTransactionHistory = sourceBankAccount.getTransactionHistory();
        assertEquals(1, sourceTransactionHistory.size());
        var sourceTransaction = sourceTransactionHistory.getTransaction(0);
        assertEquals(TransactionType.TRANSFER_FROM, sourceTransaction.transactionType());
        assertEquals(5.0, sourceTransaction.amount());

        var destinationTransactionHistory = destinationBankAccount.getTransactionHistory();
        assertEquals(1, destinationTransactionHistory.size());
        var destinationTransaction = destinationTransactionHistory.getTransaction(0);
        assertEquals(TransactionType.TRANSFER_TO, destinationTransaction.transactionType());
        assertEquals(5.0, destinationTransaction.amount());
    }
}