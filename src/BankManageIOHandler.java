import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

class BankManageIOHandler {
	public void printInitMenu() {
		System.out.println("===== 원하는 업무를 선택해주세요    =====");
		System.out.println("1. 은행원 ");
		System.out.println("2. 고객 ");
		System.out.println("3. 나가기 ");
		System.out.println("==================================");
		System.out.print("선택>> ");
	}

	public void printTellerMenu() {
		System.out.println("===== 은행원 업무를 시작합니다 ====");
		System.out.println("1. 새로운 계좌 개설 ");
		System.out.println("2. 계좌 해약/취소 ");
		System.out.println("3. 고객 정보 출력 ");
		System.out.println("4. 나가기 ");
		System.out.println("=============================");
		System.out.print("선택>> ");
	}

	public void printClientMenu() {
		System.out.println("===== 원하는 서비스를 선택하세요 ====");
		System.out.println("1. 입금 ");
		System.out.println("2. 출금 ");
		System.out.println("3. 계좌 이체 ");
		System.out.println("4. 잔액조회 ");
		System.out.println("5. 거래내역 조회 ");
		System.out.println("6. 나가기 ");
		System.out.println("==============================");
		System.out.print("선택>> ");
	}
	
	public String typeName() {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("1.이름 입력 : ");
		String newName = sc.nextLine();

		return newName;
	}
	
	public int selectAccount() { // 계좌종류 선택 화면
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("어떤 종류의 계좌를 개설하시겠습니까??");
		System.out.println("1.자유입출금 계좌 ");
		System.out.println("2.마이너스 계좌");
		System.out.print("선택>> ");
		int choice = sc.nextInt();
		sc.nextLine();

		return choice;
	}
	
	public int selectDeleteType() {
		Scanner sc = new Scanner(System.in);
		System.out.println("삭제하고자 하는 유형을 선택하세요");
		System.out.println("1. 전체 계좌 삭제");
		System.out.println("2. 특정 계좌 삭제 (계좌번호 입력 필요)");
		System.out.print("선택 >> ");
		
		int selectResult = sc.nextInt();
		sc.nextLine();
		
		return selectResult;
	}
	
	public Client typeClientData(String name) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("신규 고객입니다. 하단에 추가 정보를 입력하세요.");
		System.out.print("2.주소 입력 : ");
		String newAdd = sc.nextLine();
		System.out.print("3.핸드폰 번호 입력 : ");
		String newPhoneNum = sc.nextLine();
		System.out.println("신용등급은 자동으로 만들어집니다");
		String newCredit = "8";

		Client tempClient = new Client(name, newAdd, newPhoneNum, newCredit);
		
		return tempClient;
	}
	
	public String selectAccountNum() {
		
		Scanner sc = new Scanner(System.in);
		String selectedAccountNum;
		
		System.out.println("계좌번호를 입력하세요");
		System.out.print("입력 >> ");
		selectedAccountNum = sc.nextLine();
		
		return selectedAccountNum;
	}
	
	public int selectPrintClientType() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("출력하고자 하는 유형을 선택하세요. ");
		System.out.println("1. 모든 고객 정보 출력");
		System.out.println("2. 특정 고객 정보 출력");
		System.out.print("선택 >> ");
		
		int selectedPrintType= sc.nextInt();
		sc.nextLine();
		
		return selectedPrintType;
	}
	
	public int selectPrintAccountInfoType() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("출력하고자 하는 유형을 선택하세요. ");
		System.out.println("1. 모든 계좌 정보 출력");
		System.out.println("2. 특정 계좌 정보 출력");
		System.out.print("선택 >> ");
		
		int selectedPrintType= sc.nextInt();
		sc.nextLine();
		
		return selectedPrintType;
	}
	
	public int typeMoney() {
		Scanner sc = new Scanner(System.in);
		System.out.print("입력 >> ");
		int money = sc.nextInt();
		sc.nextLine();
		
		return money;
	}
	
	public int checkProcess() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("계좌이체를 계속해서 진행합니다.");
		System.out.println("1. 확인");
		System.out.println("2. 취소");
		System.out.print("선택 >> ");
		
		int checkProcessResult = sc.nextInt();
		sc.nextLine();
		
		return checkProcessResult;
	}
	
	public int selectTransactionPeriod() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("거래 기간을 선택하세요.");
		System.out.println("1. 오늘");
		System.out.println("2. 일주일");
		System.out.println("3. 한달");
		System.out.print("선택 >> ");
		int selectedPeriod = sc.nextInt();
		sc.nextLine();
		
		return selectedPeriod;
	}
	
	public String putDateInfo() throws ParseException {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("날짜를 입력하세요 < yyyy-MM-dd 형태로 입력할 것 >");
		System.out.print("입력: >> ");
		String selectedDate = sc.nextLine();
		
		return selectedDate;
	}
	
	public void loadBank(ArrayList<Client> clientList) {
		
	}
	
	public void saveBank(ArrayList<Client> clientList) {
		
	}
	
}
