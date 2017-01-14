import java.util.Arrays;
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

	public void addNewClient(String name, int foundNameIndex) { // 신규인지 기존인지 확인 후 회원정보까지 입력 
											
		if (foundNameIndex == NOT_FOUND) {		
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
	
	public void accountToClient(int foundNameIndex, Account account){
		
		if(foundNameIndex == NOT_FOUND){ // 중복이 없으면 
			clientManager.getClient(clientManager.getClientCnt()-1).setAccount(account);
		}else{ // 중복이 있으면 
			clientManager.getClient(foundNameIndex).setAccount(account);
		}
	}
	
	public void showAllAccountInfo(int foundNameIndex) {
		System.out.println("==== 모든 계좌 정보가 표시됩니다 ====");
		
		String showAccountNum;
		int showBalance;
		
		for(int i=0; i<clientManager.getClient(foundNameIndex).getAccountCnt(); i++) {
			showAccountNum = clientManager.getClient(foundNameIndex).getAccount(i).getAccountNum();
			showBalance = clientManager.getClient(foundNameIndex).getAccount(i).getBalance();
			System.out.println("계좌번호 : "+ showAccountNum +" // 잔액 : "+ showBalance);
		}
	}
	
	public void showTransactionList(int foundNameIndex, int foundAccountNumIndex) {
		System.out.println("==== 모든 거래내역이 표시됩니다 ====");
		for(int i=0; i<clientManager.getClient(foundNameIndex).getAccount(foundAccountNumIndex).tManager.getTransCnt(); i++) {
			printTransactionInfo(foundNameIndex, foundAccountNumIndex, i);
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
	
	public int checkLeftBalance(int foundNameIndex, int deleteType) {
		
		int balanceResultIndex = 0;
		
		if(deleteType == 1) { // 고객이 갖고 있는 전체 계좌 잔액 확인 
			balanceResultIndex = scanAllAccount(foundNameIndex);
		}else{ // 특정 계좌 잔액 확인
			balanceResultIndex = scanSpecificAccount(foundNameIndex);
		}
		return balanceResultIndex;
	}
	
	public void deleteSelectedAccount(int foundNameIndex, int balanceResultIndex){
		
		switch(balanceResultIndex) {
		case NOT_FOUND:
			System.out.println("계좌를 삭제할 수 없습니다. 계좌의 잔액이 0원인지 확인하세요.");
			// 모든 계좌정보 보여주기 
			showAllAccountInfo(foundNameIndex);
			break;
		case -2: // 전체 삭제 
			clientManager.getClient(foundNameIndex).clearAllAccount();
			clientManager.setClearClient(foundNameIndex);
			break;
		// dafault를 사용하면 안될 것 같은데 
		default: // 인덱스가 넘어오는 경우 
			clientManager.getClient(foundNameIndex).clearSpecificAccount(balanceResultIndex);
			clientManager.getClient(foundNameIndex).rearrangeAccount(balanceResultIndex); // 배열 재배치 
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
	
	public int findSameAccountNum(int foundNameIndex, String accountNum) {
		int tempAccountNumIndex = 0;
		
		for(int i=0; i<clientManager.getClient(foundNameIndex).getAccountCnt(); i++) {
			String tempAccountNum = clientManager.getClient(foundNameIndex).getAccount(i).getAccountNum();
			if(accountNum.compareTo(tempAccountNum)==0) {
				tempAccountNumIndex = i;
				break;
			}
		}
		return tempAccountNumIndex;
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
			
			System.out.println("계좌종류: "+ printedAccountType + " | " + "계좌번호: "+ printedAccountNum + " | " + "잔액: "+ printedAccountBalance);
		}	
	}	
	
	public void printOneAccountInfo(int foundNameIndex, int foundAccountNumIndex) {
		String printedAccountNum;
		String printedAccountType;
		int printedAccountBalance;
		
		printedAccountType = clientManager.getClient(foundNameIndex).getAccount(foundAccountNumIndex).getAccountType();	
		printedAccountNum = clientManager.getClient(foundNameIndex).getAccount(foundAccountNumIndex).getAccountNum();
		printedAccountBalance = clientManager.getClient(foundNameIndex).getAccount(foundAccountNumIndex).getBalance();
		
		System.out.println("계좌종류: "+ printedAccountType + " | " + "계좌번호: "+ printedAccountNum + " | " + "잔액: "+ printedAccountBalance);
	}
	
	public void printTransactionInfo(int foundNameIndex, int foundAccountNumIndex, int foundTransactionIndex) {
		int printedMonth;
		int printedDay;
		int printedMoney;
		String printedType;
		int printedNum;
		int printedBalance;
		
		printedMonth = clientManager.getClient(foundNameIndex).getAccount(foundAccountNumIndex).tManager.getTransactions(foundTransactionIndex).getMonth();
		printedDay = clientManager.getClient(foundNameIndex).getAccount(foundAccountNumIndex).tManager.getTransactions(foundTransactionIndex).getDay();
		printedMoney = clientManager.getClient(foundNameIndex).getAccount(foundAccountNumIndex).tManager.getTransactions(foundTransactionIndex).getTransactionMoney();
		printedType = clientManager.getClient(foundNameIndex).getAccount(foundAccountNumIndex).tManager.getTransactions(foundTransactionIndex).getTransactionType();
		printedNum = clientManager.getClient(foundNameIndex).getAccount(foundAccountNumIndex).tManager.getTransactions(foundTransactionIndex).getTransactionNum(); // 수정 필요
		printedBalance = clientManager.getClient(foundNameIndex).getAccount(foundAccountNumIndex).tManager.getTransactions(foundTransactionIndex).getTransactionBalance();
		
		System.out.println("거래번호: "+printedNum+" | "+"거래날짜: "+printedMonth+"월 "+printedDay+"일"+" | "+"거래종류: "+printedType+" | "+"거래액: "+printedMoney+"(원)"+" | "+"잔고: "+printedBalance+"(원)");
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
	
	public void printSpecificAccountInfo(int foundNameIndex, String accountNum) {
		int foundAccountNumIndex = findSameAccountNum(foundNameIndex, accountNum);
		printOneAccountInfo(foundNameIndex, foundAccountNumIndex);

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
		Arrays.sort(clientManager.getClientList(), Client.clientNameComparator); // 정렬하고 
		printEveryInfo(); // 모든 정보 출력 (이름, 주소, 전화번호, 신용등급)
	}
	
	public Transactions createTransactions(int month, int day, String type, int transactionMoney, int balance) {
		Transactions newTransactions = null;
		newTransactions = new Transactions(month, day, type, transactionMoney, balance); // 날짜 정보 받아서
		
		return newTransactions;
	}
	
	public void depositAction(int foundNameIndex, String accountNum, int addMoney, int month, int day) {
		int tempBalance = 0;
		String tempAccountNum;
		
		String transactionType = "입금"; 
		Transactions tempTransactions = null;
		
		for(int i=0; i<clientManager.getClient(foundNameIndex).getAccountCnt(); i++) {
			tempAccountNum = clientManager.getClient(foundNameIndex).getAccount(i).getAccountNum(); // 각 계좌의 계좌번호를 받아서 
			tempBalance = clientManager.getClient(foundNameIndex).getAccount(i).getBalance();
			
			if(accountNum.compareTo(tempAccountNum)==0) { // 입력받은 계좌번호와 동일한 경우 	
				tempBalance = tempBalance + addMoney;
				clientManager.getClient(foundNameIndex).getAccount(i).setBalance(tempBalance); // 입금 시키고 
				
				tempTransactions = createTransactions(month, day, transactionType, addMoney, tempBalance); // 날짜 정보 받아서
				clientManager.getClient(foundNameIndex).getAccount(i).tManager.setTransactions(tempTransactions); // 새로운 거래내역 추가
						
				return;
			}
		}
	}

	
	public void depositTransferAction(String accountNum, int addMoney) {
		int tempBalance = 0;
		String tempAccountNum;
		
		for(int i=0; i<clientManager.getClientCnt(); i++) {
			for(int j=0; j<clientManager.getClient(i).getAccountCnt(); j++) {
				tempAccountNum = clientManager.getClient(i).getAccount(j).getAccountNum(); // 각 계좌의 계좌번호를 받아서 
				tempBalance = clientManager.getClient(i).getAccount(j).getBalance();
				
				if(accountNum.compareTo(tempAccountNum)==0) { // 입력받은 계좌번호와 동일한 경우 	
					tempBalance = tempBalance + addMoney;
					clientManager.getClient(i).getAccount(j).setBalance(tempBalance);
					return;
				}
			}
		}
	}
	
	public void withdrawAction(int foundNameIndex, String accountNum, int minusMoney, int month, int day) {
		int tempBalance = 0;
		String tempAccountNum;
		
		String transactionType = "출금";
		Transactions tempTransactions = null;
		
		for(int i=0; i<clientManager.getClient(foundNameIndex).getAccountCnt(); i++) {
			tempAccountNum = clientManager.getClient(foundNameIndex).getAccount(i).getAccountNum();
			tempBalance = clientManager.getClient(foundNameIndex).getAccount(i).getBalance();
			
			if(accountNum.compareTo(tempAccountNum)==0) {
				tempBalance = tempBalance - minusMoney;
				if(tempBalance >= 0) {
					clientManager.getClient(foundNameIndex).getAccount(i).setBalance(tempBalance); // 돈 출금하고 
					
					tempTransactions = createTransactions(month, day, transactionType, minusMoney, tempBalance); // 날짜 정보 받아서
					clientManager.getClient(foundNameIndex).getAccount(i).tManager.setTransactions(tempTransactions); // 새로운 거래내역 추가
					
					return;
				}else {
					System.out.println("잔액이 부족합니다"); // 한도가 아직 없어서 일단 이렇게 
					return;
				}
			}
		}
	}
	
	public void withdrawTransferAction(int foundNameIndex, String accountNum, int minusMoney) {
		int tempBalance = 0;
		String tempAccountNum;		
	
		for(int i=0; i<clientManager.getClient(foundNameIndex).getAccountCnt(); i++) {
			tempAccountNum = clientManager.getClient(foundNameIndex).getAccount(i).getAccountNum();
			tempBalance = clientManager.getClient(foundNameIndex).getAccount(i).getBalance();
			
			if(accountNum.compareTo(tempAccountNum)==0) {
				tempBalance = tempBalance - minusMoney;
				if(tempBalance >= 0) {
					clientManager.getClient(foundNameIndex).getAccount(i).setBalance(tempBalance); // 돈 출금하고 
				
					return;
				}else {
					System.out.println("잔액이 부족합니다"); // 한도가 아직 없어서 일단 이렇게 
					return;
				}
			}
		}
	}
	
	public void checkSendMoneyLimit(int foundNameIndex, String accountNum) {
		int tempBalance = 0;
		String tempAccountNum;
		
		for(int i=0; i<clientManager.getClient(foundNameIndex).getAccountCnt(); i++) {
			tempAccountNum = clientManager.getClient(foundNameIndex).getAccount(i).getAccountNum();
			tempBalance = clientManager.getClient(foundNameIndex).getAccount(i).getBalance();
			
			if(accountNum.compareTo(tempAccountNum)==0) {
				System.out.println("최대 " +clientManager.getClient(foundNameIndex).getAccount(i).getBalance() +"(원) 까지 송금 가능합니다.");
			}
		}
	}
	
// ---------------------------------------------- Client 핵심 동작 메소드 ------------------------- 
	
	public void deposit() {
		String typedName = bankManageIOHandler.typeName(); // 이름 입력받고 
		int foundNameIndex = findNameGetIndex(typedName); // 이름 인덱스 확인 후 
		printAccountInfo(foundNameIndex); // 계좌 전부 보여주고 
		String selectedAccountNum = bankManageIOHandler.selectAccountNum(); // 계좌입력받고
		System.out.println("입금할 금액을 입력하세요.");
		int addMoney = bankManageIOHandler.typeMoney(); // 입금액 입력받고 
		
		
		int selectedMonth = bankManageIOHandler.putMonthInfo();
		int selectedDay = bankManageIOHandler.putDayInfo();

		
		
		depositAction(foundNameIndex, selectedAccountNum, addMoney, selectedMonth, selectedDay); // 입금 액션 실행 
	}
	
	public void withdraw() {
		
		String typedName = bankManageIOHandler.typeName();
		int foundNameIndex = findNameGetIndex(typedName);
		printAccountInfo(foundNameIndex);
		String selectedAccountNum = bankManageIOHandler.selectAccountNum();
		System.out.println("출금할 금액을 입력하세요.");
		int minusMoney = bankManageIOHandler.typeMoney();
		
		
		int selectedMonth = bankManageIOHandler.putMonthInfo();
		int selectedDay = bankManageIOHandler.putDayInfo();
		
		
		withdrawAction(foundNameIndex, selectedAccountNum, minusMoney, selectedMonth, selectedDay);
	}

	
	public void accountTransfer() {

		String typedName = bankManageIOHandler.typeName();
		int foundNameIndex = findNameGetIndex(typedName);
		printAccountInfo(foundNameIndex);
		System.out.println("계좌이체할 계좌를 선택하세요.");
		String selectedWithdrawAccountNum = bankManageIOHandler.selectAccountNum();
		
		checkSendMoneyLimit(foundNameIndex, selectedWithdrawAccountNum); // 송금 가능 금액 체크 
		
		System.out.println("송금할 금액을 입력하세요.");
		int sendMoney = bankManageIOHandler.typeMoney();
		System.out.println("입금할 계좌를 선택하세요.");
		String selectedDepositAccountNum = bankManageIOHandler.selectAccountNum();
		
		int checkProcessResult = bankManageIOHandler.checkProcess(); // 확인/취소 여부 
		
		if(checkProcessResult == 1) {
			withdrawTransferAction(foundNameIndex, selectedWithdrawAccountNum, sendMoney);
			depositTransferAction(selectedDepositAccountNum, sendMoney);
		}else {
			return;
		}
	}
	
	public void showBalance() {
		String typedName = bankManageIOHandler.typeName(); 
		int foundNameIndex = findNameGetIndex(typedName); // 이름 위치 찾고
		
		int printedType = bankManageIOHandler.selectPrintAccountInfoType(); // 출력 유형 선택 
		
		switch(printedType) {
		case 1:
			printAccountInfo(foundNameIndex);
			printTotalBalance(foundNameIndex);
			break;
		case 2:
			String selectedAccountNum = bankManageIOHandler.selectAccountNum(); // 계좌를 입력 받아서 
			printSpecificAccountInfo(foundNameIndex, selectedAccountNum); // 해당 계좌 정보만 출력 
			break;
		}
	}
	
	public void showStatement() {
		String typedName = bankManageIOHandler.typeName(); 
		int foundNameIndex = findNameGetIndex(typedName); // 이름 위치 찾고 
		String selectedAccount = bankManageIOHandler.selectAccountNum(); // 계좌 선택 
		int foundAccountNumIndex = findSameAccountNum(foundNameIndex, selectedAccount);
		// int selectedPeriod = bankManageIOHandler.selectTransactionPeriod(); // 거래기간 선택 
	
		showTransactionList(foundNameIndex, foundAccountNumIndex); // 거래내역 보여주기
	}
}
