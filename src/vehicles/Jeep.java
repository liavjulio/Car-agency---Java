package vehicles;
import vehicles.Enums.licenceType;
import javax.swing.ImageIcon;

import vehicles.Enums.RoadType;
public class Jeep extends land_vehicles implements Motorized, licence 
{
    // Declare class-specific instance variables
    private float EngineLife;
    private float AvarageFuel;
    private ImageIcon image;
    // Constructor for creating a new Jeep object
    public Jeep(String name, float speed, float EngineLife, float AvarageFuel, ImageIcon image) 
    {
        // Call the constructor of the superclass using the "super" keyword
        // Pass the necessary arguments to the superclass constructor
        super(name, 5, speed, 4, RoadType.Dirt);
        
        // Assign values to the instance variables
        this.AvarageFuel = AvarageFuel;
        this.EngineLife = EngineLife;
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
    // Implementation of the licence interface method
    public licenceType GetLicence()
    {
        return licenceType.Mini;
    }
    
    // Override the superclass toString() method to add Jeep-specific information
    public String toString() 
    {
        return super.toString() + " average fuel: " + AvarageFuel + " and engine life: " + EngineLife; 
    }
    
    // Implementation of the Motorized interface method
    public float GetAvarageFuel() 
    {
        return this.AvarageFuel;
    }
    
    // Another implementation of the Motorized interface method
    public float GetAvarageEngineLife() 
    {
        return this.EngineLife;
    }
    
    // Override the superclass equals() method to add Jeep-specific comparisons
    public boolean equals(Object x) 
    {
        if (!(x instanceof Jeep)) 
        {
            return false;
        } 
        else
        {
            Jeep other = (Jeep) x;
            return super.equals(other) && this.EngineLife == other.EngineLife && this.AvarageFuel == other.AvarageFuel;
        }
    }
}