package wcci.virtualpetsamok;

import java.math.BigDecimal;

public class OrganicDog extends OrganicPet implements Walkable
{	
	// percentage chance to soil
	private BigDecimal chanceToSoilCage = calculateChanceToSoilCage();
	
	public BigDecimal calculateChanceToSoilCage()
	{
		// -100 + 0 = -100, |-100| + 0 = 100
		chanceToSoilCage = new BigDecimal((1/500) * Math.pow(bladder, 2.00) + 5);
		return chanceToSoilCage;
	}
	
	public OrganicDog(String name, String description, int health)
	{
		super(name, description, health);
	}
	
	public OrganicDog(String name, String description, int health, int hunger, int bladder, int fun, int energy, int happiness)
	{
		super(name, description, health, hunger, bladder, fun, energy, happiness);
	}
	
	public BigDecimal getChanceToSoilCage() {
		return this.chanceToSoilCage;
	}
	
	@Override
	public void tick()
	{
		super.tick();
		this.chanceToSoilCage = chanceToSoilCage.add(new BigDecimal(10));
	}

	@Override
	public int hashCode()
	{
		return OrganicDog.class.hashCode();
	}

	@Override
	public int walk() {
		this.chanceToSoilCage = chanceToSoilCage.subtract(new BigDecimal(10));
		return this.happiness += 25;
	}
}
