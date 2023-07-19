package vehicles;


import javax.swing.ImageIcon;

public class Frigate extends sea_vehcles implements Motorized  
{
	private float EngineLife = 4; // Engine life of the frigate
	private float AvarageFuel = 500; // Average fuel consumption of the frigate
	private ImageIcon image;
	// Constructor for creating a new Frigate object
	public Frigate(String name, int passangers, float speed, boolean wind_direction, ImageIcon image) 
	{
		super(name, passangers, speed, wind_direction, "Israel"); // Call the constructor of the super class
		this.image = image;
	}
	public ImageIcon getImage()
    {
    	return image;
    }
    public void setImage(ImageIcon image)
    {
    	this.image = image; 
    }
	// Method to get the average fuel consumption of the frigate
	public float GetAvarageFuel() 
	{
		return AvarageFuel;
	}

	// Method to get the engine life of the frigate
	public float GetAvarageEngineLife() 
	{
		return EngineLife;
	}

	// Override the toString method to display the frigate's details as a string
	public String toString()
	{
		return super.toString() + " Engine life of " + EngineLife + " and Average Fuel consumption of " + AvarageFuel ;
	}

	// Override the equals method to check if two Frigate objects are equal
	public boolean equals(Object x) 
	{
	    if (!(x instanceof Frigate)) 
	    {
	        return false;
	    }
	    else 
	    {
	        Frigate other = (Frigate) x;
	        return super.equals(other) && this.EngineLife == other.EngineLife && this.AvarageFuel == other.AvarageFuel ;
	    }
	}
}