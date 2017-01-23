import java.util.ArrayList;

public class AccountManager {
	
	private ArrayList<Account> accountList;
	
	public AccountManager() {
		accountList = new ArrayList<Account>();
	}
	
	public void insertAccount(Account account) {
		accountList.add(account);
		
	}
	
	public Account getAccount(int index) {
		return accountList.get(index);
		
	}
	
	public void setAccountList(ArrayList<Account> list) {
		this.accountList = list;
	}
	
	public ArrayList<Account> getAccountList() {
		return accountList;
	}
	
	public void clearAllAccount() {
		this.accountList.clear();
	}
	
	public void clearSpecificAccount(int index) {
		accountList.remove(index);
	}
}
