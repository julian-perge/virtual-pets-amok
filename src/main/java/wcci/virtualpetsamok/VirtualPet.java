package wcci.virtualpetsamok;

public abstract class VirtualPet
{
	private String name = "";
	private String description = "";
	private int health = 100;
	
	public VirtualPet(String name, String description, int health)
	{
		super();
		this.name = name;
		this.description = description;
		this.health = health;
	}
	
	public String getName()
	{
		return name;
	}
	public String getDescription()
	{
		return description;
	}
	public int getHealth()
	{
		return health;
	}
	
	public void tick()
	{
		this.health -= 2;
	}
	
	@Override
	public String toString()
	{
		return name + "\t|" + health + "\t";
	}
}
