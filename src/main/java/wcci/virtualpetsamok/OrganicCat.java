package wcci.virtualpetsamok;

public class OrganicCat extends OrganicPet implements UseLitterBox
{
	public OrganicCat(String name, String description, int health, int hunger, int waste, int thirst, int happiness)
	{
		super(name, description, health, hunger, waste, thirst, happiness);
	}

	public OrganicCat(String name, String description, int health)
	{
		super(name, description, health);
	}
	
	@Override
	public int hashCode()
	{
		return OrganicDog.class.hashCode();
	}

	@Override
	public void useLitterBox(VirtualPetShelter shelter) {
		shelter.useLitterBox();
	}

}