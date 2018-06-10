package wcci.virtualpetsamok;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class VirtualPetShelter
{
	public Map<String, VirtualPet> pets = new HashMap<>();
	public Map<VirtualPet, CageForDog> cagesForDogs = new HashMap<>();
	
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
	
	// ROBOTIC PET SPECIFIC
	
	public void oilAllRoboticPets()
	{
		for (RoboticPet robotPet : getRoboticPets())
		{
			robotPet.oilPet();
		}
	}
	
	// ORGANIC PET SPECIFIC
	
	public void playWithAll()
	{
		for (VirtualPet pet : getAllPets())
		{
			if (pet instanceof OrganicPet)
			{
				((OrganicPet)pet).play();	
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
	
	// CAT SPECIFIC
	
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
	
	public void catUsesLitterBox()
	{
		shelterLitterBoxWaste += 1;
		if(shelterLitterBoxWaste >= 10 && !litterBoxNeedsCleaned)
		{
			litterBoxNeedsCleaned = litterBoxIsDirtyToggle();
		}
	}
	
	// DOG SPECIFIC
	
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
	
	public void addDogToNewCage(OrganicDog dog)
	{
		cagesForDogs.put(dog, new CageForDog());
	}
	
	public int getNumberOfCages()
	{
		return cagesForDogs.size();
	} 
	
	public void cleanCage(OrganicDog dog)
	{
		cagesForDogs.get(dog).cleanCage();
	}
	
	public void cleanAllDogCages()
	{
		for (CageForDog cage : cagesForDogs.values()) {
			cage.cleanCage();
		}
	}
	
	/*
	 * 
	 * COLLECTIONS
	 * 
	 */
	
	public Collection<VirtualPet> getAllPets()
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
	
	// TICK
	
//	public void tick(int test)
//	{
//		for (VirtualPet pet : getAllPets())
//		{
//			int petFun = pet.getFun();
//			int petEnergy = pet.getEnergy();
//			int petHunger = pet.getHunger();
//			
//			switch (test)
//			{
//				// is fed
//				case 1:
//					petHunger -= 5;
//					petEnergy += 5;
//					break;
//				// is played with
//				case 2:
//					petHunger -= 5;
//					petEnergy -= 5;
//					break;
//				// has slept
//				case 3:
//					petHunger -= 5;
//					petFun-= 5;
//					break;
//				// does nothing
//				case 0:
//				case 4:
//				case 5:
//					petHunger -= 5;
//					petFun -= 5;
//					petEnergy -= 5;
//					break;
//				default:
//					break;
//			}
//		}
//	}
}
