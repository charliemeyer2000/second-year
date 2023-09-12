import java.util.ArrayList;
import java.util.List;

public class TransactionHistory {
    private List<Transaction> transactions;

    public TransactionHistory() {
        transactions = new ArrayList<>();
    }

    public int size() {
        return transactions.size();
    }

    public Transaction getTransaction(int index) {
        return transactions.get(index);
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    @Override
    public String toString() {
        return transactions.toString();
    }
}
