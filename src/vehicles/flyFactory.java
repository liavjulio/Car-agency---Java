package vehicles;

import javax.swing.ImageIcon;

public class flyFactory implements abstractFactory
{
	public vehicles create(int num, ImageIcon image ,String... args) 
	{
		vehicles vehicle = null;
		
		switch (num) 
		{
			case 0: 
			{
				vehicle = new Gaming_Glider(image);
				break;
			}
			case 1:
			{
				vehicle = new Hybrid_Plane(args[0] , Integer.parseInt(args[1]),Float.parseFloat(args[2]),Integer.parseInt(args[3]), Float.parseFloat(args[4]) , Float.parseFloat(args[5]) , Boolean.parseBoolean(args[6]),args[7], image);
				break;
			}
			case 2:
			{
				vehicle = new Spy_Glider(args[0],image);
				break;
			}
		}
		return vehicle;
	}
}
