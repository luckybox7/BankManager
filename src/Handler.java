import java.util.Scanner;

class Handler {

	ClientManager clientManager = new ClientManager();
	
	public int searchDuplicateName(String name) { // 이름 중복 확인 위한 메소드. 이름 있으면 해당 인덱스(i) 리턴, 없으면 -1 리턴 

//		for (int i = 0; i < ctCnt; i++) { // 중복 있는 경우
		for (int i = 0; i < clientManager.getClientCnt(); i++) {
			//if (name.compareTo(client[i].getName()) == 0) {
			if (name.compareTo(clientManager.getClient(i).getName()) == 0) {
				return i;
			}
		}
		return -1; // 없는 경우 
	}

	public String typeName() {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("1.이름 입력 : ");
		String newName = sc.nextLine();

		return newName;
	}

	public void addClientData(String name, int index) { // 신규인지 기존인지 확인 후 회원정보까지 입력 
											
		Scanner sc = new Scanner(System.in);
		if (index == -1) {
			System.out.println("신규 고객입니다. 하단에 추가 정보를 입력하세요.");
			System.out.print("2.주소 입력 : ");
			String newAdd = sc.nextLine();
			System.out.print("3.핸드폰 번호 입력 : ");
			String newPhoneNum = sc.nextLine();
			System.out.println("신용등급은 자동으로 만들어집니다");
			String newCredit = "8";

			//client[ctCnt++] = new Client(name, newAdd, newPhoneNum, newCredit);
			Client tempClient = new Client(name, newAdd, newPhoneNum, newCredit);
			clientManager.setClient(tempClient);
		} else {
			System.out.println("기존 고객입니다. 해당 정보에 계좌만 추가합니다.");
		}
	}

	public int selectAccount() { // 계좌종류 선택 화면
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("어떤 종류의 계좌를 개설하시겠습니까??");
		System.out.println("1.자유입출금 계좌 ");
		System.out.println("2.마이너스 계좌");
		System.out.print("선택>> ");
		int choice = sc.nextInt();
		sc.nextLine();

		return choice;
	}

	public int getRandomNumber(int n1, int n2) // 랜덤숫자 만들기
	{
		return (int) (Math.random() * (n2 - n1 + 1)) + n1;
	}

	public String createAccountNum(int accountType) {

		String accountNumber;
		int num1 = getRandomNumber(100, 999);
		int num2 = getRandomNumber(1000, 9999);

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
					System.out.println("============ 계좌번호 중복이 발생했음 ============= 테스트코드 ");
					check = true;
					break;
				} else { // 중복 X
					System.out.println("============ 계좌번호 중복이 발생하지 않음 ============= 테스트코드 ");
					check = false;
				}
			}
		}
		return check;
	}

	public Account createAccount() {

		int selectResult = selectAccount(); // 계좌선택한 결과 찾기
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
		
		if(index == -1){ // 중복이 없으면 
			clientManager.getClient(clientManager.getClientCnt()-1).setAccount(account);
		}else{ // 중복이 있으면 
			clientManager.getClient(index).setAccount(account);
		}
	}
	
	public void showAccountInfo(int index) {
		System.out.println("==== 모든 계좌 정보가 표시됩니다 ====");
		for(int i=0; i<clientManager.getClient(index).getAccountCnt(); i++) {
			System.out.println("계좌번호 : "+ clientManager.getClient(index).getAccount(i).getAccountNum() +" // 잔액 : "+ clientManager.getClient(index).getAccount(i).getBalance());
		}
	}
	
	public int selectDeleteType() {
		Scanner sc = new Scanner(System.in);
		System.out.println("삭제하고자 하는 유형을 선택하세요");
		System.out.println("1. 전체 계좌 삭제");
		System.out.println("2. 특정 계좌 삭제 (계좌번호 입력 필요)");
		System.out.print("선택 >> ");
		
		int selectResult = sc.nextInt();
		sc.nextLine();
		
		return selectResult;
	}
	
	public int checkLeftBalance(int duplicateNameIndex, int deleteType) {
		
		Scanner sc = new Scanner(System.in);
		int balanceResultIndex = 0;
		String selectedAccountNum;
		
		if(deleteType == 1) { // 고객이 갖고 있는 전체 계좌 잔액 확인 
			for(int i=0; i<clientManager.getClient(duplicateNameIndex).getAccountCnt(); i++) {
				if(clientManager.getClient(duplicateNameIndex).getAccount(i).getBalance() != 0 ){ // 갖고있는 계좌 중 잔액이 0원이 아닌 계좌가 하나라도 있는 경우 
					System.out.println("계좌삭제못한다 ******************** 테스트코드 ***************** ");
					balanceResultIndex = -1;
					break; // 하나라도 잔고가 0원이 아니면 삭제 불가하므로 for문을 빠져나간다. 
				}else{
					System.out.println("계좌삭제할수있음 ******************** 테스트코드 ***************** ");
					balanceResultIndex = -2;
					// 첫번째 계좌만 걸리는건가? 
				}
			}
		}else{ // 특정 계좌 잔액 확인
			System.out.println("계좌번호를 입력하세요");
			System.out.print("입력 >> ");
			selectedAccountNum = sc.nextLine();
			
			for(int i=0; i<clientManager.getClient(duplicateNameIndex).getAccountCnt(); i++) { // 해당 이름의 위치에서 갖고 있는 계좌 수만큼 반복하면서 	
				if(selectedAccountNum.equals(clientManager.getClient(duplicateNameIndex).getAccount(i).getAccountNum())){  // 입력받은 계좌번호와 동일한 계좌번호를 찾은 다음 
					if(clientManager.getClient(duplicateNameIndex).getAccount(i).getBalance() != 0) { // 해당 계좌의 잔액이 0원이 아니면 삭제 불가 
						System.out.println("삭제 못한다 ******************** 테스트코드 ***************** ");
						balanceResultIndex = -1;
					}else{ // 해당 계좌의 잔액이 0원이면 삭제 가능 
						System.out.println("찾았다 ******************** 테스트코드 *****************");
						balanceResultIndex = i; // 해당 계좌의 위치 반환 
					}
					break;
				}else{ // 계좌번호와 일치하는 정보가 없음
					
				}
			}
		}
		return balanceResultIndex;
	}
	
	public void deleteSelectedAccount(int duplicateNameIndex, int balanceResultIndex){
		
		switch(balanceResultIndex) {
		case -1:
			System.out.println("계좌를 삭제할 수 없습니다. 계좌의 잔액이 0원인지 확인하세요.");
			// 모든 계좌정보 보여주기 
			showAccountInfo(duplicateNameIndex);
			break;
		case -2:
			clientManager.getClient(duplicateNameIndex).clearAllAccount();
			clientManager.setClearClient(duplicateNameIndex);
			break;
		default: // 인덱스가 넘어오는 경우 
			clientManager.getClient(duplicateNameIndex).clearSpecificAccount(balanceResultIndex);
			clientManager.getClient(duplicateNameIndex).rearrangeAccount(balanceResultIndex);
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
	
	public void printClientInfo(int index) {
		for(int i=0; i<clientManager.getClientCnt(); i++) {
			if(clientManager.getClient(index).getName().compareTo(clientManager.getClient(i).getName())==0) {
				clientManager.getClient(index).showClientInfo();
				return;
			}
		}
	}
	
	public void printAccountInfo(int index) {
		
		int leftBalance = 0;
		int accountType = 0;
		String tempAccount;
		
		for(int i=0; i<clientManager.getClient(index).getAccountCnt(); i++) {
			// 계좌 종류 및 잔고 출력
			
			tempAccount = clientManager.getClient(index).getAccount(i).getAccountNum();
			
			if(tempAccount.substring(0, 4).compareTo("001")==0){
				accountType = -1; 
			}else{
				accountType = 1;
			}
			System.out.println("잔액: ");
		}
	}
	
}
