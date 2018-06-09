package wcci.virtualpetsamok;

public abstract class OrganicPet extends VirtualPet
{
	public int getHunger()
	{
		return hunger;
	}
	public int getBladderLevel()
	{
		return bladder;
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
	private int thirst = 50;
	private int bladder = 0;
	private int happiness = 100;
	
	public OrganicPet(String name, String description, int health, int hunger, int bladder, int thirst, int happiness)
	{
		super(name, description, health);
		this.hunger = hunger;
		this.bladder = bladder;
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
	public int water() {
		return thirst += 50;
	}
	
	@Override
	public String toString() {
		return ""+getHunger() + "\t" + getThirst() + "\t" + getBladderLevel() + "\t" + getHappiness();
	}
}
