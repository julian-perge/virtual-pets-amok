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

	public OrganicPet(String name, String description, int health, int hunger, int bladder, int fun, int energy,
			int happiness)
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

	public int calculateHappiness(int hunger, int bladder, int fun, int energy)
	{
		System.out.println(hunger + "|" + bladder + "|" + fun + "|" + energy);
		BigDecimal bladderWeight = new BigDecimal(2 * Math.pow(0.97048695, bladder) + 1);
		BigDecimal funWeight 	= new BigDecimal((1 / 500) * Math.pow(fun, 2) + 5);
		BigDecimal energyWeight = new BigDecimal(5 * Math.pow(0.97942029, energy));
		BigDecimal hungerWeight = new BigDecimal(5 * Math.pow(0.96507112, hunger) + 10);
		System.out.println("Bladder: " + bladderWeight + "\nFun" + funWeight + "\nEnergy" + energyWeight + "\nHunger" + hungerWeight);

		BigDecimal happinessWeight = new BigDecimal(0.00).add(bladderWeight).add(funWeight).add(energyWeight)
				.add(hungerWeight);

		BigDecimal calculation = new BigDecimal(
				4 * Math.pow(0.99676251868, happinessWeight.doubleValue()) + 8 * (10.00));
		System.out.println(calculation);
		return happiness = calculation.intValue();
	}

	@Override
	public void tick()
	{
		super.tick();
		this.bladder -= 5;
		this.energy -= 5;
		this.fun -= 5;
		this.hunger -= 5;
		calculateHappiness(this.hunger, this.bladder, this.fun, this.energy);
	}

	@Override
	public String toString()
	{
		return super.toString() + "|" + getHunger() + "\t|" + getFun() + "\t|" + getBladderLevel() + "\t|"
				+ getHappiness();
	}

	public int walk()
	{
		return this.happiness += 25;
	}
}
