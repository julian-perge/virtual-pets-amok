package wcci.virtualpetsamok;

public abstract class OrganicPet extends VirtualPet
{
	public int getHunger()
	{
		return hunger;
	}
	public int getWaste()
	{
		return waste;
	}
	public int getThirst()
	{
		return thirst;
	}
	public int getHappiness()
	{
		return happiness;
	}

	private int hunger = 50;
	private int waste = 50;
	private int thirst = 50;
	private int happiness = 50;
	
	public OrganicPet(String name, String description, int health, int hunger, int waste, int thirst, int happiness)
	{
		super(name, description, health);
		this.hunger = hunger;
		this.waste = waste;
		this.thirst = thirst;
		this.happiness = happiness;
	}
	public OrganicPet(String name, String description, int health)
	{
		super(name, description, health);
	}
	
	public int feed()
	{
		return hunger += 50;
	}
}
