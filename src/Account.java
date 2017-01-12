
class Account { //자유입출금 
	
	BankManageHandler bankManageHandler = new BankManageHandler();
	BankManageIOHandler bankManageIOHandler = new BankManageIOHandler();
	
	public final int MAX_TRANSACTIONS = 100;
	protected String accountNum; // 계좌번호 
	protected int balance; // 잔액
	protected Transactions[] trans = new Transactions[MAX_TRANSACTIONS]; // 거래내역
	protected String accountType; // 계좌종류
	
	public Account(){
		
	}
	
	public Account(String accountNum){
		this.accountNum = accountNum;
		this.balance = 0;
	}
	
	public void setAccountNum(String accountNum){
		this.accountNum = accountNum;
	}
	
	public String getAccountNum(){
		return accountNum;
	}
	
	public void setBalance(int balance){
		this.balance = balance;
	}
	
	public int getBalance(){
		return balance;
	}
	
	public String getAccountType() {
		return accountType;
	}
}

class CheckAccount extends Account{
	
	public CheckAccount(String accountNum){
		super(accountNum);
		accountType = "자유입출금 계좌";
	}
}

class MinusAccount extends Account{ // 마이너스 계좌 

	protected String creditLimit; // 대출한도 
	 
	public MinusAccount(String accountNum, String creditLimit){
		super(accountNum);
		this.creditLimit = creditLimit;
		accountType = "마이너스 계좌";
	}
}