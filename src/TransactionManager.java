import java.util.ArrayList;

class TransactionManager {
	
	private ArrayList<Transactions> transactionList;
	
	public TransactionManager() {
		transactionList = new ArrayList<Transactions>();
	}
	
	public void setTransactions(Transactions transactions) {
		transactionList.add(transactions);
	}
	
	public Transactions getTransactions(int index) {
		return transactionList.get(index);
	}
	
	public ArrayList<Transactions> getTransactionList() {
		return transactionList;
	}
}
