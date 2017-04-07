import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

class BankMain {
	public static void main(String[] args) throws ParseException, IOException{

		Scanner sc = new Scanner(System.in);
		
		MenuTeller menuTeller = new MenuTeller();
		MenuClient menuClient = new MenuClient();
		Constant cs = new Constant();
		
		BankManageHandler bankManageHandler = new BankManageHandler();
		BankManageIOHandler bankManageIOHandler = new BankManageIOHandler();
		
		System.out.println("===== 은행관리 프로그램을 시작합니다 =====");
		bankManageIOHandler.loadBank(bankManageHandler.clientManager); // 파일 생성 
		
		while (true) {
			bankManageIOHandler.printInitMenu();
			int choice = sc.nextInt();
			sc.nextLine(); // 버퍼비우기  

			switch (choice) {
			case Constant.INIT_MENU.TELLER:
				menuTeller.tellerJob(bankManageHandler, bankManageIOHandler);
				break;
			case Constant.INIT_MENU.CLIENT:
				menuClient.clientJob(bankManageHandler, bankManageIOHandler);
				break;
			case Constant.INIT_MENU.EXIT:
				bankManageIOHandler.saveBank(bankManageHandler.clientManager); // 파일 저장 
				System.out.println("프로그램을 종료합니다");
				return;
			}
		}
	}
}
