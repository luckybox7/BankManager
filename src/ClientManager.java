import java.io.Serializable;
import java.util.ArrayList;

public class ClientManager implements Serializable{

	private ArrayList<Client> clientList;
	
	public ClientManager() {
		clientList = new ArrayList<Client>();
	}
	
	public void insertClient(Client client) {
		clientList.add(client);	
	}
	
	public Client getClient(int index) {
		return clientList.get(index);
	}
	
	public void clearClient(int index) { // 이름바꾸기 -> 기능: 고객 정보 하나 삭제하는 것.	
		clientList.remove(index);
	}
	
	public ArrayList<Client> getClientList() {
		return clientList;
	}
}
