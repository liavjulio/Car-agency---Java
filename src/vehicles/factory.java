package vehicles;
public class factory 
{
	public static abstractFactory getFact(int num) 
	{
		abstractFactory factory = null;
		switch (num) 
		{
			case 0: 
			{
				factory = new flyFactory();
				break;
			}
			case 1:
			{
				factory = new landFactory();
				break;
			}
			case 2:
			{
				factory = new seaFactory();
				break;
			}
		}
	return factory;
	}
}
