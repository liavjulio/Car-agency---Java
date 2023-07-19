package vehicles;

import java.awt.image.BufferedImage;
import java.util.Objects;

import javax.swing.ImageIcon;

public abstract class vehicles 
{
    
    // instance variables of the vehicles class
    protected String Name;
    protected float Km, Max_speed;
    protected int Num_Of_Passangers;
    protected BufferedImage image;
    protected boolean isOnTry;
    protected boolean isOnBuy;
    // constructor method of the vehicles class
    public vehicles(String name, int passangers, float speed) 
    {
        // initializes the name, number of passengers, and maximum speed of the vehicle
        Name = new String(name);
        Num_Of_Passangers = passangers;
        Max_speed = speed;
        isOnTry = false;
        // sets the initial value of the distance traveled by the vehicle to 0
        Km = 0;
    }
    public boolean isOnTry()
    {
    	return isOnTry;
    }
    public void setTry(boolean istry)
    {
    	isOnTry = istry;
    }
    public boolean isOnBuy()
    {
    	return isOnBuy;
    }
    public void setBuy(boolean isbuy)
    {
    	isOnBuy = isbuy;
    }
    // method to update the distance traveled by the vehicle
    public void move(float km) 
    {
        // adds the distance passed as a parameter to the distance traveled by the vehicle
        this.Km += km;
    }
    
    // method to return a string representation of the vehicle
    public String toString() 
    {
        // returns a string including the name, distance traveled, and maximum speed of the vehicle
        return ("model : " + this.Name + ", traveled : " + this.Km + ", Max speed of " + this.Max_speed);
    }
    
    // method to check if two vehicle instances are equal
    public boolean equals(Object x) 
    {
        if (!(x instanceof vehicles)) 
        {
            // returns false if the parameter object is not an instance of the vehicles class
            return false;
        }
        else 
        {
            vehicles other = (vehicles) x;
            // returns true if the name, number of passengers, and maximum speed of the two vehicles are equal
            return this.Max_speed == other.Max_speed && this.Num_Of_Passangers == other.Num_Of_Passangers && Objects.equals(this.Name, other.Name);
        }
    }
    
    // method to return the name of the vehicle
    public String getName() 
    {
        // returns the name of the vehicle instance
        return Name;
    }
    
    // method to reset the distance traveled by the vehicle
    public void resetKm() 
    {
        // sets the distance traveled by the vehicle to 0
        this.Km = 0;
        System.out.println(toString());
    }
    public abstract ImageIcon getImage();
    
}