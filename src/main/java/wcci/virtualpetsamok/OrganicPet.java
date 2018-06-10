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
	public int getFun()
	{
		return fun;
	}
	public int getHappiness()
	{
		return happiness;
	}
	
	public int getEnergy()
	{
		return energy;
	}

	private int MAX_ATTRIBUTE_VALUE = 100;

	private int energy = 50;
	private int hunger = MAX_ATTRIBUTE_VALUE;
	private int fun = MAX_ATTRIBUTE_VALUE;
	private int bladder = 0;
	protected int happiness = MAX_ATTRIBUTE_VALUE;
	
	public OrganicPet(String name, String description, int health, int hunger, int bladder, int fun, int energy, int happiness)
	{
		super(name, description, health);
		this.bladder = bladder;
		this.energy = energy;
		this.fun = fun;
		this.happiness = happiness;
		this.hunger = hunger;
	}
	public OrganicPet(String name, String description, int health)
	{
		super(name, description, health);
	}
	
	public int feed()
	{
		return hunger += 50;
	}
	
	public void play()
	{
		this.fun += 20;
		this.energy -= 20;
	}
	
	@Override
	public String toString() {
		return super.toString() + "|" + getHunger() + "\t|" + getFun() + "\t|" + getBladderLevel() + "\t|" + getHappiness();
	}
}
