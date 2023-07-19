package vehicles;

import javax.swing.ImageIcon;

public class seaFactory implements abstractFactory
{
	public vehicles create(int num, ImageIcon image ,String... args) 
	{
		vehicles vehicle = null;
		
		switch (num) 
		{
			case 0: 
			{
				vehicle = new Ampivi_Vehicle(args[0], Integer.parseInt(args[1]) , Float.parseFloat(args[2]), Integer.parseInt(args[3]), Float.parseFloat(args[4]) , Float.parseFloat(args[5]) , Boolean.parseBoolean(args[6]),args[7], image);
				break;
			}
			case 1:
			{
				vehicle = new Cruise_Ship(args[0],Integer.parseInt(args[1]), Float.parseFloat(args[2]),args[3],Float.parseFloat(args[4]),Float.parseFloat(args[5]),image);
				break;
			}
			case 2:
			{
				vehicle = new Frigate(args[0], Integer.parseInt(args[1]) , Float.parseFloat(args[2]), Boolean.parseBoolean(args[3]),image);
				break;
			}
		}
		return vehicle;
	}
}
