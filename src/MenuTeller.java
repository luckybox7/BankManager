import java.util.Scanner;

class MenuTeller {
	
	Scanner sc1 = new Scanner(System.in);
	
	MenuSet ms = new MenuSet();
	Teller tr = new Teller();

	public void tellerJob() {

		while (true) {
			MenuSet.printTellerMenu();
			int tellerChoice = sc1.nextInt();
			sc1.nextLine();

			switch (tellerChoice) {
			case TELLER_MENU.OPENACCOUNT: // 계좌 개설
				tr.openAccount();
				break;
			case TELLER_MENU.CLOSEACCOUNT:
				tr.closeAccount();
				break;
			case TELLER_MENU.SHOWCLIENT:

				break;
			case TELLER_MENU.EXIT:

				return;
			}
		}
	}
}
