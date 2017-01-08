
class Account { //자유입출금 
	
	protected String accountNum; // 계좌번호 
	protected int balance; // 잔액
	protected Transactions[] trans = new Transactions[100]; // 거래내역
	
	public Account(){
		
	}
	
	public Account(String accountNum, int balance){
		this.accountNum = accountNum;
		this.balance = balance;
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
	
	public void deposit(int money){
		// 계좌선택 필요
		balance += money;
	}
	
	public void withdraw(int money){
		balance -= money;
	}
}

class CheckAccount extends Account{
	
	public CheckAccount(String accountNum, int balance){
		super(accountNum, balance);
	}
}

class MinusAccount extends Account{ // 마이너스 계좌 

	protected String creditLimit; // 대출한도 
	 
	public MinusAccount(String accountNum, int balance, String creditLimit){
		super(accountNum, balance);
		this.creditLimit = creditLimit;
	}
}