import java.io.Serializable;

class Transaction implements Serializable{
	
	private String dateInfo;
	private int transactionType;
	private int transactionMoney; // 거래 금액 
	private int transactionBalance; // 거래 후 잔액 
	private int transactionNum; // 거래 번호

	private String transferTargetName; // 계좌이체 대상

	public Transaction(String dateInfo, int transactionType, int transactionMoney, int balance){
		this.dateInfo = dateInfo;
		this.transactionType = transactionType;
		this.transactionMoney = transactionMoney;
		this.transactionBalance = balance;
	}
	
	@Override
	public String toString() {		
		String printedTransactionType;
		
		if(transactionType == Constant.TRNASACTION_TYPE.DEPOSIT || transactionType == Constant.TRNASACTION_TYPE.WITHDRAW){
			if(transactionType == Constant.TRNASACTION_TYPE.DEPOSIT) {
				printedTransactionType = "입금";
			}else{
				printedTransactionType = "출금";
			}
			
			return " | "+"거래날짜: "+dateInfo +" | "+"거래종류: "+printedTransactionType+" | "+"거래액: "+transactionMoney+"(원)"+" | "+"잔고: "+transactionBalance+"(원)";
		}else{
			if(transactionType == Constant.TRNASACTION_TYPE.TRANSFER_DEPOSIT) {
				printedTransactionType = "계좌이체(입금)";
				return " | "+"거래날짜: "+dateInfo +" | "+"거래종류: "+printedTransactionType+" | "+"거래액: "+transactionMoney+"(원)"+" | "+"잔고: "+transactionBalance+"(원)"+" | "+"보낸이: "+transferTargetName;
			}else{
				printedTransactionType = "계좌이체(송금)";
				return " | "+"거래날짜: "+dateInfo +" | "+"거래종류: "+printedTransactionType+" | "+"거래액: "+transactionMoney+"(원)"+" | "+"잔고: "+transactionBalance+"(원)"+" | "+"받는이: "+transferTargetName;
			}
		}		
	}

	public Transaction(String dateInfo, int transactionType, int transactionMoney, int balance, String transferTargetName){
		this.dateInfo = dateInfo;
		this.transactionType = transactionType;
		this.transactionMoney = transactionMoney;
		this.transactionBalance = balance;
		this.transferTargetName = transferTargetName;
	}
	
	public void setDateInfo(String dateInfo) {
		this.dateInfo = dateInfo;
	}
	
	public String getDateInfo() {
		return dateInfo;
	}
	
	public void setTransactionMoney(int transactionMoney) {
		this.transactionMoney = transactionMoney;
	}
	
	public int getTransactionMoney() {
		return transactionMoney;
	}
	
	public void setTransactionType(int transactionType) {
		this.transactionType = transactionType;
	}
	
	public int getTransactionType() {
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
