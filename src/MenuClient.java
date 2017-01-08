import java.util.Scanner;

class MenuClient {
	Scanner sc2 = new Scanner(System.in);

	public void clientJob() {

		while (true) {
			MenuSet.printClientMenu();
			int clientChoice = sc2.nextInt();
			sc2.nextLine();
			
			switch(clientChoice) {
			case CLIENT_MENU.DEPOSIT:
				
				break;
			case CLIENT_MENU.WITHDRAW:
				
				break;
			case CLIENT_MENU.ACCOUNTTRANSFER:
				
				break;
			case CLIENT_MENU.SHOWBALANCE:
				
				break;
			case CLIENT_MENU.SHOWSTATEMENT:
				
				break;
			case CLIENT_MENU.EXIT:
				return;
			}
		}
	}
}
