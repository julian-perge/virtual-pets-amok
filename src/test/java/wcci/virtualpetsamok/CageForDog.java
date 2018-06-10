package wcci.virtualpetsamok;


public class CageForDog
{
	private final int MAX_CLEANLINESS_LEVEL = 10;
	private int cleanliness = 5; //10 means super clean

	public int getCleanlinessOfCage() {
		return cleanliness;
	}
	
	public int cleanCage()
	{
		return cleanliness = MAX_CLEANLINESS_LEVEL;
	}

	public CageForDog() {
		super();
	}

}
