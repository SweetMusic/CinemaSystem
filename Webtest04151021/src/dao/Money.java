package dao;

public class Money {

	public double tax(double gain)
	{
		double money = gain - 1600;
		double finalMoney = 0;
		if(money < 0)
		{
			finalMoney = 0;
		}
		else if(money <= 500)
		{
			finalMoney = money * 0.05 - 0;
		}
		else if(money <=3000)
		{
			finalMoney = money * 0.1 - 25;
		}
		else if(money <= 5000)
		{
			finalMoney = money * 0.15 - 125;
		}
		else if(money <= 20000)
		{
			finalMoney = money * 0.2 - 375;
		}
		else if(money <= 40000)
		{
			finalMoney = money * 0.25 - 1375;
		}
		else if(money <= 60000)
		{
			finalMoney = money * 0.30 - 3375;
		}
		else if(money <= 80000)
		{
			finalMoney = money * 0.35 - 6375;
		}
		else if(money <= 100000)
		{
			finalMoney = money * 0.40 - 10375;
		}
		else
		{
			finalMoney = money * 0.45 - 15375;
		}
		return finalMoney;
	}
}
