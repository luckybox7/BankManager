interface INIT_MENU {
	int TELLER = 1, CLIENT = 2, EXIT = 3;
}

interface TELLER_MENU {
	int OPENACCOUNT = 1, CLOSEACCOUNT = 2, SHOWCLIENT = 3, EXIT = 4;
}

interface CLIENT_MENU {
	int DEPOSIT = 1, WITHDRAW = 2, ACCOUNTTRANSFER = 3, SHOWBALANCE = 4, SHOWSTATEMENT = 5, EXIT = 6;
}

class MenuSet {
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
}
