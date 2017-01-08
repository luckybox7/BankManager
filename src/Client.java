import java.util.Scanner;

class Client {
	
	private String name; // 이름 
	private String address; // 주소 
	private String phoneNum; // 핸드폰번호 
	private String credit; // 처음 계좌 생성 시 8등급
	private Account[] accountArr = new Account[100]; // 계좌 생성을 위한 배열 
	
	private int accountCnt=0;
	
	public Client() {
		
	}
	
	public Client(String name, String address, String phoneNum, String credit) {
		
		this.name = name;
		this.address = address;
		this.phoneNum = phoneNum;
		this.credit = credit;
	}
	
	public void setAccountCnt(int accountCnt){
		this.accountCnt = accountCnt;
	}
	
	public int getAccountCnt(){
		return accountCnt;
	}
	
	public void setAccountArray(Account[] accountArr){
		this.accountArr = accountArr;
	}
	
	public Account[] getAccountArray(){
		return accountArr;
	}
	
	public void setAccount(int index){
		this.accountArr[index] = accountArr[index];
	}
	
	public Account getAccount(int index){
		return accountArr[index];
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
	  

}
