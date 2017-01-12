import java.util.Scanner;

interface INIT_MENU {
	int TELLER = 1, CLIENT = 2, EXIT = 3;
}

interface TELLER_MENU {
	int OPENACCOUNT = 1, CLOSEACCOUNT = 2, SHOWCLIENT = 3, EXIT = 4;
}

interface CLIENT_MENU {
	int DEPOSIT = 1, WITHDRAW = 2, ACCOUNTTRANSFER = 3, SHOWBALANCE = 4, SHOWSTATEMENT = 5, EXIT = 6;
}

class BankManageIOHandler {
	public static void printInitMenu() {
		System.out.println("===== 원하는 업무를 선택해주세요    =====");
		System.out.println("1. 은행원 ");
		System.out.println("2. 고객 ");
		System.out.println("3. 나가기 ");
		System.out.println("==================================");
		System.out.print("선택>> ");
	}

	public static void printTellerMenu() {
		System.out.println("===== 은행원 업무를 시작합니다 ====");
		System.out.println("1. 새로운 계좌 개설 ");
		System.out.println("2. 계좌 해약/취소 ");
		System.out.println("3. 고객 정보 출력 ");
		System.out.println("4. 나가기 ");
		System.out.println("=============================");
		System.out.print("선택>> ");
	}

	public static void printClientMenu() {
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
		
		Scanner sc = new Scanner(System.in)
				;
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
	
	public int selectPrintType() {
		
		Scanner sc = new Scanner(System.in);
		int selectedPrintType = 0;
		
		System.out.println("출력하고자 하는 유형을 선택하세요. ");
		System.out.println("1. 모든 고객 정보 출력");
		System.out.println("2. 특정 고객 정보 출력");
		System.out.print("선택 >> ");
		
		selectedPrintType= sc.nextInt();
		sc.nextLine();
		
		return selectedPrintType;
	}
	
	public int putMoney() {
		int money = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("입금할 금액을 입력하세요.");
		System.out.print("입력 >> ");
		money = sc.nextInt();
		sc.nextLine();
		
		return money;
	}
}
