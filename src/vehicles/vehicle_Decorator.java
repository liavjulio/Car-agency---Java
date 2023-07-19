package vehicles;

import java.awt.Color;

public abstract class vehicle_Decorator extends vehicles implements Vehicle_Interface 
{
    protected vehicles decoratedVehicle;

    public vehicle_Decorator(vehicles decoratedVehicle) 
    {
        super(decoratedVehicle.Name,decoratedVehicle.Num_Of_Passangers,decoratedVehicle.Max_speed);
        this.decoratedVehicle = decoratedVehicle;
    }
}