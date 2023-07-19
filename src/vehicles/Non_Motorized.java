package vehicles;
import vehicles.Enums.EnergyRank;

// Interface representing non-motorized vehicles
public interface Non_Motorized 
{
	// Returns the energy rank of the vehicle
	public EnergyRank Energetic();
	
	// Returns the energy source of the vehicle
	public String getEnergySource();
}