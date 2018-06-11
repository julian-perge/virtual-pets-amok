package wcci.virtualpetsamok;

public class CageForDog {
	private final int MAX_WASTE_LEVEL = 10;
	private int wasteLevel = 2; // 10 means a lot of shit and needs cleaned NOW
	private String ownerOfCage = "";

	public CageForDog(String nameOfDog) {
		super();
		this.ownerOfCage = nameOfDog;
	}

	public String getOwnerOfCage() {
		return this.ownerOfCage + "'s";
	}

	public int getWasteLevelOfCage() {
		return wasteLevel;
	}

	public int cleanCage() {
		return wasteLevel = 0;
	}
	
	public void tick()
	{
		this.wasteLevel += 1;
		if(this.wasteLevel >= MAX_WASTE_LEVEL)
		{
			wasteLevel = MAX_WASTE_LEVEL;
			System.out.println("The cage for " + getOwnerOfCage() + " needs cleaned!");
		}
	}

	@Override
	public String toString() {
		return "Owner: " + getOwnerOfCage() + "\t|Waste: " + getWasteLevelOfCage();
	}

}
