package wcci.virtualpetsamok;

public abstract class RoboticPet extends VirtualPet
{
	private final int OIL_LEVEL= 50;
	private int oilLevel = OIL_LEVEL;
	
	public int getOilLevel()
	{
		return oilLevel;
	}
	
	public RoboticPet(String name, String description, int health, int oilLevel)
	{
		super(name, description, health);
		this.oilLevel = oilLevel;
	}
	
	public RoboticPet(String name, String description, int health)
	{
		super(name, description, health);
	}

	public int oilPet()
	{
		return oilLevel += 50;
	}
	
	@Override
	public int hashCode()
	{
		return RoboticPet.class.hashCode();
	}
}
