import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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

	public void setAccount(Account account){
		accountManager.insertAccount(account);
	}
	
	public Account getAccount(int index){
		return accountManager.getAccount(index);
	}
	
	public void setAccountList(ArrayList<Account> list) {
		accountManager.setAccountList(list);
	}
	
	public ArrayList<Account> getAccountList() {
		return accountManager.getAccountList();
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
	
	public void clearAllAccount() {	
		System.out.println("모든 계좌를 삭제합니다");
		accountManager.clearAllAccount(); // ArrayList
		System.out.println("모든 계좌 삭제 완료");
	}
	
	public void clearSpecificAccount(int index) {
		accountManager.clearSpecificAccount(index); // List
	}
	
	public void showClientBasicInfo() {
		System.out.println(" ==== 계좌 정보를 출력합니다 ==== ");
		System.out.println("이름: " + name +" | "+ "주소: " + address +" | "+ "전화번호: " + phoneNum +" | "+ "신용등급: " + credit);
		System.out.println("----------------------------");
	}
	
	public static Comparator<Client> clientNameComparator = new Comparator<Client>() {

		@Override
		public int compare(Client c1, Client c2) {
			if(c1==null && c2==null){
				return 0;
			}else if(c1==null){
				return 1;
			}else if(c2==null){
				return -1;
			}
			// TODO Auto-generated method stub
			return c1.getName().compareTo(c2.getName());
		}
	
	};
	
}
