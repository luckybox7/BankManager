import java.text.ParseException;
import java.util.Scanner;

class BankMain {
	public static void main(String[] args) throws ParseException {

		Scanner sc = new Scanner(System.in);
		
		MenuTeller tr = new MenuTeller();
		MenuClient ct = new MenuClient();
		BankManageHandler bankManageHandler = new BankManageHandler();
		BankManageIOHandler bankManageIOHandler = new BankManageIOHandler();
		
		System.out.println("===== 은행관리 프로그램을 시작합니다 =====");
		while (true) {
			bankManageIOHandler.printInitMenu();
			int choice = sc.nextInt();
			sc.nextLine(); // 버퍼비우기  

			switch (choice) {
			case INIT_MENU.TELLER:
				tr.tellerJob(bankManageHandler, bankManageIOHandler);
				break;
			case INIT_MENU.CLIENT:
				ct.clientJob(bankManageHandler, bankManageIOHandler);
				break;
			case INIT_MENU.EXIT:
				System.out.println("프로그램을 종료합니다");
				return;
			}
		}
	}
}
