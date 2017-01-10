
public class ClientManager {
	
	private Client[] client;
	private int clientCnt;
	
	public ClientManager() {
		client = new Client[100];
		clientCnt=0;
	}
	
	public void setClientCnt(int clientCnt) {
		this.clientCnt = clientCnt;
	}
	
	public int getClientCnt() {
		return clientCnt;
	}
	
	public void setClient(Client client) {
		this.client[clientCnt] = client;
		clientCnt++;
	}
	
	public Client getClient(int index) {
		return client[index];
	}
	
	public void setClearClient(int index) {
//		client[index] = null;
		
		for(int i=index; i<clientCnt; i++) {
			client[i] = client[i+1];
		}
		clientCnt = clientCnt-1;
	}
}
