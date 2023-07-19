package vehicles;

/**
 * This interface represents a motorized vehicle, which is a vehicle powered by an engine.
 */
public interface Motorized 
{
	/**
	 * Returns the average fuel consumption of the vehicle.
	 * */
	public float GetAvarageFuel();
	
	/**
	 * Returns the average engine life of the vehicle.
	 *  */
	public float GetAvarageEngineLife();
}