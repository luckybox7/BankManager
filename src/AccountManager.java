import java.util.ArrayList;

public class AccountManager {
	
//	public final int MAX_ACCOUNT = 100;
//	private Account[] accountArr; // 계좌 생성을 위한 배열 
//	private int accountCnt;
	
	private ArrayList<Account> accountList;
	
	public AccountManager() {
		accountList = new ArrayList<Account>();
		
//		accountArr = new Account[MAX_ACCOUNT];
//		accountCnt = 0;
	}

//	public void setAccountCnt(int accountCnt) {
//		this.accountCnt = accountCnt;
//	}
//	
//	public int getAccountCnt() {
//		return accountCnt;
//	}
	
	public void setAccount(Account account) {
		accountList.add(account);
		
//		accountArr[accountCnt] = account;
//		accountCnt++;
	}
	
	public Account getAccount(int index) {
		return accountList.get(index);
		
//		return accountArr[index];
	}
	
//	public void setAccountArray(Account[] accountArr) {
//		this.accountArr = accountArr;
//	}
	
	public void setAccountList(ArrayList<Account> list) {
		this.accountList = list;
	}
	
//	public Account[] getAccountArray() {
//		return accountArr;
//	}
	
	public ArrayList<Account> getAccountList() {
		return accountList;
	}
	
//	public void setClearAll(Account[] accountArr) {
//		this.accountArr = accountArr;
//		accountCnt = 0;	
//	}
	
	public void setClearAll() {
		this.accountList.clear();
	}
	
//	public void setSpecificAccount(int index) {
//		accountArr[index] = null;
//		accountCnt = accountCnt-1;
//	}
	
	public void setSpecificAccount(int index) {
		accountList.remove(index);
	}
	
//	public void setRearrangeAccount(int index) {
//		for(int i=index; i<accountCnt; i++) {
//			accountArr[i] = accountArr[i+1];
//		}
//	}

}
