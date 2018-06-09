package wcci.virtualpetsamok;

public class RoboticDog extends RoboticPet
{	
	public RoboticDog(String name, String description, int health, int oilLevel)
	{
		super(name, description, health, oilLevel);
	}
	
	public RoboticDog(String name, String description, int health)
	{
		super(name, description, health);
	}
	
	@Override
	public int hashCode()
	{
		return RoboticDog.class.hashCode();
	}
}
