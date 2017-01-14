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
				bankManageHandler.withdraw();
				break;
			case CLIENT_MENU.ACCOUNTTRANSFER:
				bankManageHandler.accountTransfer();
				break;
			case CLIENT_MENU.SHOWBALANCE:
				bankManageHandler.showBalance();
				break;
			case CLIENT_MENU.SHOWSTATEMENT:
				bankManageHandler.showStatement();
				break;
			case CLIENT_MENU.EXIT:
				return;
			}
		}
	}
}
