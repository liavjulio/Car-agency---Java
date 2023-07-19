package vehicles;
import java.util.Objects;

import javax.swing.ImageIcon;
import vehicles.Enums.RoadType;
public abstract class Abstractbicycle extends land_vehicles 
{
	public Abstractbicycle(String name, int passangers, float speed, int wheels, RoadType Type) 
	{
		super(name, passangers, speed, wheels, Type);
		// TODO Auto-generated constructor stub
	}
	public abstract ImageIcon getImage();
	public String toString()
    {
		return super.toString(); 
    }
	// Override the equals method to check if two Bicycle objects are equal
	public boolean equals(Object x) 
	{		 
		if (!(x instanceof Abstractbicycle)) 
		    {
		        return false;
		    }
		else 
		{
			Abstractbicycle other = (Abstractbicycle) x;
			return super.equals(other);
		}
	}
}
