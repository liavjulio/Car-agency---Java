package vehicles;

import javax.swing.ImageIcon;

import vehicles.Enums.Use;

public class Hybrid_Plane extends Ampivi_Vehicle implements fly_travel
{
	private Use usage = Use.Army;
	public Hybrid_Plane(String name, int passangers, float speed, int wheels, float engineLife, float avarageFuel , boolean wind, String flag, ImageIcon image) 
	{
		super(name, passangers, speed, wheels, engineLife, avarageFuel, wind, flag, image);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() 
    {
		return super.toString() + usage + " Use ";
    }
	 public boolean equals(Object x) 
	 {
		 	if (!(x instanceof Hybrid_Plane)) 
	        {
	            return false;
	        } 
	        else 
	        {
	            Hybrid_Plane other = (Hybrid_Plane) x;
	            // Compare the fields specific to amphibious vehicles as well as the fields from the parent class
	            	return super.equals(other) && usage == other.usage;
	        }
	 }
	@Override
	public Use getUse() 
	{
		// TODO Auto-generated method stub
		return usage;
	}
}
