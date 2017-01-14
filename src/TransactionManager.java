
class TransactionManager {

	public final int TRANSACTION_MAX = 100; 
	private Transactions[] transactionList;
	private int transCnt;
	
	public TransactionManager() {
		transactionList = new Transactions[TRANSACTION_MAX];
		transCnt = 0;
	}
	
	public void setTransCnt(int transCnt) {
		this.transCnt = transCnt;
	}
	
	public int getTransCnt() {
		return transCnt;
	}
	
	public void setTransactions(Transactions transactions) {
		this.transactionList[transCnt] = transactions;
		transCnt++;
	}
	
	public Transactions getTransactions(int index) {
		return transactionList[index];
	}
}
