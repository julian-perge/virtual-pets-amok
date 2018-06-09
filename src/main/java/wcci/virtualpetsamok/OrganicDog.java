package wcci.virtualpetsamok;

public class OrganicDog extends OrganicPet implements Walkable
{	
	public OrganicDog(String name, String description, int health)
	{
		super(name, description, health);
	}
	
	public OrganicDog(String name, String description, int health, int hunger, int waste, int thirst, int happiness)
	{
		super(name, description, health, hunger, waste, thirst, happiness);
	}
	
	@Override
	public int hashCode()
	{
		return OrganicDog.class.hashCode();
	}

	@Override
	public void walk()
	{
		int happy = this.getHappiness();
		happy += 50;
	}
}
