package vehicles;

import java.awt.Color;

import javax.swing.ImageIcon;

public class statusandcolor_Decorator extends vehicle_Decorator implements Vehicle_Interface
{
	private String status;
	private Color color;
    public statusandcolor_Decorator(vehicles decoratedVehicle , Color color) 
    {
		super(decoratedVehicle);
		this.status = "In stock";
		this.color = color;
		// TODO Auto-generated constructor stub
	}
    @Override
    public String getStatus() 
    {
        return status;
    }

    @Override
    public void setStatus(String status) 
    {
        this.status = status;
    }

    @Override
    public String toString() 
    {
        return decoratedVehicle.toString() + "status: " + status;
    }

	@Override
	public Color getColor() 
	{
		// TODO Auto-generated method stub
		return color;
	}
	public void resestKm()
	{
		decoratedVehicle.resetKm();
	}
	@Override
	public ImageIcon getImage() 
	{
		return decoratedVehicle.getImage();
	}

	public void move(float km)
	{
		decoratedVehicle.move(km);
	}
	public void setColor(Color color) 
	{
		// TODO Auto-generated method stub
		this.color = color;
	}
	public vehicles getVehicle()
	{
		return decoratedVehicle;
	}
	
}