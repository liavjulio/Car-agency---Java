package vehicles;

import javax.swing.ImageIcon;

public class landFactory implements abstractFactory
{
	public vehicles create(int num, ImageIcon image ,String... args) 
	{
		vehicles vehicle = null;
		switch (num) 
		{
			case 0: 
			{
				vehicle = new Jeep(args[0], Float.parseFloat(args[1]) , Float.parseFloat(args[2]), Integer.parseInt(args[3]),image);
				break;
			}
			case 1:
			{
				vehicle = new electric_bicycle(args[0],Float.parseFloat(args[1]),Float.parseFloat(args[2]),image);
				break;
			}
			case 2:
			{
				vehicle = new Bicycle(args[0],Float.parseFloat(args[1]),image);
				break;
			}
		}
		return vehicle;
	}
}
