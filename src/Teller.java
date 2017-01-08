import java.util.Scanner;

class Teller {

	private Client[] client = new Client[100]; // 길이 설정을 하지 않으면 NullPointerException 에러 발생
	int ctCnt = 0;

	Scanner sc = new Scanner(System.in);
	

	public int getDuplicateNameIndex(String name) { // 이름 중복 확인 위한 메소드. 이름 있으면 해당 인덱스(i) 리턴, 없으면 -1 리턴 

		for (int i = 0; i < ctCnt; i++) { // 중복 있는 경우 
			if (name.compareTo(client[i].getName()) == 0) {
				return i;
			}
		}
		return -1; // 없는 경우 
	}

	public String typeName() {
		System.out.println("====== 계좌를 개설합니다 ======");
		System.out.print("1.이름 입력 : ");
		String newName = sc.nextLine();

		return newName;
	}

	public void typeClientData(String name, int index) { // 신규인지 기존인지 확인 후 회원정보까지 입력 
											
		if (index == -1) {
			System.out.println("신규 고객입니다. 하단에 추가 정보를 입력하세요.");
			System.out.print("2.주소 입력 : ");
			String newAdd = sc.nextLine();
			System.out.print("3.핸드폰 번호 입력 : ");
			String newPhoneNum = sc.nextLine();
			System.out.println("신용등급은 자동으로 만들어집니다");
			String newCredit = "8";

			client[ctCnt++] = new Client(name, newAdd, newPhoneNum, newCredit);
		} else {
			System.out.println("기존 고객입니다. 해당 정보에 계좌만 추가합니다.");
		}
	}

	public int selectAccount() { // 계좌종류 선택 화면
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
		int num2 = getRandomNumber(100, 9999);

		if (accountType == 1) {
			accountNumber = "001" + "-" + String.valueOf(num1) + "-" + String.valueOf(num2);
		} else {
			accountNumber = "002" + "-" + String.valueOf(num1) + "-" + String.valueOf(num2);
		}
		return accountNumber;
	}

	public boolean checkAccountDuplicate(String accountNum) {
		boolean check = false;

		for (int i = 0; i < ctCnt; i++) {
			for (int j = 0; j < client[i].getAccountCnt(); j++) {
				if (accountNum.compareTo(client[i].getAccount(j).getAccountNum()) == 0) { // 모든 고객의 계좌번호 체크, 충복 발생 
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
			account = new CheckAccount(tempAccountNum, 0);
		} else {
			account = new MinusAccount(tempAccountNum, 0, "8");
		}

		return account;
	}
	
	public void accountToClient(int index, Account account){
		
		Account[] tempAccountArr = new Account[100]; 
		
		if(index == -1){ // 중복이 없으면 
	
			int newIndex = client[ctCnt-1].getAccountCnt(); // 고객의 계좌 인덱스 위치를 갖고 온다. 
						
			client[ctCnt-1].setAccountArray(tempAccountArr);
			tempAccountArr[newIndex] = new Account(account.getAccountNum(), account.getBalance());
			
			System.out.println(tempAccountArr[newIndex].getAccountNum() + "========== 계좌번호 체크 =================");
//			System.out.println(tempAccountArr[newIndex].getBalance() + "========== 잔액 체크 =================");
//			System.out.println(client[ctCnt-1].getAccount(newIndex).accountNum + "========== 계좌 체크 =================");
//			System.out.println(client[ctCnt-1].getAccountArray()[newIndex].accountNum + "========== 계좌배열 체크 =================");
			
			newIndex++;
			client[ctCnt-1].setAccountCnt(newIndex); // accountCnt를 하나 증가시킨다
			
			System.out.println(client[ctCnt-1].getAccountCnt()  + "========== accountCnt 체크 =================");
			
		}else{ // 중복이 있으면 
			
			int duplicateIndex = client[index].getAccountCnt();
			
			client[index].setAccountArray(tempAccountArr);
			tempAccountArr[duplicateIndex] = new Account(account.getAccountNum(), account.getBalance());
			
			System.out.println(tempAccountArr[duplicateIndex].getAccountNum() + "========== 계좌번호체크 =================");
//			System.out.println(tempAccountArr[duplicateIndex].getBalance() + "========== 잔액 체크 =================");
//			System.out.println(client[index].getAccount(duplicateIndex).accountNum + "========== 계좌 체크 =================");
//			System.out.println(client[index].getAccountArray()[duplicateIndex].accountNum + "========== 계좌속성 체크 =================");
			
			duplicateIndex++;
			client[index].setAccountCnt(duplicateIndex);
			
			System.out.println(client[index].getAccountCnt() + "========== accountCnt 체크 =================");
			
		}
	}

	public void openAccount() {
		String tempName = typeName(); // 이름을 입력 받고 
		int resultIndex = getDuplicateNameIndex(tempName); // 입력받은 이름의 중복 위치 인덱스를 넘겨받고 
		
		typeClientData(tempName, resultIndex); // 이름으로 기존고객/신규고객 확인, 신규고객일 경우 새로운 Client 정보 추가 
		System.out.println("=======테스트코드 =============== 기존/신규고객 확인 완료 ========");

		Account account = createAccount(); // 계좌 선택에 따른 종류별 계좌 생성  
		System.out.println("=======테스트코드 =============== 종류별 계좌 생성 완료 ========");
		
		accountToClient(resultIndex, account);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public int showSelectCloseMenu(){
		Scanner sc = new Scanner(System.in);
		
		System.out.println("어떤 계좌를 삭제하시겠습니까??");
		System.out.println("1. 모든 계좌 ");
		System.out.println("2. 특정 계좌 ");
		System.out.print("선택 >>  ");
		
		int close = sc.nextInt();
		sc.nextLine();
		
		return close;
	}
	
	public int checkLeftBalance(int closeResult){
	
		Scanner sc = new Scanner(System.in);
		int tempDeleteIndex = 0;
		
		if(closeResult == 1){ // 모든 계좌 삭제 
			for(int i=0; i<ctCnt; i++){
				for(int j=0; j<client[i].getAccountCnt(); j++){
					if(client[i].getAccount(j).getBalance() != 0){ // 잔액이 0이 아닌 경우 삭제 불가 
						System.out.println("잔액이 0원이 아니면 계좌를 삭제할 수 없습니다. ");
					}else{ // 잔액이 0인 경우 계좌 삭제 
						tempDeleteIndex = -1; // 모든 계좌를 삭제해도 되는 경우 -1 반환 
					}
				}
			}
		}else{ // 특정 계좌 삭제
			System.out.println("삭제하고싶은 계좌번호를 입력하세요.");
			System.out.print("입력 >> ");
			String closeAccountNum = sc.nextLine();
			
			for(int i=0; i<ctCnt; i++){
				for(int j=0; j<client[i].getAccountCnt(); j++){
					if(client[i].getAccount(j).getAccountNum().compareTo(closeAccountNum)==0){ // 계좌번호가 같은걸 찾아서 
						if(client[i].getAccount(j).getBalance() != 0){
							System.out.println("잔액이 0원이 아니면 계좌를 삭제할 수 없습니다. ");
						}else{
							tempDeleteIndex = j; 
						}
					}else{
						System.out.println("동일한 계좌번호를 찾을 수 없습니다. ");
					}
				}
			}
		}
		
		return tempDeleteIndex;
	}
	
	public void deleteSelectedAccount(int deleteIndex){

		if(deleteIndex == -1){ // 모든계좌 삭제일 경우 
			for(int i=0; i<ctCnt; i++){
				client[i].setAccountArray(null);
			}
		}else{
			for(int i=0; i<ctCnt; i++){ // 배열 이동 필요 ex) arr[i] = arr[i+1] 
				
			}
		}
	}

	public void closeAccount() {
		
		int closeResult = showSelectCloseMenu(); // 모든 or 특정계좌 삭제 선택 
		
		int deleteIndex = checkLeftBalance(closeResult); // 잔액 확인 
		System.out.println(deleteIndex);
		// deleteSelectedAccount(deleteIndex)
		
	}

	public void showClient() {

	}

}
