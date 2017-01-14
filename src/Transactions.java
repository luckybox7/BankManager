
class Transactions {
	
	private int month; // 거래(월)
	private int day; // 거래(일)
	private String transactionType; // 거래 종류 
	private int transactionMoney; // 거래 금액 
	private int transactionBalance; // 거래 후 잔액 
	private int transactionNum; // 거래 번호


	public Transactions(int month, int day, String transactionType, int transactionMoney, int balance){
		this.month = month;
		this.day = day;
		this.transactionType = transactionType;
		this.transactionMoney = transactionMoney;
		this.transactionBalance = balance;
		this.transactionNum = 1;
		transactionNum++;
		
	}
	public void setMonth(int month) {
		this.month = month;
	}
	
	public int getMonth() {
		return month;
	}
	
	public void setDay(int day) {
		this.day = day;
	}
	
	public int getDay() {
		return day;
	}
	
	public void setTransactionMoney(int transactionMoney) {
		this.transactionMoney = transactionMoney;
	}
	
	public int getTransactionMoney() {
		return transactionMoney;
	}
	
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	
	public String getTransactionType() {
		return transactionType;
	}
	
	public void setTransactionBalance(int transactionBalance) {
		this.transactionBalance = transactionBalance;
	}
	
	public int getTransactionBalance() {
		return transactionBalance;
	}
	
	public void setTransactionNum(int transactionNum) {
		this.transactionNum = transactionNum;
	}
	
	public int getTransactionNum() {
		return transactionNum;
	}
	
}
