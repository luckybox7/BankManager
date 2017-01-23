import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;


class BankManageHandler {

	ClientManager clientManager = new ClientManager();
	BankManageIOHandler bankManageIOHandler = new BankManageIOHandler();
	BankManageHelper bankManagerHelper = new BankManageHelper();
	
	public int getFoundIndex(String name) { // 이름 중복 확인 위한 메소드. 이름 있으면 해당 인덱스(i) 리턴, 없으면 -1 리턴 

		for (int i = 0; i < clientManager.getClientList().size(); i++) {
			if (name.compareTo(clientManager.getClient(i).getName()) == 0) {
				return i;
			}
		}
		return Constant.FOUND_RESULT.NOT_FOUND; // 없는 경우 
	}

	public void addNewClient(String name, int index) { // 신규인지 기존인지 확인 후 회원정보까지 입력 
											
		if (index == Constant.FOUND_RESULT.NOT_FOUND) {		
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
		
		for (int i = 0; i < clientManager.getClientList().size(); i++) {
			for (int j = 0; j < clientManager.getClientList().get(i).getAccountList().size(); j++) {
				if (accountNum.compareTo(clientManager.getClient(i).getAccount(j).getAccountNum()) == 0 ) { // 모든 고객의 계좌번호 체크, 충복 발생 
					return true;
				}
			}
		}
		return false;
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
	
	public void attachAccount(int index, Account account){
		
		if(index == Constant.FOUND_RESULT.NOT_FOUND){ // 중복이 없으면 
			clientManager.getClient(clientManager.getClientList().size()-1).setAccount(account);
		}else{ // 중복이 있으면 
			clientManager.getClient(index).setAccount(account);
		}
	}
	
	public void showAllAccountInfo(int index) {
		System.out.println("==== 모든 계좌 정보가 표시됩니다 ====");
		
		for(int i=0; i<clientManager.getClient(index).getAccountList().size(); i++) {
			String showAccountNum = clientManager.getClient(index).getAccount(i).getAccountNum();
			int showBalance = clientManager.getClient(index).getAccount(i).getBalance();
			System.out.println("계좌번호 : "+ showAccountNum +" // 잔액 : "+ showBalance);
		}
	}
	
	public void printTodayTransaction(int transactionNum, Transaction t, int compare) {
		if(compare == 0) { // 오늘 날짜와 같은 경우 
			System.out.print("거래번호: "+transactionNum);
			System.out.println(t);
		}
	}
	
	public void printPeriodTransaction(int transactionNum, Transaction t, int compare1, int compare2) {
		if(compare1 >= 0 && compare2 < 0) { // 오늘 날짜보다 작고 일주일(한달)전보다 큰 경우
			System.out.print("거래번호: "+transactionNum);
			System.out.println(t);
		}
	}
	public void printTransactionList(int foundNameIndex, int foundAccountNumIndex, int foundTransactionIndex, int period, String todayInfo) throws ParseException { 
		
		int transactionNum = foundTransactionIndex+1;
		String printedDate = clientManager.getClient(foundNameIndex).getAccount(foundAccountNumIndex).tManager.getTransaction(foundTransactionIndex).getDateInfo();
		
		int compare1 = todayInfo.compareTo(printedDate);
		
		if(period == 1) { // 하루
			Transaction t = clientManager.getClient(foundNameIndex).getAccount(foundAccountNumIndex).tManager.getTransaction(foundTransactionIndex);
			printTodayTransaction(transactionNum, t,compare1);
		}

	}
	
	public void printTransactionList(int foundNameIndex, int foundAccountNumIndex, int foundTransactionIndex, int period, String todayInfo, String compareDay) throws ParseException { 
		
		int transactionNum = foundTransactionIndex+1;
		String printedDate = clientManager.getClient(foundNameIndex).getAccount(foundAccountNumIndex).tManager.getTransaction(foundTransactionIndex).getDateInfo();
		
		int compare1 = todayInfo.compareTo(printedDate); // 오늘과 입력받은 날짜 비교 
		int compare2 = compareDay.compareTo(printedDate); 
	
		Transaction t = clientManager.getClient(foundNameIndex).getAccount(foundAccountNumIndex).tManager.getTransaction(foundTransactionIndex);
		printPeriodTransaction(transactionNum, t, compare1, compare2);
	}
	
	public String getTodayDate(SimpleDateFormat sdf, Date today) {
		String todayInfo = sdf.format(today); // String 형태로 바꾼 부분 
		
		return todayInfo;
	}
	
	public String getWeekAgoDate(SimpleDateFormat sdf, Date today) {
		Date weekAgo = today;
		
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(weekAgo);
		cal1.add(Calendar.DATE, -7);
		
		String weekAgoDate = sdf.format(cal1.getTime()); // String 형태로 변환
		
		return weekAgoDate;
	}
	
	public String getMonthAgoDate(SimpleDateFormat sdf, Date today) {
		Calendar cal2 = Calendar.getInstance();
		
		Date monthAgo = today;
		cal2.setTime(monthAgo);
		cal2.add(Calendar.DATE, -30);
		String monthAgoDate = sdf.format(cal2.getTime());
		
		return monthAgoDate;
	}
	
	public void showTransactionList(int foundNameIndex, int foundAccountNumIndex, int period) throws ParseException {

		Date today = new Date(); // 오늘 날짜 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // yyyy-MM-dd 형태로 바꾸기 위한 부분 
		
		String todayInfo = getTodayDate(sdf, today);
		
		if(period == 1) { // 하루 
			System.out.println("오늘 하루의 거래 내역");
			
			for(int i=0; i<clientManager.getClient(foundNameIndex).getAccount(foundAccountNumIndex).tManager.getTransactionList().size(); i++) {
			
				int transactionNum = i+1;
				String printedDate = clientManager.getClient(foundNameIndex).getAccount(foundAccountNumIndex).tManager.getTransaction(i).getDateInfo();
				int compare1 = todayInfo.compareTo(printedDate);
				
				Transaction t = clientManager.getClient(foundNameIndex).getAccount(foundAccountNumIndex).tManager.getTransaction(i);
				printTodayTransaction(transactionNum, t, compare1);
			}
		}else if(period == 2 || period ==3 ){ // 일주일
			String weekAgoDate = getWeekAgoDate(sdf, today);
			String monthAgoDate = getMonthAgoDate(sdf, today);
			System.out.println("일주일 동안의 거래 내역");
			
			for(int i=0; i<clientManager.getClient(foundNameIndex).getAccount(foundAccountNumIndex).tManager.getTransactionList().size(); i++) {

				int transactionNum = i+1;
				String dateInfo = clientManager.getClient(foundNameIndex).getAccount(foundAccountNumIndex).tManager.getTransaction(i).getDateInfo();
				
				int compare1 = todayInfo.compareTo(dateInfo); // 오늘과 입력받은 날짜 비교 
			
				Transaction t = clientManager.getClient(foundNameIndex).getAccount(foundAccountNumIndex).tManager.getTransaction(i);
				
				if(period == 2) {
					int compare2 = weekAgoDate.compareTo(dateInfo);
					printPeriodTransaction(transactionNum, t, compare1, compare2);
				}else{
					int compare3 = monthAgoDate.compareTo(dateInfo);
					printPeriodTransaction(transactionNum, t, compare1, compare3);
				}
				
			}
		}
	}
	
	public int checkAllAccount(int duplicateNameIndex) {
		
		int balanceResultIndex = 0; 
	
		for(int i=0; i<clientManager.getClient(duplicateNameIndex).getAccountList().size(); i++) {
			if(clientManager.getClient(duplicateNameIndex).getAccount(i).getBalance() != 0 ){ // 갖고있는 계좌 중 잔액이 0원이 아닌 계좌가 하나라도 있는 경우 
				balanceResultIndex = Constant.FOUND_RESULT.NOT_FOUND;
				break; // 하나라도 잔고가 0원이 아니면 삭제 불가하므로 for문을 빠져나간다. 
			}else{ // 잔액이 전부 0원인 경우 
				balanceResultIndex = Constant.FOUND_RESULT.DELETE_ALL;
			}
		}
		
		return balanceResultIndex;
	}
	
	public int checkSpecificAccount(int duplicateNameIndex) {
		
		int balanceResultIndex = 0;
		String selectedAccountNum = bankManageIOHandler.selectAccountNum();
		
		for(int i=0; i<clientManager.getClient(duplicateNameIndex).getAccountList().size(); i++) { // 해당 이름의 위치에서 갖고 있는 계좌 수만큼 반복하면서 	
			if(selectedAccountNum.equals(clientManager.getClient(duplicateNameIndex).getAccount(i).getAccountNum())){  // 입력받은 계좌번호와 동일한 계좌번호를 찾은 다음 
				if(clientManager.getClient(duplicateNameIndex).getAccount(i).getBalance() != 0) { // 해당 계좌의 잔액이 0원이 아니면 삭제 불가 
					balanceResultIndex = Constant.FOUND_RESULT.CANNOT_DELETE;
				}else{ // 해당 계좌의 잔액이 0원이면 삭제 가능 
					balanceResultIndex = i; // 해당 계좌의 위치 반환 
				}
				break;
			}else{ // 계좌번호와 일치하는 정보가 없음
				balanceResultIndex = Constant.FOUND_RESULT.NOT_FOUND;
			}
		}
		return balanceResultIndex;
	}
	
	public int checkLeftBalance(int foundNameIndex, int deleteType) {
		
		int balanceResultIndex = 0;
		
		if(deleteType == Constant.DELETE_TYPE.ALL) { // 고객이 갖고 있는 전체 계좌 잔액 확인 
			balanceResultIndex = checkAllAccount(foundNameIndex);
		}else{ // 특정 계좌 잔액 확인
			balanceResultIndex = checkSpecificAccount(foundNameIndex);
		}
		return balanceResultIndex;
	}
	
	public void deleteAccount(int foundNameIndex, int balanceResultIndex){
		
		switch(balanceResultIndex) {
		case Constant.FOUND_RESULT.CANNOT_DELETE:
			System.out.println("계좌를 삭제할 수 없습니다. 계좌의 잔액이 0원인지 확인하세요.");
			// 모든 계좌정보 보여주기 
			showAllAccountInfo(foundNameIndex);
			break;
		case Constant.FOUND_RESULT.DELETE_ALL: // 전체 삭제 
			clientManager.getClient(foundNameIndex).clearAllAccount();
			clientManager.setClearClient(foundNameIndex);
			break;
		case Constant.FOUND_RESULT.NOT_FOUND:
			System.out.println("해당 계좌를 찾을 수 없습니다.");
		default: // 인덱스가 넘어오는 경우 
			clientManager.getClient(foundNameIndex).clearSpecificAccount(balanceResultIndex);
			break;
		}
	}
	
	public int findName(String name) {
		int nameIndex = Constant.FOUND_RESULT.NOT_FOUND;
		for(int i=0; i<clientManager.getClientList().size(); i++) {
			if(name.compareTo(clientManager.getClient(i).getName()) == 0) {
				nameIndex = i;
				break;
			}
		}
		return nameIndex;
	}
	
	public int findAccountNum(int foundNameIndex, String accountNum) {
		int tempAccountNumIndex = Constant.FOUND_RESULT.NOT_FOUND;
		
		for(int i=0; i<clientManager.getClient(foundNameIndex).getAccountList().size(); i++) {
			String tempAccountNum = clientManager.getClient(foundNameIndex).getAccount(i).getAccountNum();
			if(accountNum.compareTo(tempAccountNum)==0) {
				tempAccountNumIndex = i;
				break;
			}
		}
		return tempAccountNumIndex;
	}
	
	public int findReceiverIndex(String accountNum) {
		int tempReceiverIndex = 0;
			
		for(int i=0; i<clientManager.getClientList().size(); i++) { 
			for(int j=0; j<clientManager.getClient(i).getAccountList().size(); j++) { // 고객들이 갖는 계좌 수만큼 다시 확인
				String tempAccountNum = clientManager.getClient(i).getAccount(j).getAccountNum(); 
				
				if(accountNum.compareTo(tempAccountNum)==0) {
					tempReceiverIndex = i;
					break;
				}
			}
		}
		return tempReceiverIndex;
	}
	
	public void printBasicInfo(int foundNameIndex) {
		System.out.println("==== 고객 정보를 출력합니다 ====");
		
		for(int i=0; i<clientManager.getClientList().size(); i++) { 
			if(clientManager.getClient(foundNameIndex).getName().compareTo(clientManager.getClient(i).getName())==0) {
				clientManager.getClient(foundNameIndex).showClientBasicInfo();
				return;
			}
		}
	}
	
	public void printAccountInfo(int foundNameIndex) {
		for(int i=0; i<clientManager.getClient(foundNameIndex).getAccountList().size(); i++) { 
			Account a = clientManager.getClient(foundNameIndex).getAccountList().get(i); // toString을 이용해서 출력
			System.out.println(a);
		}	
	}	
	
	public void printOneAccountInfo(int foundNameIndex, int foundAccountNumIndex) {
		Account a = clientManager.getClient(foundNameIndex).getAccountList().get(foundAccountNumIndex);
		System.out.println(a);
		
	}
	
	public int printCheckAccountBalance(int foundNameIndex) {
		int checkAccountTotalBalance = 0;
	
		for(int i=0; i<clientManager.getClient(foundNameIndex).getAccountList().size(); i++) {	
			int tempAccountType = clientManager.getClient(foundNameIndex).getAccount(i).getAccountType();
			
			if(tempAccountType == Constant.ACCOUNT_TYPE.CHECK_ACCOUNT) {
				checkAccountTotalBalance += clientManager.getClient(foundNameIndex).getAccount(i).getBalance();
			}
		} 
		
		return checkAccountTotalBalance;
	}
	
	public int printMinusAccountBalance(int foundNameIndex) {
		int minusAccountTotalBalance = 0;
		
		for(int i=0; i<clientManager.getClient(foundNameIndex).getAccountList().size(); i++) {
			int tempAccountType = clientManager.getClient(foundNameIndex).getAccount(i).getAccountType();
			
			if(tempAccountType == Constant.ACCOUNT_TYPE.MINUS_ACCOUNT){
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
		
		int	foundNameIndex = findName(name); // 이름 비교해서 위치를 찾고
		if(foundNameIndex == Constant.FOUND_RESULT.NOT_FOUND) {
			System.out.println("이름을 찾을 수 없습니다");
		}else{
			printBasicInfo(foundNameIndex); // 정보를 보여준다 (이름, 주소, 전화번호, 신용등급 )
			printAccountInfo(foundNameIndex); // 계좌 잔고 출력 ( 계좌종류, 잔고 순서 ) 
			printTotalBalance(foundNameIndex); // 종류별 잔액 출력 
			System.out.println("");
		}
	}
	
	public void printSpecificAccountInfo(int foundNameIndex, String accountNum) {
		int foundAccountNumIndex = findAccountNum(foundNameIndex, accountNum);
		
		if(foundAccountNumIndex == Constant.FOUND_RESULT.NOT_FOUND) {
			System.out.println("해당 계좌번호를 찾을 수 없습니다.");
		}else{
			printOneAccountInfo(foundNameIndex, foundAccountNumIndex);
		}
	}
	
	public void printAllClientInfo() {		
		Collections.sort(clientManager.getClientList(), Client.clientNameComparator);
		
		for(int i=0; i<clientManager.getClientList().size(); i++) {
			clientManager.getClient(i).showClientBasicInfo();
			printAccountInfo(i);
			printTotalBalance(i);
			System.out.println(""); 
		}
	}
	
	public void depositAction(int foundNameIndex, String accountNum, int addMoney, String selectedDate) {
		for(int i=0; i<clientManager.getClient(foundNameIndex).getAccountList().size(); i++) {
			String tempAccountNum = clientManager.getClient(foundNameIndex).getAccount(i).getAccountNum(); // 각 계좌의 계좌번호를 받아서 
			int tempBalance = clientManager.getClient(foundNameIndex).getAccount(i).getBalance();
			
			if(accountNum.compareTo(tempAccountNum)==0) { // 입력받은 계좌번호와 동일한 경우 	
				tempBalance = tempBalance + addMoney;
				clientManager.getClient(foundNameIndex).getAccount(i).setBalance(tempBalance); // 입금 시키고 
				Transaction transaction = new Transaction(selectedDate, Constant.TRNASACTION_TYPE.DEPOSIT, addMoney, tempBalance); // 날짜 정보 받아서
				
				clientManager.getClient(foundNameIndex).getAccount(i).tManager.setTransaction(transaction); // 새로운 거래내역 추가
						
				return;
			}
		}
	}
	
	public void withdrawAction(int foundNameIndex, String accountNum, int minusMoney, String selectedDate) {
		for(int i=0; i<clientManager.getClient(foundNameIndex).getAccountList().size(); i++) {
			String tempAccountNum = clientManager.getClient(foundNameIndex).getAccount(i).getAccountNum();
			int tempBalance = clientManager.getClient(foundNameIndex).getAccount(i).getBalance();
			
			if(accountNum.compareTo(tempAccountNum)==0) {
				tempBalance = tempBalance - minusMoney;
				
				if(tempBalance > minusMoney) {
					clientManager.getClient(foundNameIndex).getAccount(i).setBalance(tempBalance); // 돈 출금하고 
					Transaction transaction = new Transaction(selectedDate, Constant.TRNASACTION_TYPE.WITHDRAW, minusMoney, tempBalance); // 날짜 정보 받아서
					clientManager.getClient(foundNameIndex).getAccount(i).tManager.setTransaction(transaction); // 새로운 거래내역 추가
				}else {
					System.out.println("잔액이 부족합니다"); // 한도가 아직 없어서 일단 이렇게 
				}
				break;
			}
		}
	}
	
	public void depositTransferAction(int foundReceiverNameIndex, String selectedDepositAccountNum, int addMoney, String selectedDate, int foundSenderNameIndex) {
		for(int i=0; i<clientManager.getClient(foundReceiverNameIndex).getAccountList().size(); i++) {
			String tempAccountNum = clientManager.getClient(foundReceiverNameIndex).getAccount(i).getAccountNum();
			int tempBalance = clientManager.getClient(foundReceiverNameIndex).getAccount(i).getBalance();
			
			if(selectedDepositAccountNum.compareTo(tempAccountNum)==0) {
				tempBalance = tempBalance + addMoney;
				clientManager.getClient(foundReceiverNameIndex).getAccount(i).setBalance(tempBalance);
				String senderName = clientManager.getClient(foundSenderNameIndex).getName(); // 보낸사람 이름 확인
				Transaction transaction = new Transaction(selectedDate, Constant.TRNASACTION_TYPE.TRANSFER_DEPOSIT, addMoney, tempBalance, senderName); // 거래내역 생성
				clientManager.getClient(foundReceiverNameIndex).getAccount(i).tManager.setTransaction(transaction); // 거래 추가 
				
				return;
			}
		}
	}
	
	public void withdrawTransferAction(int foundSenderNameIndex, String selectedWithdrawAccountNum, int minusMoney, String selectedDate, int foundReceiverNameIndex) {
		for(int i=0; i<clientManager.getClient(foundSenderNameIndex).getAccountList().size(); i++) {
			String tempAccountNum = clientManager.getClient(foundSenderNameIndex).getAccount(i).getAccountNum();
			int tempBalance = clientManager.getClient(foundSenderNameIndex).getAccount(i).getBalance();
			
			if(selectedWithdrawAccountNum.compareTo(tempAccountNum)==0) {
				tempBalance = tempBalance - minusMoney;
				if(tempBalance > minusMoney) {
					clientManager.getClient(foundSenderNameIndex).getAccount(i).setBalance(tempBalance); // 돈 출금하고 
					String receiverName = clientManager.getClient(foundReceiverNameIndex).getName(); // 받는사람 이름 확인 
					Transaction transaction = new Transaction(selectedDate, Constant.TRNASACTION_TYPE.TRANSFER_WITHDRAW, minusMoney, tempBalance, receiverName); // 받은 정보대로 새로운 거래내역 생성하는 구문 
					clientManager.getClient(foundSenderNameIndex).getAccount(i).tManager.setTransaction(transaction); // 새로운 거래내역 추가
				}else {
					System.out.println("잔액이 부족합니다"); // 한도가 아직 없어서 일단 이렇게 
				}
				break;
			}
		}
	}
	
	public void checkSendMoneyLimit(int foundNameIndex, String accountNum) {
		for(int i=0; i<clientManager.getClient(foundNameIndex).getAccountList().size(); i++) {
			String tempAccountNum = clientManager.getClient(foundNameIndex).getAccount(i).getAccountNum();
			int tempBalance = clientManager.getClient(foundNameIndex).getAccount(i).getBalance();
			
			if(accountNum.compareTo(tempAccountNum)==0) {
				System.out.println("최대 " +tempBalance +"(원) 까지 송금 가능합니다.");
			}
		}
	}
	
// ---------------------------------------------- Client 핵심 동작 메소드 ------------------------- 
	
	public void deposit() throws ParseException {
		String typedName = bankManageIOHandler.typeName(); // 이름 입력받고 
		int foundNameIndex = getFoundIndex(typedName); // 이름 인덱스 확인 후 
		printAccountInfo(foundNameIndex); // 계좌 전부 보여주고 
		String selectedAccountNum = bankManageIOHandler.selectAccountNum(); // 계좌입력받고
		System.out.println("입금할 금액을 입력하세요.");
		int addMoney = bankManageIOHandler.typeMoney(); // 입금액 입력받고 
	
		String selectedDate = bankManageIOHandler.putDateInfo();

		depositAction(foundNameIndex, selectedAccountNum, addMoney, selectedDate); // 입금 액션 실행 ( 거래내역도 생성 )
	}
	
	public void withdraw() throws ParseException {
		
		String typedName = bankManageIOHandler.typeName();
		int foundNameIndex = getFoundIndex(typedName);
		printAccountInfo(foundNameIndex);
		String selectedAccountNum = bankManageIOHandler.selectAccountNum();
		System.out.println("출금할 금액을 입력하세요.");
		int minusMoney = bankManageIOHandler.typeMoney();
		
		String selectedDate = bankManageIOHandler.putDateInfo();
		
		withdrawAction(foundNameIndex, selectedAccountNum, minusMoney, selectedDate); // 출금 액션 실행 ( 날짜입력 포함 ) 
	}

	
	public void accountTransfer() throws ParseException {

		String typedName = bankManageIOHandler.typeName();
		int foundSenderNameIndex = getFoundIndex(typedName); // 계좌 인덱스 확인 
		printAccountInfo(foundSenderNameIndex);
		
		System.out.println("계좌이체할 계좌를 선택하세요.");
		String selectedWithdrawAccountNum = bankManageIOHandler.selectAccountNum();
		checkSendMoneyLimit(foundSenderNameIndex, selectedWithdrawAccountNum); // 송금 가능 금액 체크 
		System.out.println("송금할 금액을 입력하세요.");
		int sendMoney = bankManageIOHandler.typeMoney();
		System.out.println("입금할 계좌를 선택하세요.");
		String selectedDepositAccountNum = bankManageIOHandler.selectAccountNum(); // 받는 계좌 
		int foundReceiverNameIndex = findReceiverIndex(selectedDepositAccountNum); // 이체를 받는 고객의 인덱스 파악

		String selectedDate = bankManageIOHandler.putDateInfo();
		int checkProcessResult = bankManageIOHandler.checkProcess(); // 확인/취소 여부 
	
		if(checkProcessResult == Constant.DECISION.YES) {	
			withdrawTransferAction(foundSenderNameIndex, selectedWithdrawAccountNum, sendMoney, selectedDate, foundReceiverNameIndex); // 계좌 이체시 출금 실행 
			depositTransferAction(foundReceiverNameIndex, selectedDepositAccountNum, sendMoney, selectedDate, foundSenderNameIndex); // 입금 실행 
		}else if(checkProcessResult == Constant.DECISION.NO){
			System.out.println("처리 취소");
			return;
		}
	}
	
	public void showBalance() {
		String typedName = bankManageIOHandler.typeName(); 
		int foundNameIndex = getFoundIndex(typedName); // 이름 위치 찾고
		
		int printedType = bankManageIOHandler.selectPrintAccountInfoType(); // 출력 유형 선택 
		
		switch(printedType) {
		case Constant.PRINT_TYPE.ALL:
			printAccountInfo(foundNameIndex);
			printTotalBalance(foundNameIndex);
			break;
		case Constant.PRINT_TYPE.SPECEFIC:
			String selectedAccountNum = bankManageIOHandler.selectAccountNum(); // 계좌를 입력 받아서 
			printSpecificAccountInfo(foundNameIndex, selectedAccountNum); // 해당 계좌 정보만 출력 
			break;
		}
	}
	
	public void showStatement() throws ParseException {
		String typedName = bankManageIOHandler.typeName(); 
		int foundNameIndex = getFoundIndex(typedName); // 이름 위치 찾고 
		String selectedAccount = bankManageIOHandler.selectAccountNum(); // 계좌 선택 
		int foundAccountNumIndex = findAccountNum(foundNameIndex, selectedAccount);
		
		if(foundAccountNumIndex == Constant.FOUND_RESULT.NOT_FOUND) {
			System.out.println("해당 계좌를 찾을 수 없습니다.");
		}else {
			int selectedPeriod = bankManageIOHandler.selectTransactionPeriod(); // 거래기간 선택 1일, 7일, 30일 
			showTransactionList(foundNameIndex, foundAccountNumIndex, selectedPeriod);
		}
	}
}
