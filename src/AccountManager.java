
public class AccountManager {
	
	private Account[] accountArr; // 계좌 생성을 위한 배열 
	private int accountCnt;
	
	public AccountManager() {
		accountArr = new Account[100];
		accountCnt = 0;
	}

	public void setAccountCnt(int accountCnt) {
		this.accountCnt = accountCnt;
	}
	
	public int getAccountCnt() {
		return accountCnt;
	}
	
	public void setAccount(Account account) {
		accountArr[accountCnt] = account;
		accountCnt++;
	}
	
	public Account getAccount(int index) {
		return accountArr[index];
	}
	
	public void setAccountArray(Account[] accountArr) {
		this.accountArr = accountArr;
	}
	
	public Account[] getAccountArray() {
		return accountArr;
	}
	

}
