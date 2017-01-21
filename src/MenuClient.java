import java.text.ParseException;
import java.util.Scanner;

class MenuClient {
	
	Scanner sc = new Scanner(System.in);
	Client client = new Client();

	public void clientJob(BankManageHandler bankManageHandler, BankManageIOHandler bankManageIOHandler) throws ParseException {

		while (true) {
			bankManageIOHandler.printClientMenu();
			int clientChoice = sc.nextInt();
			sc.nextLine();
			
			switch(clientChoice) {
			case Constant.CLIENT_MENU.DEPOSIT:
				bankManageHandler.deposit();
				break;
			case Constant.CLIENT_MENU.WITHDRAW:
				bankManageHandler.withdraw();
				break;
			case Constant.CLIENT_MENU.ACCOUNT_TRANSFER:
				bankManageHandler.accountTransfer();
				break;
			case Constant.CLIENT_MENU.SHOW_BALANCE:
				bankManageHandler.showBalance();
				break;
			case Constant.CLIENT_MENU.SHOW_STATEMENT:
				bankManageHandler.showStatement();
				break;
			case Constant.CLIENT_MENU.EXIT:
				return;
			}
		}
	}
}
