
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
}
