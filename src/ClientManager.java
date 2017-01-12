import java.util.ArrayList;
import java.util.Arrays;

public class ClientManager {
	
	private Client[] clientList;
	private int clientCnt;
	final private int MAX_CLIENT = 100;
	private Client[] tempClientList; // 정렬을 위한 임시배열 추가 
	
	public ClientManager() {
		clientList = new Client[MAX_CLIENT];
		clientCnt=0;
	}
	
	public void setClientCnt(int clientCnt) {
		this.clientCnt = clientCnt;
	}
	
	public int getClientCnt() {
		return clientCnt;
	}
	
	public void setClient(Client client) {
		this.clientList[clientCnt] = client;
		clientCnt++;
	}
	
	public Client getClient(int index) {
		return clientList[index];
	}
	
	public void setClearClient(int index) {
//		client[index] = null;
		
		for(int i=index; i<clientCnt; i++) {
			clientList[i] = clientList[i+1];
		}
		clientCnt = clientCnt-1;
	}
	
	public Client[] getClientList() {
		return clientList;
	}
	
	public void setSortClientList() { // 가나다 순 정렬하기 
		tempClientList = new Client[MAX_CLIENT];	
		
	}
}
