import java.util.Scanner;

class Teller {
	
	Handler handler = new Handler();
	
	public void openAccount() {
		
		System.out.println("====== 계좌를 개설합니다 ======");
		String tempName = handler.typeName(); // 이름을 입력 받고 
		int resultIndex = handler.searchDuplicateName(tempName); // 입력받은 이름의 중복 위치 인덱스를 넘겨받고 
		handler.addClientData(tempName, resultIndex); // 이름으로 기존고객/신규고객 확인, 신규고객일 경우 새로운 Client 정보 추가
		Account account = handler.createAccount(); // 계좌 선택에 따른 종류별 계좌 생성  
		handler.accountToClient(resultIndex, account); // 계좌 정보를 고객에 연결 
	}
	
	public void closeAccount() {
		
		String typedName = handler.typeName(); // 이름입력 받고
		int duplicateNameIndex = handler.searchDuplicateName(typedName); // 이름찾고 ( 찾으면 인덱스값 or 없으면 -1)
		handler.showAccountInfo(duplicateNameIndex); // 갖고있는 계좌정보 다 보여주고(계좌번호, 잔액 등등) 
		int deleteType = handler.selectDeleteType(); // 전체 삭제 or 특정 삭제 입력받고
		int leftBalanceResult = handler.checkLeftBalance(duplicateNameIndex, deleteType); // 전체삭제일 경우 계좌의 잔액을 모두 확인, 특정삭제일 경우 계좌번호를 입력받아 해당 계좌의 잔액 확인		
		handler.deleteSelectedAccount(duplicateNameIndex, leftBalanceResult); // 잔액 확인 후 계좌 삭제 
	}

	public void showClient() {
		
		// 
		
	}
	
	public void showClient(String name) {
		
		int nameIndex = handler.findSameName(name); // 이름 비교해서 위치를 찾고
		handler.printClientInfo(nameIndex); // 정보를 보여준다 (이름, 주소, 전화번호, 신용등급 )
		handler.printAccountInfo(nameIndex); // 계좌 잔고 출력 ( 계좌종류, 잔고 순서 ) 
		
		
		// 종류별 총액 출력 
	}

}
