package wcci.virtualpetsamok;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class VirtualPetShelterTest
{
	VirtualPetShelter vps = new VirtualPetShelter();
	OrganicPet newOrganicDog = new OrganicDog("OrganicDoggo", "is organic dog", 100);
	OrganicPet newOrganicCat = new OrganicCat("OrganicCat", "is organic cat", 100);
	RoboticPet newRobotDog = new RoboticDog("RoboticDog", "is robotic dog", 100);
	RoboticPet newRobotCat = new RoboticCat("RoboticCat", "is robotic cat", 100);
	
	@Before
	public void setUp()
	{
		vps.pets.put(newOrganicDog.getName(), newOrganicDog);
		vps.pets.put(newOrganicCat.getName(), newOrganicCat);
		vps.pets.put(newRobotDog.getName(), newRobotDog);
		vps.pets.put(newRobotCat.getName(), newRobotCat);
	}
	
	@Test 
	public void shouldOilAllRoboticPets()
	{
		int totalOilLevelsBeforeOilingAllPets = newRobotCat.getOilLevel() + newRobotDog.getOilLevel();
		vps.oilAllRoboticPets();
		int totalOilLevelsAfterOilingPets = newRobotCat.getOilLevel() + newRobotDog.getOilLevel();
		assertThat(totalOilLevelsAfterOilingPets, is(equalTo(totalOilLevelsBeforeOilingAllPets + 100)));
	}
	
	@Test
	public void shouldReturnAllRoboticPetsInShelter()
	{
		int expectedAmountOfRoboticPets = 2;
		int numOfRoboticPets = vps.getRoboticPets().size();
		assertThat(numOfRoboticPets, is(equalTo(expectedAmountOfRoboticPets)));
	}
	
	@Test
	public void shouldReturnAllOrganicPetsInShelter()
	{
		int expectedAmountOfOrganicPets = 2;
		int numOfRoboticPets = vps.getOrganicPets().size();
		assertThat(numOfRoboticPets, is(equalTo(expectedAmountOfOrganicPets)));
	}

	@Test
	public void shouldBeAbleToAddAPet()
	{
		int sizeOfShelterBefore = vps.pets.size(); // 4
		vps.addPet(new RoboticDog("tim", "2020", 100));
		int sizeOfShelterAfter = vps.pets.size();
		assertThat(sizeOfShelterAfter, is(equalTo(sizeOfShelterBefore + 1)));
	}

	@Test
	public void shouldBeAbleToAdoptAPet() // "adopt" is removing
	{
		vps.addPet(newOrganicDog);
		int sizeOfShelterBefore = vps.pets.size();
		vps.adoptPet(newOrganicDog);
		int sizeOfShelterAfter = vps.pets.size();
		assertThat(sizeOfShelterAfter, is(equalTo(sizeOfShelterBefore - 1)));
	}

	@Test
	public void shouldReturnAllPetsInShelter()
	{
		vps.addPet(newOrganicCat);
		vps.addPet(newOrganicDog);

		assertTrue(vps.pets.containsValue(newOrganicDog));
		assertTrue(vps.pets.containsValue(newOrganicCat));
	}
	
	@Test
	public void shouldFeedAllOrganicPetsInShelter()
	{
		int expectedTotalHungerBeforeFeedingAll = newOrganicCat.getHunger() + newOrganicDog.getHunger();
		vps.feedAllOrganicPets();
		int totalHungerAfterFeeding = newOrganicCat.getHunger() + newOrganicDog.getHunger();;
		
		assertThat(totalHungerAfterFeeding, is(equalTo(expectedTotalHungerBeforeFeedingAll + 100)));
	}
	
	@Test
	public void shouldIncreaseHappinessOfAllOrganicDogWhenWalked()
	{
		int happinessOfDogBeforeWalk = newOrganicDog.getHappiness();
		
		int expectedHappinessOfDogAfterWalk = newOrganicDog.getHappiness();
		
		assertThat(expectedHappinessOfDogAfterWalk, is(equalTo(happinessOfDogBeforeWalk + 1)));
	}
	
	

}
