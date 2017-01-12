import java.util.Scanner;

class BankManageHandler {

	ClientManager clientManager = new ClientManager();
	BankManageIOHandler bankManageIOHandler = new BankManageIOHandler();
	BankManageHelper bankManagerHelper = new BankManageHelper();
	
	final private int NOT_FOUND = -1;
	
	public int findNameGetIndex(String name) { // 이름 중복 확인 위한 메소드. 이름 있으면 해당 인덱스(i) 리턴, 없으면 -1 리턴 

		for (int i = 0; i < clientManager.getClientCnt(); i++) {
			if (name.compareTo(clientManager.getClient(i).getName()) == 0) {
				return i;
			}
		}
		return NOT_FOUND; // 없는 경우 
	}

	public void addNewClient(String name, int index) { // 신규인지 기존인지 확인 후 회원정보까지 입력 
											
		if (index == NOT_FOUND) {		
			Client newClient = bankManageIOHandler.typeClientData(name);
			clientManager.setClient(newClient);
		} else {
			System.out.println("기존 고객입니다. 해당 정보에 계좌만 추가합니다.");
		}
	}

	public String createAccountNum(int accountType) {

		String accountNumber;
		int num1 = bankManagerHelper.getRandomNumber(100, 999);
		int num2 = bankManagerHelper.getRandomNumber(1000, 9999);

		if (accountType == 1) {
			accountNumber = "001" + "-" + String.valueOf(num1) + "-" + String.valueOf(num2);
		} else {
			accountNumber = "002" + "-" + String.valueOf(num1) + "-" + String.valueOf(num2);
		}
		return accountNumber;
	}

	public boolean checkAccountDuplicate(String accountNum) {
		boolean check = false;

		for (int i = 0; i < clientManager.getClientCnt(); i++) {
			for (int j = 0; j < clientManager.getClient(i).getAccountCnt(); j++) {	
				if (accountNum.compareTo(clientManager.getClient(i).getAccount(j).getAccountNum()) == 0 ) { // 모든 고객의 계좌번호 체크, 충복 발생 
					check = true;
					break;
				} else { // 중복 X
					check = false;
				}
			}
		}
		return check;
	}

	public Account createAccount() {

		int selectResult = bankManageIOHandler.selectAccount(); // 계좌선택한 결과 찾기
		String tempAccountNum;
		while (true) {
			tempAccountNum = createAccountNum(selectResult); // 계좌생성 
			boolean duplicateCheck = checkAccountDuplicate(tempAccountNum); // 중복체크 

			if (duplicateCheck == true) { // 중복이면 반복문 계속 실행 
				continue;
			} else {
				break;
			}
		}

		Account account = null;

		if (selectResult == 1) {
			account = new CheckAccount(tempAccountNum);
		} else {
			account = new MinusAccount(tempAccountNum, "8");
		}

		return account;
	}
	
	public void accountToClient(int index, Account account){
		
		if(index == NOT_FOUND){ // 중복이 없으면 
			clientManager.getClient(clientManager.getClientCnt()-1).setAccount(account);
		}else{ // 중복이 있으면 
			clientManager.getClient(index).setAccount(account);
		}
	}
	
	public void showAllAccountInfo(int findNameResultIndex) {
		System.out.println("==== 모든 계좌 정보가 표시됩니다 ====");
		
		String showAccountNum;
		int showBalance;
		
		for(int i=0; i<clientManager.getClient(findNameResultIndex).getAccountCnt(); i++) {
			showAccountNum = clientManager.getClient(findNameResultIndex).getAccount(i).getAccountNum();
			showBalance = clientManager.getClient(findNameResultIndex).getAccount(i).getBalance();
			System.out.println("계좌번호 : "+ showAccountNum +" // 잔액 : "+ showBalance);
		}
	}
	
	public int scanAllAccount(int duplicateNameIndex) {
		
		int balanceResultIndex = 0; 
		
		for(int i=0; i<clientManager.getClient(duplicateNameIndex).getAccountCnt(); i++) {
			if(clientManager.getClient(duplicateNameIndex).getAccount(i).getBalance() != 0 ){ // 갖고있는 계좌 중 잔액이 0원이 아닌 계좌가 하나라도 있는 경우 
				balanceResultIndex = NOT_FOUND;
				break; // 하나라도 잔고가 0원이 아니면 삭제 불가하므로 for문을 빠져나간다. 
			}else{ // 잔액이 전부 0원인 경우 
				balanceResultIndex = -2;
			}
		}
		
		return balanceResultIndex;
	}
	
	public int scanSpecificAccount(int duplicateNameIndex) {
		
		int balanceResultIndex = 0;
		String selectedAccountNum = bankManageIOHandler.selectAccountNum();
		
		for(int i=0; i<clientManager.getClient(duplicateNameIndex).getAccountCnt(); i++) { // 해당 이름의 위치에서 갖고 있는 계좌 수만큼 반복하면서 	
			if(selectedAccountNum.equals(clientManager.getClient(duplicateNameIndex).getAccount(i).getAccountNum())){  // 입력받은 계좌번호와 동일한 계좌번호를 찾은 다음 
				if(clientManager.getClient(duplicateNameIndex).getAccount(i).getBalance() != 0) { // 해당 계좌의 잔액이 0원이 아니면 삭제 불가 
					balanceResultIndex = NOT_FOUND;
				}else{ // 해당 계좌의 잔액이 0원이면 삭제 가능 
					balanceResultIndex = i; // 해당 계좌의 위치 반환 
				}
				break;
			}else{ // 계좌번호와 일치하는 정보가 없음
				
			}
		}
		return balanceResultIndex;
	}
	
	public int checkLeftBalance(int findNameResultIndex, int deleteType) {
		
		int balanceResultIndex = 0;
		
		if(deleteType == 1) { // 고객이 갖고 있는 전체 계좌 잔액 확인 
			balanceResultIndex = scanAllAccount(findNameResultIndex);
		}else{ // 특정 계좌 잔액 확인
			balanceResultIndex = scanSpecificAccount(findNameResultIndex);
		}
		return balanceResultIndex;
	}
	
	public void deleteSelectedAccount(int findNameResultIndex, int balanceResultIndex){
		
		switch(balanceResultIndex) {
		case NOT_FOUND:
			System.out.println("계좌를 삭제할 수 없습니다. 계좌의 잔액이 0원인지 확인하세요.");
			// 모든 계좌정보 보여주기 
			showAllAccountInfo(findNameResultIndex);
			break;
		case -2:
			clientManager.getClient(findNameResultIndex).clearAllAccount();
			clientManager.setClearClient(findNameResultIndex);
			break;
		// dafault를 사용하면 안될 것 같은데 
		default: // 인덱스가 넘어오는 경우 
			clientManager.getClient(findNameResultIndex).clearSpecificAccount(balanceResultIndex);
			clientManager.getClient(findNameResultIndex).rearrangeAccount(balanceResultIndex);
			break;
		}
	}
	
	public int findSameName(String name) {
		int nameIndex = 0;
		for(int i=0; i<clientManager.getClientCnt(); i++) {
			if(name.compareTo(clientManager.getClient(i).getName()) == 0) {
				nameIndex = i;
				break;
			}
		}
		
		return nameIndex;
	}
	
	public void printBasicInfo(int foundNameIndex) {
		System.out.println("==== 고객 정보를 출력합니다 ====");
		for(int i=0; i<clientManager.getClientCnt(); i++) {
			if(clientManager.getClient(foundNameIndex).getName().compareTo(clientManager.getClient(i).getName())==0) {
				clientManager.getClient(foundNameIndex).showClientBasicInfo();
				return;
			}
		}
	}
	
	public void printAccountInfo(int foundNameIndex) {
		String printedAccountNum;
		String printedAccountType;
		int printedAccountBalance;

		for(int i=0; i<clientManager.getClient(foundNameIndex).getAccountCnt(); i++) {
			printedAccountType = clientManager.getClient(foundNameIndex).getAccount(i).getAccountType();		
			printedAccountNum = clientManager.getClient(foundNameIndex).getAccount(i).getAccountNum();
			printedAccountBalance = clientManager.getClient(foundNameIndex).getAccount(i).getBalance();
			
			System.out.println("계좌번호: "+ printedAccountNum + " | " + "계좌종류: "+ printedAccountType + " | " + "잔액: "+ printedAccountBalance);
		}	
	}	
	
	public int printCheckAccountBalance(int foundNameIndex) {
		
		String tempCheckAccount = "자유입출금 계좌";
		int checkAccountTotalBalance = 0;
	
		for(int i=0; i<clientManager.getClient(foundNameIndex).getAccountCnt(); i++) {
			String tempAccountType = clientManager.getClient(foundNameIndex).getAccount(i).getAccountType();
			
			if(tempCheckAccount.compareTo(tempAccountType)==0) { // 자유입출금 계좌면 
				checkAccountTotalBalance += clientManager.getClient(foundNameIndex).getAccount(i).getBalance();
			}
		} 
		
		return checkAccountTotalBalance;
	}
	
	public int printMinusAccountBalance(int foundNameIndex) {
		String tempMinusAccount = "마이너스 계좌";
		int minusAccountTotalBalance = 0;
		
		for(int i=0; i<clientManager.getClient(foundNameIndex).getAccountCnt(); i++) {
			String tempAccountType = clientManager.getClient(foundNameIndex).getAccount(i).getAccountType();
			
			if(tempMinusAccount.compareTo(tempAccountType)==0){
				minusAccountTotalBalance += clientManager.getClient(foundNameIndex).getAccount(i).getBalance();
			}
		}
		
		return minusAccountTotalBalance;
	}
	
	public void printTotalBalance(int foundNameIndex) {
		int checkAccountTotalBalance = printCheckAccountBalance(foundNameIndex);
		int minusAccountTotalBalance = printMinusAccountBalance(foundNameIndex);
		
		System.out.println("자유입출금 계좌 총 잔액 : " + checkAccountTotalBalance);
		System.out.println("마이너스 계좌 총 잔액 : " + minusAccountTotalBalance);
	}
	
	public void printSpecificClientInfo(String name) {
		
		int	foundNameIndex = findSameName(name); // 이름 비교해서 위치를 찾고
		printBasicInfo(foundNameIndex); // 정보를 보여준다 (이름, 주소, 전화번호, 신용등급 )
		printAccountInfo(foundNameIndex); // 계좌 잔고 출력 ( 계좌종류, 잔고 순서 ) 
		printTotalBalance(foundNameIndex); // 종류별 잔액 출력 
		System.out.println("");
	}
	
	public void printEveryInfo() {
		for(int i=0; i<clientManager.getClientCnt(); i++) {
			clientManager.getClient(i).showClientBasicInfo();
			printAccountInfo(i);
			printTotalBalance(i);
			System.out.println(""); 
		}
	}
	
	public void printAllInfo() {		
		// clientManager.setSortClientList(); // 이름 순으로 정렬하고나서 
		printEveryInfo(); // 모든 정보 출력 (이름, 주소, 전화번호, 신용등급)
		
		// 정렬 아직 안했음 
	}
	
	public void depositAction(int findNameResultIndex, String accountNum, int money) {
		int balance = 0;
		String tempAccountNum;
		for(int i=0; i<clientManager.getClient(findNameResultIndex).getAccountCnt(); i++) {
			tempAccountNum = clientManager.getClient(findNameResultIndex).getAccount(i).getAccountNum(); // 각 계좌의 계좌번호를 받아서 
			
			if(accountNum.compareTo(tempAccountNum)==0) { // 입력받은 계좌번호와 동일한 경우 
				balance = clientManager.getClient(findNameResultIndex).getAccount(i).getBalance();
				balance = balance + money;
				clientManager.getClient(findNameResultIndex).getAccount(i).setBalance(balance);
				return;
			}
		}
	}
	
	public void deposit() {
		String typedName = bankManageIOHandler.typeName(); // 이름 입력받고 
		int findNameResultIndex = findNameGetIndex(typedName); // 이름 인덱스 확인 후 
		printAccountInfo(findNameResultIndex); // 계좌 전부 보여주고 
		String selectedAccountNum = bankManageIOHandler.selectAccountNum(); // 계좌선택하고
		int money = bankManageIOHandler.putMoney(); // 입금액 입력받고 
		depositAction(findNameResultIndex, selectedAccountNum, money);
	}
	
	public void withdraw() {
		
	}
}
