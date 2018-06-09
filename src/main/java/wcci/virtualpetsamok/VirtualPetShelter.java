package wcci.virtualpetsamok;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class VirtualPetShelter
{
	Map<String, VirtualPet> pets = new HashMap<>();
	
	private int shelterLitterBoxWaste = 0; // 5
	private boolean litterBoxNeedsCleaned = false;
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	public void addPet(VirtualPet pet)
	{
		pets.put(pet.getName(), pet);
	}

	public void adoptPet(VirtualPet pet)
	{
		pets.remove(pet.getName(), pet);
	}
	
	public VirtualPet returnSpecificPet(String petToGet)
	{
		return pets.get(petToGet);
	}
	
	public int getLitterBoxCleanValue() {
		return shelterLitterBoxWaste;
	}

	public boolean doesLitterBoxNeedCleaned() {
		return litterBoxNeedsCleaned;
	}
	
	public boolean litterBoxIsDirtyToggle()
	{
		return litterBoxNeedsCleaned = !litterBoxNeedsCleaned;
	}
	
	public void cleanLitterBox()
	{
		shelterLitterBoxWaste = 0;
		litterBoxNeedsCleaned = litterBoxIsDirtyToggle();
	}
	
	public void useLitterBox()
	{
		shelterLitterBoxWaste += 1;
		if(shelterLitterBoxWaste >= 10 && !litterBoxNeedsCleaned)
		{
			litterBoxNeedsCleaned = litterBoxIsDirtyToggle();
		}
	}
	
	public void walkOneDog(OrganicDog dog)
	{
		dog.walk();
	}
	
	public void walkAllOrganicDogs()
	{
		for (OrganicPet dog : getOrganicPets())
		{
			if(dog instanceof OrganicDog)
			{
				((OrganicDog) dog).walk();
			}
		}
	}

	public void feedAllOrganicPets()
	{
		for (OrganicPet pet : getOrganicPets())
		{
			pet.feed();
		}
	}
	
	public void waterAllOrganicPets()
	{
		for (OrganicPet pet : getOrganicPets())
		{
			pet.water();
		}
	}
	
	public void oilAllRoboticPets()
	{
		for (RoboticPet robotPet : getRoboticPets())
		{
			robotPet.oilPet();
		}
	}
	
	/*
	 * 
	 * COLLECTIONS
	 * 
	 */
	
	public Collection<VirtualPet> getPets()
	{
		return pets.values();
	}
	
	public Collection<OrganicPet> getOrganicPets()
	{
		Set<OrganicPet> organicPets = new HashSet<>();
		
		for (VirtualPet pet : pets.values())
		{
			if (pet instanceof OrganicPet)
			{
				organicPets.add((OrganicPet)pet);
			}
		}
		return organicPets;
	}

	public Collection<RoboticPet> getRoboticPets()
	{
		Set<RoboticPet> roboPets = new HashSet<>();
		for (VirtualPet pet : pets.values())
		{
			if (pet instanceof RoboticPet)
			{
				roboPets.add((RoboticPet)pet);
			}
		}
		return roboPets;
	}
}
