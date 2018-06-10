package wcci.virtualpetsamok;

public class OrganicDog extends OrganicPet implements Walkable
{	
	// percentage chance to soil
	private int chanceToSoilCage = 25;
	
	public OrganicDog(String name, String description, int health)
	{
		super(name, description, health);
	}
	
	public OrganicDog(String name, String description, int health, int hunger, int bladder, int fun, int energy, int happiness)
	{
		super(name, description, health, hunger, bladder, fun, energy, happiness);
	}
	
	@Override
	public int hashCode()
	{
		return OrganicDog.class.hashCode();
	}

	@Override
	public int walk() {
		chanceToSoilCage -= 10;
		return happiness += 25;
	}
}
