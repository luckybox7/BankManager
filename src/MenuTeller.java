import java.util.Scanner;

class MenuTeller {
	
	Scanner sc = new Scanner(System.in);

	public void tellerJob(BankManageHandler bankManageHandler, BankManageIOHandler bankManageIOHandler) {
		Teller teller = new Teller(bankManageHandler, bankManageIOHandler);
		
		while (true) {
			bankManageIOHandler.printTellerMenu();
			int tellerChoice = sc.nextInt();
			sc.nextLine();

			switch (tellerChoice) {
			case Constant.TELLER_MENU.OPEN_ACCOUNT: // 계좌 개설
				teller.openAccount();
				break;
			case Constant.TELLER_MENU.CLOSE_ACCOUNT:
				teller.closeAccount();
				break;
			case Constant.TELLER_MENU.SHOW_CLIENT:
				teller.showClient();
				break;
			case Constant.TELLER_MENU.EXIT:

				return;
			}
		}
	}
}
