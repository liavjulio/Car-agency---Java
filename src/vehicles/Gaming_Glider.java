package vehicles;

import java.util.Objects;

import javax.swing.ImageIcon;

import vehicles.Enums.EnergyRank;
import vehicles.Enums.Use;

public class Gaming_Glider extends flying_vehicles implements Non_Motorized
{
    private String EnergySource; // A private field to hold the energy source of the glider
    private EnergyRank energyRank = EnergyRank.A; // A private field to hold the energy rank of the glider
    private Use use = Use.civilian; // A private field to hold the use of the glider
    private ImageIcon image;
    public Gaming_Glider(ImageIcon image) 
    {
        // Call the constructor of the parent class with default values
        super("Toy", 0, 10, Use.civilian);
        this.image = image;
        EnergySource = "Manual"; // Set the default value of the energy source field
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
        return energyRank; // Return the energy rank of the glider
    }

    public String getEnergySource() 
    {
        return EnergySource; // Return the energy source of the glider
    }

    @Override
    public String toString()
    {
        // Return a string representation of the glider, including all relevant fields
        return super.toString() + " With " + Num_Of_Passangers + " passangers " + " for " + use + " Use , and with " + energyRank + " energy rank , " + " From " + EnergySource + " Source "; 
    }

    @Override
    public boolean equals(Object x)
    {
        if (! (x instanceof Gaming_Glider)) 
        {
            return false; // The other object is not a glider, so they can't be equal
        }
        else 
        {
            Gaming_Glider other = (Gaming_Glider) x; // Cast the other object to a glider to access its fields

            // Check if all relevant fields of the two gliders are equal
            return super.equals(other) && this.energyRank == other.energyRank && Objects.equals(this.EnergySource, other.EnergySource);
        }
    }
}