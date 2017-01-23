import java.util.ArrayList;

class TransactionManager {
	
	private ArrayList<Transaction> transactionList;
	
	public TransactionManager() {
		transactionList = new ArrayList<Transaction>();
	}
	
	public void insertTransaction(Transaction transactions) {
		transactionList.add(transactions);
	}
	
	public Transaction getTransaction(int index) {
		return transactionList.get(index);
	}
	
	public ArrayList<Transaction> getTransactionList() {
		return transactionList;
	}
}
