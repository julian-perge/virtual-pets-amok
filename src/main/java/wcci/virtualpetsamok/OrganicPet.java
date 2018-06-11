package wcci.virtualpetsamok;

import java.math.BigDecimal;

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

	private int energy = MAX_ATTRIBUTE_VALUE;
	private int hunger = MAX_ATTRIBUTE_VALUE;
	private int fun = MAX_ATTRIBUTE_VALUE;
	private int bladder = 0;
	private int happiness = 75;
	
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
	
	public void feed()
	{
		this.bladder += 30;
		this.hunger += 40;
	}
	
	public void useBathroom()
	{
		this.bladder = 0;
	}
	
	public void play()
	{
		this.hunger -= 20;
		this.fun += 20;
		this.energy -= 20;
	}
	
	private int calculateHappiness(int bladder, int energy, int hunger)
	{
		 BigDecimal bladderWeight = new BigDecimal(2 * Math.pow(Math.pow( (40/2), (-1/100) ), bladder) + 1);
		 bladderWeight = bladderWeight.multiply(new BigDecimal(0.20));
		 BigDecimal funWeight = new BigDecimal( (1/500) * Math.pow(this.fun, 2) + 5);
		 funWeight = funWeight.multiply(new BigDecimal(0.20));
		 BigDecimal energyWeight = new BigDecimal(5 * Math.pow(Math.pow( (40/5), (-1/100) ), energy));
		 energyWeight = energyWeight.multiply(new BigDecimal(0.20));
		 BigDecimal hungerWeight = new BigDecimal(5 * Math.pow(Math.pow( (90/2), (-1/100) ), hunger) + 10);
		 hungerWeight = hungerWeight.multiply(new BigDecimal(0.40));
		 BigDecimal happinessWeight = bladderWeight;
		 happinessWeight = happinessWeight.add(bladderWeight).add(funWeight).add(energyWeight).add(hungerWeight);
		 BigDecimal calculation = new BigDecimal(4 * Math.pow(0.99676251868, happinessWeight.doubleValue()) + 8 * (10.00));
		 int calculatedHappiness = calculation.intValue();
		 return this.happiness = calculatedHappiness;
	}
	
	@Override
	public void tick()
	{
		super.tick();
		this.bladder -= 10;
		this.energy -= 10;
		this.fun -= 10;
		this.hunger -= 10;
		this.happiness = calculateHappiness(this.bladder, this.energy, this.hunger);
	}
	
	@Override
	public String toString() {
		return super.toString() + "|" + getHunger() + "\t|" + getFun() + "\t|" + getBladderLevel() + "\t|" + getHappiness();
	}
	
	public int walk()
	{
		return this.happiness += 25;
	}
}
