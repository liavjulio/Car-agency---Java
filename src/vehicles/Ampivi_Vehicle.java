package vehicles;
import javax.swing.ImageIcon;

import vehicles.Enums.RoadType;
public class Ampivi_Vehicle extends land_vehicles implements sea_travel , Motorized
{
    // Fields specific to amphibious vehicles
	private float engineLife;
	private float avarageFuel;
	private boolean wind_direction;
    private String flag;
    private ImageIcon image;
    // Constructor for amphibious vehicles
    public Ampivi_Vehicle(String name, int passangers, float speed, int wheels, float engineLife , float avarageFuel , boolean wind , String flag , ImageIcon image) 
    {
        // Call the constructor of the parent class (land_vehicles)
        super(name, passangers, speed, wheels, RoadType.Paved);
        // Set the fields specific to amphibious vehicles
        this.avarageFuel = avarageFuel;
        this.engineLife = engineLife;
        this.wind_direction = wind;
        this.flag = String.valueOf(flag);
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
    // Method to get a string representation of the amphibious vehicle
    public String toString() 
    {
		return super.toString() + " with wind direction: " + wind_direction + " under flag of : " + flag + " with avarage Fuel of  : " + avarageFuel + " and engine life of " + engineLife; 
    }

    // Method to compare two amphibious vehicles and check if they are equal
    public boolean equals(Object x) 
    {
        if (!(x instanceof Ampivi_Vehicle)) 
        {
            return false;
        } 
        else 
        {
            Ampivi_Vehicle other = (Ampivi_Vehicle) x;
            // Compare the fields specific to amphibious vehicles as well as the fields from the parent class
            	return super.equals(other) && this.engineLife == other.engineLife && this.avarageFuel == other.avarageFuel; 
        }
    }

    // Method to set the flag of the amphibious vehicle
    public void setflag(String flag) 
    {
    	this.flag = String.valueOf(flag);
    }
    
	@Override
	public float GetAvarageFuel() 
	{
		// TODO Auto-generated method stub
		return avarageFuel;
	}

	@Override
	public float GetAvarageEngineLife() 
	{
		// TODO Auto-generated method stub
		return engineLife;
	}

	@Override
	public boolean getwind() 
	{
		// TODO Auto-generated method stub
		return wind_direction;
	}

	@Override
	public String getflag() 
	{
		// TODO Auto-generated method stub
		return flag;
	}
	@Override
	public void setFlag(String flag) {
		// TODO Auto-generated method stub
		
	}
}