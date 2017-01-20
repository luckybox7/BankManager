import java.util.ArrayList;

public class ClientManager {
	
//	private Client[] clientList;
//	private int clientCnt;
//	final private int MAX_CLIENT = 100;
	private ArrayList<Client> clientList;
	
	public ClientManager() {
		clientList = new ArrayList<Client>();
		
//		clientList = new Client[MAX_CLIENT];
//		clientCnt=0;
	}
	
//	public void setClientCnt(int clientCnt) {
//		this.clientCnt = clientCnt;
//	}
//	
//	public int getClientCnt() {
//		return clientCnt;
//	}
	
	public void setClient(Client client) {
		clientList.add(client);
		
//		this.clientList[clientCnt] = client;
//		clientCnt++;
	}
	
	public Client getClient(int index) {
		return clientList.get(index);
		
//		return clientList[index];
	}
	
	public void setClearClient(int index) { // 이름바꾸기 -> 기능: 고객 정보 하나 삭제하는 것.
		
		clientList.remove(index);
		
		
//		for(int i=index; i<clientCnt; i++) {
//			clientList[i] = clientList[i+1];
//		}
//		clientCnt = clientCnt-1;
	}
	
//	public Client[] getClientList() {
//		return clientList;
//	}
	
	public ArrayList<Client> getClientList() {
		return clientList;
	}
}
