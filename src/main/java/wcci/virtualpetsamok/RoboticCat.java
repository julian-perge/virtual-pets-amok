package wcci.virtualpetsamok;

public class RoboticCat extends RoboticPet
{
	public RoboticCat(String name, String description, int health, int oilLevel)
	{
		super(name, description, health, oilLevel);
	}

	public RoboticCat(String name, String description, int health)
	{
		super(name, description, health);
	}
	
	@Override
	public int hashCode()
	{
		return RoboticCat.class.hashCode();
	}

}
