package wcci.virtualpetsamok;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class VirtualPetShelter
{
	Map<String, VirtualPet> pets = new HashMap<>();

	public Collection<VirtualPet> getPets()
	{
		return pets.values();
	}

	public void addPet(VirtualPet pet)
	{
		pets.put(pet.getName(), pet);
	}

	public void adoptPet(VirtualPet pet)
	{
		pets.remove(pet.getName(), pet);
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

	public VirtualPet returnSpecificPet(String petToGet)
	{
		return pets.get(petToGet);
	}

	public void feedAllOrganicPets()
	{
		for (OrganicPet pet : getOrganicPets())
		{
			pet.feed();
		}
	}
	
	public void walkAllOrganicDogs()
	{
		for (OrganicPet pet : getOrganicPets())
		{
			if(pet instanceof OrganicDog)
			{
				((OrganicDog) pet).walk();
			}
		}
	}

	public void oilAllRoboticPets()
	{
		for (RoboticPet robotPet : getRoboticPets())
		{
			robotPet.oilPet();
		}
	}

}
