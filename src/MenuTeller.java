import java.util.Scanner;

class MenuTeller {
	
	Scanner sc = new Scanner(System.in);
	Teller teller = new Teller();

	public void tellerJob() {

		while (true) {
			BankManageIOHandler.printTellerMenu();
			int tellerChoice = sc.nextInt();
			sc.nextLine();

			switch (tellerChoice) {
			case TELLER_MENU.OPENACCOUNT: // 계좌 개설
				teller.openAccount();
				break;
			case TELLER_MENU.CLOSEACCOUNT:
				teller.closeAccount();
				break;
			case TELLER_MENU.SHOWCLIENT:
				teller.showClient();
				break;
			case TELLER_MENU.EXIT:

				return;
			}
		}
	}
}
