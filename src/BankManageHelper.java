
class BankManageHelper {

	public int getRandomNumber(int n1, int n2) // 랜덤숫자 만들기
	{
		return (int) (Math.random() * (n2 - n1 + 1)) + n1;
	}
}
