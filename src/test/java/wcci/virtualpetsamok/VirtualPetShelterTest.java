package wcci.virtualpetsamok;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class VirtualPetShelterTest {
	VirtualPetShelter vps = new VirtualPetShelter();
	OrganicDog newOrganicDog = new OrganicDog("OrganicDoggo", "is organic dog", 100);
	OrganicDog newOrganicDog2 = new OrganicDog("OrganicDoggo2", "is organic dog2", 100);
	OrganicCat newOrganicCat = new OrganicCat("OrganicCat", "is organic cat", 100);
	RoboticDog newRobotDog = new RoboticDog("RoboticDog", "is robotic dog", 100);
	RoboticCat newRobotCat = new RoboticCat("RoboticCat", "is robotic cat", 100);

	@Before
	public void setUp() {
		vps.pets.put(newOrganicDog.getName(), newOrganicDog);
		vps.pets.put(newOrganicDog2.getName(), newOrganicDog2);
		vps.pets.put(newOrganicCat.getName(), newOrganicCat);
		vps.pets.put(newRobotDog.getName(), newRobotDog);
		vps.pets.put(newRobotCat.getName(), newRobotCat);
	}

	@Test
	public void shouldOilAllRoboticPets() {
		int totalOilLevelsBeforeOilingAllPets = newRobotCat.getOilLevel() + newRobotDog.getOilLevel();
		vps.oilAllRoboticPets();
		int totalOilLevelsAfterOilingPets = newRobotCat.getOilLevel() + newRobotDog.getOilLevel();
		assertThat(totalOilLevelsAfterOilingPets, is(equalTo(totalOilLevelsBeforeOilingAllPets + 100)));
	}

	@Test
	public void shouldReturnAllRoboticPetsInShelter() {
		int expectedAmountOfRoboticPets = 2;
		int numOfRoboticPets = vps.getRoboticPets().size();
		assertThat(numOfRoboticPets, is(equalTo(expectedAmountOfRoboticPets)));
	}

	@Test
	public void shouldReturnAllOrganicPetsInShelter() {
		int expectedAmountOfOrganicPets = 3;
		int numOfRoboticPets = vps.getOrganicPets().size();
		assertThat(numOfRoboticPets, is(equalTo(expectedAmountOfOrganicPets)));
	}
	
	@Test
	public void shouldBeAbleToAddAPet() {
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
	public void shouldReturnAllPetsInShelter() {
		vps.addPet(newOrganicCat);
		vps.addPet(newOrganicDog);
		assertTrue(vps.pets.containsValue(newOrganicDog));
		assertTrue(vps.pets.containsValue(newOrganicCat));
	}

	@Test
	public void shouldFeedAllOrganicPetsInShelter() {
		int expectedTotalHungerBeforeFeedingAll = newOrganicCat.getHunger() + newOrganicDog.getHunger();
		vps.feedAllOrganicPets();
		int totalHungerAfterFeeding = newOrganicCat.getHunger() + newOrganicDog.getHunger();
		assertThat(totalHungerAfterFeeding, is(equalTo(expectedTotalHungerBeforeFeedingAll + 100)));
	}

	@Test
	public void shouldIncreaseHappinessOfAllOrganicDogWhenWalked() {
		int happinessOfDogBeforeWalk = newOrganicDog.getHappiness() + newOrganicDog2.getHappiness();
		vps.walkAllOrganicDogs();
		int expectedHappinessOfDogAfterWalk = newOrganicDog.getHappiness() + newOrganicDog2.getHappiness();
		assertThat(expectedHappinessOfDogAfterWalk, is(equalTo(happinessOfDogBeforeWalk + 50)));
	}
	
	@Test
	public void shouldToggleLitterBoxNeedsCleanedBoolean()
	{
		boolean litterBoxIsDirty = vps.doesLitterBoxNeedCleaned();
		litterBoxIsDirty = vps.litterBoxIsDirtyToggle();
		assertTrue(litterBoxIsDirty);
	}
	
	@Test
	public void shouldCleanLitterBoxWhenLitterBoxCleanVlaueIsZero()
	{
		boolean litterBoxIsDirty = vps.doesLitterBoxNeedCleaned();
		if (litterBoxIsDirty)
		{
			vps.cleanLitterBox();
		}
		
		assertTrue(!litterBoxIsDirty);
	}
	
	@Test
	public void shouldCleanCageOfSpecificDog()
	{
		vps.addDogToNewCage(newOrganicDog);
		int cleanlinessOfCageBeforeCleaning = vps.cagesForDogs.get(newOrganicDog).getCleanlinessOfCage();  
		vps.cleanCage(newOrganicDog);
		int cleanlinessOfCageAfterCleaning = vps.cagesForDogs.get(newOrganicDog).getCleanlinessOfCage();
		
		assertThat(cleanlinessOfCageAfterCleaning, greaterThan(cleanlinessOfCageBeforeCleaning) );
	}
	
	@Test
	public void shouldCleanAllCagesOfOrganicDogs()
	{
		vps.addDogToNewCage(newOrganicDog);
		vps.addDogToNewCage(newOrganicDog2);
		int cleanlinessOfCagesBeforeCleaning = 10;
		vps.cleanAllDogCages();
		int cleanlinessOfCagesAfterCleaning = vps.cagesForDogs.get(newOrganicDog).getCleanlinessOfCage() + vps.cagesForDogs.get(newOrganicDog2).getCleanlinessOfCage();
		
		assertThat(cleanlinessOfCagesAfterCleaning, greaterThan(cleanlinessOfCagesBeforeCleaning) );
	}

}
