package vehicles;

import java.util.Objects;

import javax.swing.ImageIcon;

import vehicles.Enums.RoadType;

public class electric_bicycle extends Abstractbicycle implements Motorized 
{
	private float avaragefuel;
	private float enginelife;
	private ImageIcon image;
	public electric_bicycle(String name, float speed, float engine ,ImageIcon image) 
	{
		super(name, 2, speed, 2, RoadType.Both);
		// TODO Auto-generated constructor stub
		this.avaragefuel = 20;
		this.image = image; 
	}
	@Override
	public float GetAvarageFuel() 
	{
		// TODO Auto-generated method stub
		return avaragefuel;
	}

	@Override
	public float GetAvarageEngineLife() 
	{
		// TODO Auto-generated method stub
		return enginelife;
	}

	@Override
	public ImageIcon getImage() 
	{
		// TODO Auto-generated method stub
		return image;
	}
	public String toString()
    {
		return super.toString() + " with " + avaragefuel + " avarage fuel and " + enginelife + " engine life"; 
    }
	// Override the equals method to check if two Bicycle objects are equal
	public boolean equals(Object x) 
	{		 
		if (!(x instanceof electric_bicycle)) 
		    {
		        return false;
		    }
		else 
		{
			electric_bicycle other = (electric_bicycle) x;
			return super.equals(other) && avaragefuel == other.avaragefuel && enginelife == other.enginelife;
		}
	}

}
