package wcci.virtualpetsamok;

public class OrganicCat extends OrganicPet
{
	public OrganicCat(String name, String description, int health, int hunger, int bladder, int fun, int energy, int happiness)
	{
		super(name, description, health, hunger, bladder, fun, energy, happiness);
	}

	public OrganicCat(String name, String description, int health)
	{
		super(name, description, health);
	}
	
	@Override
	public int hashCode()
	{
		return OrganicDog.class.hashCode();
	}
}