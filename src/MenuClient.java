import java.util.Scanner;

class MenuClient {
	
	Scanner sc = new Scanner(System.in);
	Client client = new Client();

	public void clientJob(BankManageHandler bankManageHandler, BankManageIOHandler bankManageIOHandler) {

		while (true) {
			bankManageIOHandler.printClientMenu();
			int clientChoice = sc.nextInt();
			sc.nextLine();
			
			switch(clientChoice) {
			case CLIENT_MENU.DEPOSIT:
				bankManageHandler.deposit();
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
