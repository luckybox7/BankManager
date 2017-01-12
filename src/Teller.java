import java.util.Scanner;

class Teller {
	
	BankManageHandler bankManageHandler = new BankManageHandler();
	BankManageIOHandler bankManageIOHandler = new BankManageIOHandler();
	
	public void openAccount() {
		
		System.out.println("====== 계좌를 개설합니다 ======");
		String tempName = bankManageIOHandler.typeName(); // 이름을 입력 받고 
		int resultIndex = bankManageHandler.findNameGetIndex(tempName); // 입력받은 이름의 중복 위치 인덱스를 넘겨받고 
		bankManageHandler.addNewClient(tempName, resultIndex); // 이름으로 기존고객/신규고객 확인, 신규고객일 경우 새로운 Client 정보 추가
		Account account = bankManageHandler.createAccount(); // 계좌 선택에 따른 종류별 계좌 생성  
		bankManageHandler.accountToClient(resultIndex, account); // 계좌 정보를 고객에 연결 
	}
	
	public void closeAccount() {
		
		String typedName = bankManageIOHandler.typeName(); // 이름입력 받고
		int findNameResultIndex = bankManageHandler.findNameGetIndex(typedName); // 이름찾고 ( 찾으면 인덱스값 or 없으면 -1)
		bankManageHandler.showAllAccountInfo(findNameResultIndex); // 갖고있는 계좌정보 다 보여주고(계좌번호, 잔액 등등) 
		int deleteType = bankManageIOHandler.selectDeleteType(); // 전체 삭제 or 특정 삭제 입력받고
		int leftBalanceResult = bankManageHandler.checkLeftBalance(findNameResultIndex, deleteType); // 전체삭제일 경우 계좌의 잔액을 모두 확인, 특정삭제일 경우 계좌번호를 입력받아 해당 계좌의 잔액 확인		
		bankManageHandler.deleteSelectedAccount(findNameResultIndex, leftBalanceResult); // 잔액 확인 후 계좌 삭제 
	}

	public void showClient() {
		
		int printedType = bankManageIOHandler.selectPrintType();
		
		switch(printedType){
		case 1:
			bankManageHandler.printAllInfo();
			break;
		case 2:
			String selectedName = bankManageIOHandler.typeName();
			bankManageHandler.printSpecificClientInfo(selectedName);
			break;
		}
	}
}
