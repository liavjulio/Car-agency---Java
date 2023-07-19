package vehicles;



import javax.swing.ImageIcon;

import vehicles.Enums.RoadType;

public abstract class land_vehicles extends vehicles 
{
    
    // instance variables of the land_vehicles class
    private int numOfWheels;
    private RoadType Type;
    
    // constructor method of the land_vehicles class
    public land_vehicles(String name, int passangers, float speed, int wheels, RoadType Type) 
    {
        // calls the constructor of the superclass (vehicles) and passes in the name, number of passengers, and maximum speed of the land vehicle
        super(name, passangers, speed);
        // initializes the number of wheels and type of road the vehicle is driving on
        this.numOfWheels = wheels;
        this.Type = Type;
    }
    
    // method to return a string representation of the land vehicle
    public String toString() 
    {
        // calls the toString method of the superclass (vehicles) and appends the number of wheels and type of road the vehicle is driving on
        return super.toString() + " with " + numOfWheels + " wheels, and driving on " + Type + " roads.";
    }
    
    // method to check if two land vehicle instances are equal
    public boolean equals(Object x) 
    {
        if (! (x instanceof land_vehicles)) 
        {
            // returns false if the parameter object is not an instance of the land_vehicles class
            return false;
        }
        else 
        {
            land_vehicles other = (land_vehicles)x;
            // returns true if the superclass (vehicles) method equals returns true and the number of wheels of the two vehicles are equal
            return super.equals(other) && this.numOfWheels == other.numOfWheels;
        }
    }
    public abstract ImageIcon getImage();
}