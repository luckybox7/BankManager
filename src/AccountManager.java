
public class AccountManager {
	
	public final int MAX_ACCOUNT = 100;
	private Account[] accountArr; // 계좌 생성을 위한 배열 
	private int accountCnt;
	
	public AccountManager() {
		accountArr = new Account[MAX_ACCOUNT];
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
	
	public void setClearAll(Account[] accountArr) {
		this.accountArr = accountArr;
		//this.accountArr = new Account[100];
		accountCnt = 0;	
	}
	
	public void setSpecificAccount(int index) {
		accountArr[index] = null;
		accountCnt = accountCnt-1;
	}
	
	public void setRearrangeAccount(int index) {
		for(int i=index; i<accountCnt; i++) {
			accountArr[i] = accountArr[i+1];
		}
	}

}
