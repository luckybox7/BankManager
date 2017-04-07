import java.io.Serializable;

class Account implements Serializable{ //자유입출금 
		
	protected String accountNum; // 계좌번호 
	protected int balance; // 잔액
	protected TransactionManager tManager;
	protected int accountType;
	
	@Override
	public String toString() {
		String printedAccountType;
		
		if(accountType == Constant.ACCOUNT_TYPE.CHECK_ACCOUNT) {
			printedAccountType = "자유입출금 계좌";
		}else {
			printedAccountType = "마이너스 계좌";
		}
		
		return "계좌종류: "+ printedAccountType + " | " + "계좌번호: "+ accountNum + " | " + "잔액: "+ balance;
	}

	public Account(){
		
	}
	
	public Account(String accountNum){
		this.accountNum = accountNum;
		this.balance = 0;
		
		tManager = new TransactionManager();
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

	public int getAccountType() {
		return accountType;
	}
}

class CheckAccount extends Account{
	
	public CheckAccount(String accountNum){
		super(accountNum);
		accountType = Constant.ACCOUNT_TYPE.CHECK_ACCOUNT;
	}
	
	
}

class MinusAccount extends Account{ // 마이너스 계좌 

	protected int creditGrade; // 신용등급 
	protected int loanLimit; // 대출한도
	 
//	public MinusAccount(String accountNum, String creditLimit){
//		super(accountNum);
//		this.creditLimit = creditLimit;
//		accountType = Constant.ACCOUNT_TYPE.MINUS_ACCOUNT;
//	}
	
	public MinusAccount(String accountNum) {
		super(accountNum);
		accountType = Constant.ACCOUNT_TYPE.MINUS_ACCOUNT;
		
		this.creditGrade = Constant.CREDIT_GRADE.CREDIT_BASIC; // 신용등급 - 기본 8등급 
		
		//TODO
		// accountType은 this. 안해줘도 되는가? 
	}
	
	public void setCredit(int creditLimit) {
		this.creditGrade = creditLimit;
	}
	
	public int getCredit() {
		return creditGrade;
	}
	
}