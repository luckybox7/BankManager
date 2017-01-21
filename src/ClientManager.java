import java.util.ArrayList;

public class ClientManager {

	private ArrayList<Client> clientList;
	
	public ClientManager() {
		clientList = new ArrayList<Client>();
	}
	
	public void setClient(Client client) {
		clientList.add(client);	
	}
	
	public Client getClient(int index) {
		return clientList.get(index);
	}
	
	public void setClearClient(int index) { // 이름바꾸기 -> 기능: 고객 정보 하나 삭제하는 것.	
		clientList.remove(index);
	}
	
	public ArrayList<Client> getClientList() {
		return clientList;
	}
}
