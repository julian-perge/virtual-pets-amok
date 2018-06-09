package wcci.virtualpetsamok;

public class OrganicDog extends OrganicPet implements Walkable
{	
	private int happiness = 100;
	// percentage chance to soil
	private int chanceToSoilCage = 25;
	
	public OrganicDog(String name, String description, int health)
	{
		super(name, description, health);
	}
	
	public OrganicDog(String name, String description, int health, int hunger, int waste, int thirst, int happiness)
	{
		super(name, description, health, hunger, waste, thirst, happiness);
	}
	
	public int getHappiness()
	{
		return happiness;
	}
	
	@Override
	public int hashCode()
	{
		return OrganicDog.class.hashCode();
	}

	@Override
	public int walk() {
		chanceToSoilCage -= 10;
		return this.happiness += 25;
	}
}
