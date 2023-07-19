package vehicles;
import java.util.Objects;

import javax.swing.ImageIcon;

public abstract class sea_vehcles extends vehicles 
{
    // Fields specific to sea vehicles
    private boolean wind_direction;
    private String flag;
    
    // Constructor for sea vehicles
    public sea_vehcles(String name, int passangers, float speed,boolean wind_direction,String flag) 
    {
        // Call the constructor of the parent class (vehicles)
        super(name,passangers, speed);
        
        // Set the fields specific to sea vehicles
        this.wind_direction = wind_direction;
        this.flag = new String(flag);
    }
    public String getflag() 
    {
    	return flag;
    }
    // Method to get a string representation of the sea vehicle
    public String toString()
    {
        // Check if the wind direction is against or with the vehicle
        if (wind_direction == false) 
        {
            return super.toString() + " under " + flag + " flag " + " and " + " against wind direction ";
        }
        else 
        {
            return super.toString() + " under " + flag + " flag " + " and " + " with the wind direction ";
        }
    }
    
    // Method to compare two sea vehicles and check if they are equal
    public boolean equals(Object x) 
    {
        if (!(x instanceof sea_vehcles)) 
        {
            return false;
        }
        else 
        {
            sea_vehcles other = (sea_vehcles) x;
            
            // Compare the fields specific to sea vehicles as well as the fields from the parent class
            return super.equals(other) && this.wind_direction == other.wind_direction && Objects.equals(this.flag, other.flag);
        }
    }
    public boolean getwind()
    {
    	return wind_direction;
    }
   
    // Method to set the flag of the sea vehicle
    public void setflag(String flag) 
    {
        this.flag = String.valueOf(flag);
    }
    public abstract ImageIcon getImage();
}