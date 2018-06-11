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
	protected int bladder = 0;
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
	
	public int calculateHappiness()
	{
		 BigDecimal bladderWeight = new BigDecimal(2 * Math.pow(Math.pow( (40/2), (-1/100) ), getBladderLevel()) + 1);
		 bladderWeight = bladderWeight.multiply(new BigDecimal(0.25));
		 System.out.println("Bladder: " + bladderWeight);
		 
		 BigDecimal funWeight = new BigDecimal( (1/500) * Math.pow(this.fun, 2) + 5);
		 funWeight = funWeight.multiply(new BigDecimal(0.25));
		 System.out.println("Fun: " + funWeight);
		 
		 BigDecimal energyWeight = new BigDecimal(5 * Math.pow(Math.pow( (40/5), (-1/100) ), this.energy));
		 energyWeight = energyWeight.multiply(new BigDecimal(0.25));
		 System.out.println("Energy: " + energyWeight);
		 
		 BigDecimal hungerWeight = new BigDecimal(5 * Math.pow(Math.pow( (90/2), (-1/100) ), this.hunger) + 10);
		 hungerWeight = hungerWeight.multiply(new BigDecimal(0.25));
		 System.out.println("Hunger: " + hungerWeight);
		 
		 BigDecimal happinessWeight = bladderWeight;
		 happinessWeight = happinessWeight.add(bladderWeight).add(funWeight).add(energyWeight).add(hungerWeight);
		 System.out.println("Happy weight: " + happinessWeight);
		 
		 BigDecimal calculation = new BigDecimal(4 * Math.pow(0.97566251868, 100) + 8);
		 System.out.println("Calc: " + calculation);
//		 int calculatedHappiness = (int) Math.round(calculation);
		 
		 return 0;
	}
	
	public void setBladder(int bladder) {
		this.bladder = bladder;
	}
	@Override
	public void tick()
	{
//		this.bladder -= 10;
//		this.energy -= 10;
//		this.fun -= 10;
//		this.hunger -= 10;
		this.happiness = calculateHappiness();
	}
	
	@Override
	public String toString() {
		return super.toString() + "|" + getHunger() + "\t|" + getFun() + "\t|" + getBladderLevel() + "\t|" + getHappiness();
	}
}
