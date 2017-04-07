
public class Constant {

	public static interface INIT_MENU {
		int TELLER = 1, CLIENT = 2, EXIT = 3;
	}

	public static interface TELLER_MENU {
		int OPEN_ACCOUNT = 1, CLOSE_ACCOUNT = 2, SHOW_CLIENT = 3, EXIT = 4;
	}

	public static interface CLIENT_MENU {
		int DEPOSIT = 1, WITHDRAW = 2, ACCOUNT_TRANSFER = 3, SHOW_BALANCE = 4, SHOW_STATEMENT = 5, EXIT = 6;
	}
	
	public static interface ACCOUNT_TYPE {
		int CHECK_ACCOUNT = 1, MINUS_ACCOUNT = 2;
	}
	
	public static interface TRNASACTION_TYPE {
		int DEPOSIT = 1, WITHDRAW = 2, TRANSFER_DEPOSIT = 3, TRANSFER_WITHDRAW = 4;
	}
	
	public static interface FOUND_RESULT {
		int NOT_FOUND = -1, CANNOT_DELETE = -2, DELETE_ALL = -3;
	}
	
	public static interface DELETE_TYPE {
		int ALL = 1, SPECIFIC = 2;
	}
	
	public static interface DECISION {
		int YES = 1, NO = 2;
	}
	
	public static interface PRINT_TYPE {
		int ALL = 1, SPECEFIC = 2;
	}
	
	public static interface CREDIT_GRADE {
		int CREDIT_BASIC = 8;
	}
}
