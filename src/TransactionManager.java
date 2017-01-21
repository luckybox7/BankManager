import java.util.ArrayList;

class TransactionManager {
	
	private ArrayList<Transaction> transactionList;
	
	public TransactionManager() {
		transactionList = new ArrayList<Transaction>();
	}
	
	public void setTransactions(Transaction transactions) {
		transactionList.add(transactions);
	}
	
	public Transaction getTransactions(int index) {
		return transactionList.get(index);
	}
	
	public ArrayList<Transaction> getTransactionList() {
		return transactionList;
	}
}
