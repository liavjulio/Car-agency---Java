package vehicles;

public class Enums 
{

  // Enumeration for license types of vehicles
  public enum licenceType 
  {
    Mini,    // License for vehicles up to 3500 kg
    Limit,   // License for vehicles up to 7500 kg
    Unlimit  // License for vehicles over 7500 kg
  }

  // Enumeration for energy ranks of vehicles
  public enum EnergyRank 
  {
    A,  // Highest energy rank
    B,
    C   // Lowest energy rank
  }

  // Enumeration for road types
  public enum RoadType 
  {
    Dirt,   // Unpaved roads
    Paved,   // Paved roads
    Both   //  Dirt and paved roads
  }

  // Enumeration for vehicle use
  public enum Use 
  {
    Army,     // Used by the army
    civilian  // Used by civilians
  }

}