
class Transactions {
	
	private String dateInfo;
	private String transactionType; // 거래 종류 
	private int transactionMoney; // 거래 금액 
	private int transactionBalance; // 거래 후 잔액 
	private int transactionNum; // 거래 번호

	private String transferTargetName; // 계좌이체 대상

	public Transactions(String dateInfo, String transactionType, int transactionMoney, int balance){
		this.dateInfo = dateInfo;
		this.transactionType = transactionType;
		this.transactionMoney = transactionMoney;
		this.transactionBalance = balance;
		this.transactionNum = 1;
		transactionNum++;
	}
	
	public Transactions(String dateInfo, String transactionType, int transactionMoney, int balance, String transferTargetName){
		this.dateInfo = dateInfo;
		this.transactionType = transactionType;
		this.transactionMoney = transactionMoney;
		this.transactionBalance = balance;
		this.transferTargetName = transferTargetName;
		
		this.transactionNum = 1;
		transactionNum++;
	}
	
	public void setDateInfo(String dateInfo) {
		this.dateInfo = dateInfo;
	}
	
	public String getDateInfo() {
		return dateInfo;
	}
	
//	public void setMonth(int month) {
//		this.month = month;
//	}
//	
//	public int getMonth() {
//		return month;
//	}
//	
//	public void setDay(int day) {
//		this.day = day;
//	}
//	
//	public int getDay() {
//		return day;
//	}
	
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
	
	public void setTransferName(String transferName) {
		this.transferTargetName = transferName;
	}
	
	public String getTransferName() {
		return transferTargetName;
	}
}
