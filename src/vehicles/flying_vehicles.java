package vehicles;
import vehicles.Enums.Use;


import javax.swing.ImageIcon;

public abstract class flying_vehicles extends vehicles 
{
    
    // instance variable of the flying_vehicles class
    private Use typeOfUse;
    
    // constructor method of the flying_vehicles class
    public flying_vehicles(String name, int passangers, float speed, Use TypeOfUse) 
    {
        // calls the constructor of the superclass (vehicles) and passes in the name, number of passengers, and maximum speed of the flying vehicle
        super(name, passangers, speed);
        // initializes the type of use of the flying vehicle
        this.typeOfUse = TypeOfUse;
    }
    
    // method to return a string representation of the flying vehicle
    public String toString() 
    {
        // calls the toString method of the superclass (vehicles) and appends the number of passengers and type of use of the flying vehicle
        return super.toString() + " with " + Num_Of_Passangers + " passengers, and for " + typeOfUse + " use.";
    }
    
    // method to check if two flying vehicle instances are equal
    public boolean equals(Object x) 
    {
        if (! (x instanceof flying_vehicles)) 
        {
            // returns false if the parameter object is not an instance of the flying_vehicles class
            return false;
        }
        else {
            flying_vehicles other = (flying_vehicles) x;
            // returns true if the superclass (vehicles) method equals returns true and the type of use of the two vehicles are equal
            return super.equals(other) && this.typeOfUse == other.typeOfUse;
        }
    }
    public abstract ImageIcon getImage();
}