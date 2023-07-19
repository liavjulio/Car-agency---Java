package vehicles;

import java.util.Objects;

import javax.swing.ImageIcon;

import vehicles.Enums.EnergyRank;
import vehicles.Enums.Use;

public class Spy_Glider extends flying_vehicles implements Non_Motorized
{
	private String EnergySource; // The energy source for the spy glider
	private EnergyRank energyRank = EnergyRank.C; // The energy rank of the spy glider
	private Use use = Use.Army; // The intended use of the spy glider
	private ImageIcon image;
	public Spy_Glider(String EnergySource , ImageIcon image) 
	{
		// Call the constructor of the superclass with dummy values for name and number of passengers since they're not relevant
		super("Invalid", 1, 50, Use.Army); 
		this.EnergySource = String.valueOf(EnergySource); // Set the energy source to the provided value
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
	public EnergyRank Energetic() 
	{
		return energyRank; // Return the energy rank of the spy glider
	}
	
	public String getEnergySource() 
	{
		return this.EnergySource; // Return the energy source of the spy glider
	}
	
	public String toString()
	{
		// Return a string representation of the spy glider, including its number of passengers, energy rank, energy source, and intended use
		return super.toString() + " With " + Num_Of_Passangers + " passangers" + " for " + use + " Use , and with " + energyRank + " energy rank , " + " From " + EnergySource + " Source "; 
	}
	
	public boolean equals(Object x)
	{
		if (! (x instanceof Spy_Glider)) 
		{
			return false; // If the object being compared is not a spy glider, return false
		}
		else 
		{
			Spy_Glider other = (Spy_Glider) x; // Cast the object to a spy glider to compare its fields
			return super.equals(other) && this.energyRank == other.energyRank && Objects.equals(this.EnergySource, other.EnergySource) && this.use == other.use;
			// Return true if all fields are equal, including those inherited from the superclass
		}
	}
}