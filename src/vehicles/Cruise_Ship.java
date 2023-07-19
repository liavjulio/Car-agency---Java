package vehicles;
import javax.swing.ImageIcon;

import vehicles.Enums.licenceType;

public class Cruise_Ship extends sea_vehcles implements Motorized , licence
{
	private float EngineLife;
	private float AvarageFuel;
	private licenceType Licence;
	private ImageIcon image;
	public Cruise_Ship(String name, int passangers, float speed, String flag , float LifeOfEngine ,float Fuel,ImageIcon image) 
	{
		super(name, passangers, speed, true, flag);
		EngineLife = LifeOfEngine;
		AvarageFuel = Fuel;
		Licence = licenceType.Unlimit;
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
	@Override
	public licenceType GetLicence() 
	{
		return Licence;
	}

	@Override
	public float GetAvarageFuel() 
	{
		return AvarageFuel;
	}

	@Override
	public float GetAvarageEngineLife() 
	{
		return EngineLife;
	}
	// Override the superclass toString() method to add Cruise_Ship-specific information
    public String toString() 
    {
        return super.toString() + " average fuel: " + AvarageFuel + " and engine life: " + EngineLife + " " + Licence + " licence "; 
    }
    
	// Override the superclass equals() method to add Cruise_Ship-specific comparisons
    public boolean equals(Object x) 
    {
        if (!(x instanceof Cruise_Ship)) 
        {
            return false;
        } 
        else
        {
            Cruise_Ship other = (Cruise_Ship) x;
            return super.equals(other) && this.EngineLife == other.EngineLife && this.AvarageFuel == other.AvarageFuel;
        }
    }

}
