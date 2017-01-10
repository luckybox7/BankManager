import java.util.Scanner;

class Client {
	
	private String name; // 이름 
	private String address; // 주소 
	private String phoneNum; // 핸드폰번호 
	private String credit; // 처음 계좌 생성 시 8등급
	private AccountManager accountManager;
	
	public Client() {
		
	}
	
	public Client(String name, String address, String phoneNum, String credit) {
		this.name = name;
		this.address = address;
		this.phoneNum = phoneNum;
		this.credit = credit;
		
		accountManager = new AccountManager();
	}
	
	public void setAccountCnt(int accountCnt){
		accountManager.setAccountCnt(accountCnt);
	}
	
	public int getAccountCnt(){
		return accountManager.getAccountCnt();
	}

	public void setAccount(Account account){
		accountManager.setAccount(account);
	}
	
	public Account getAccount(int index){
		return accountManager.getAccount(index);
	}
	
	public void setAccountArray(Account[] accountArr){
		accountManager.setAccountArray(accountArr);
	}
	
	public Account[] getAccountArray(){
		return accountManager.getAccountArray();
	}

	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setAddress(String address){
		this.address = address;
	}
	
	public String getAddress(){
		return address;
	}
	
	public void setPhoneNum(String phoneNum){
		this.phoneNum = phoneNum;
	}
	
	public String getPhoneNum(){
		return phoneNum;
	}
	
	public void setCredit(String credit) {
		this.credit = credit;
	}
	
	public String getCredit() {
		return credit;
	}
	
	public void showClientInfo() {
		System.out.println("이름: " + name +" | "+ "주소: " + address +" | "+ "전화번호: " + phoneNum +" | "+ "신용등급	: " + credit);
	}
}
