package vehicles;
import java.util.Objects;

import javax.swing.ImageIcon;

import vehicles.Enums.EnergyRank;
import vehicles.Enums.RoadType;
public class Bicycle extends Abstractbicycle implements Non_Motorized 
{
	private EnergyRank Energy;
	private String Source;
	ImageIcon image;
	public Bicycle(String name, float speed,ImageIcon image) 
	{
		super(name, 2, speed, 2, RoadType.Both);
		Energy = EnergyRank.A;
		Source = "Manual";
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
	public EnergyRank Energetic() 
	{
		return Energy;
	}

	@Override
	public String getEnergySource() 
	{
		return Source;
	}
	public String toString()
    {
		return super.toString() + " with " + Energy + " energy rank from " + Source; 
    }
	// Override the equals method to check if two Bicycle objects are equal
	public boolean equals(Object x) 
	{		 
		if (!(x instanceof Bicycle)) 
		    {
		        return false;
		    }
		else 
		{
			Bicycle other = (Bicycle) x;
			return super.equals(other) && Objects.equals(Source ,other.Source);
		}
	}
}
