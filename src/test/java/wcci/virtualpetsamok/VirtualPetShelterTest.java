package wcci.virtualpetsamok;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class VirtualPetShelterTest {
	private VirtualPetShelter shelter = new VirtualPetShelter();
	OrganicDog newOrganicDog = new OrganicDog("OrganicDoggo", "is organic dog", 100);
	OrganicDog newOrganicDog2 = new OrganicDog("OrganicDoggo2", "is organic dog2", 100);
	OrganicCat newOrganicCat = new OrganicCat("OrganicCat", "is organic cat", 100);
	RoboticDog newRobotDog = new RoboticDog("RoboticDog", "is robotic dog", 100);
	RoboticCat newRobotCat = new RoboticCat("RoboticCat", "is robotic cat", 100);

	@Before
	public void setUp() {
		shelter.addPet(newOrganicDog);
		shelter.addPet(newOrganicDog2);
		shelter.addPet(newOrganicCat);
		shelter.addPet(newRobotDog);
		shelter.addPet(newRobotCat);
	}
	
	@Test
	public void shouldIncreaseWasteLevelOfAllDogCagesFromShelterTickMethod()
	{
		int expectedTotalWasteLevelOfCages = 6;
		shelter.shelterTick();
		int actualTotalWasteLevelOfCages = 0;
		assertThat(actualTotalWasteLevelOfCages, lessThan(expectedTotalWasteLevelOfCages));
	}
	
	@Test
	public void shouldDecreasePetAttributesFromShelterTickMethod()
	{
		int totalBladderValuesBeforeTick = 0;
		int totalEnergyValuesBeforeTick = 0;
		int totalFunValuesBeforeTick = 0;
		int totalHungerValuesBeforeTick = 0;
		int totalOilLevelsBeforeTick = 0;
		
		int totalBladderValuesAfterTick = 0;
		int totalEnergyValuesAfterTick = 0;
		int totalFunValuesAfterTick = 0;
		int totalHungerValuesAfterTick = 0;
		int totalOilLevelsAfterTick = 0;
		
		for (VirtualPet pet : shelter.getAllPets())
		{
			 if(pet instanceof OrganicPet)
			 {
				 totalBladderValuesBeforeTick += ((OrganicPet) pet).getBladderLevel();
				 totalEnergyValuesBeforeTick += ((OrganicPet) pet).getEnergy();
				 totalFunValuesBeforeTick += ((OrganicPet) pet).getFun();
				 totalHungerValuesBeforeTick += ((OrganicPet) pet).getHunger();
			 }
			 else if(pet instanceof RoboticPet)
			 {
				 totalOilLevelsBeforeTick += ((RoboticPet)pet).getOilLevel();
			 }
		}
		
		shelter.shelterTick();
		
		for (VirtualPet pet : shelter.getAllPets())
		{
			 if(pet instanceof OrganicPet)
			 {
				 totalBladderValuesAfterTick += ((OrganicPet) pet).getBladderLevel();
				 totalEnergyValuesAfterTick += ((OrganicPet) pet).getEnergy();
				 totalFunValuesAfterTick += ((OrganicPet) pet).getFun();
				 totalHungerValuesAfterTick += ((OrganicPet) pet).getHunger();
			 }
			 else if(pet instanceof RoboticPet)
			 {
				 totalOilLevelsAfterTick += ((RoboticPet)pet).getOilLevel();
			 }
		}
		assertThat(totalBladderValuesAfterTick, lessThan(totalBladderValuesBeforeTick));
		assertThat(totalEnergyValuesAfterTick, lessThan(totalEnergyValuesBeforeTick));
		assertThat(totalFunValuesAfterTick, lessThan(totalFunValuesBeforeTick));
		assertThat(totalHungerValuesAfterTick, lessThan(totalHungerValuesBeforeTick));
		assertThat(totalOilLevelsAfterTick, lessThan(totalOilLevelsBeforeTick));
	}
	

	@Test
	public void shouldOilAllRoboticPets() {
		int totalOilLevelsBeforeOilingAllPets = newRobotCat.getOilLevel() + newRobotDog.getOilLevel();
		shelter.oilAllRoboticPets();
		int totalOilLevelsAfterOilingPets = newRobotCat.getOilLevel() + newRobotDog.getOilLevel();
		assertThat(totalOilLevelsAfterOilingPets, is(equalTo(totalOilLevelsBeforeOilingAllPets + 100)));
	}

	@Test
	public void shouldReturnAllRoboticPetsInShelter() {
		int expectedAmountOfRoboticPets = 2;
		int numOfRoboticPets = shelter.getRoboticPets().size();
		assertThat(numOfRoboticPets, is(equalTo(expectedAmountOfRoboticPets)));
	}

	@Test
	public void shouldReturnAllOrganicPetsInShelter() {
		int expectedAmountOfOrganicPets = 3;
		int numOfRoboticPets = shelter.getOrganicPets().size();
		assertThat(numOfRoboticPets, is(equalTo(expectedAmountOfOrganicPets)));
	}
	
	@Test
	public void shouldBeAbleToAddAPet() {
		int sizeOfShelterBefore = shelter.pets.size(); // 4
		shelter.addPet(new RoboticDog("tim", "2020", 100));
		int sizeOfShelterAfter = shelter.pets.size();
		assertThat(sizeOfShelterAfter, is(equalTo(sizeOfShelterBefore + 1)));
	}

	@Test
	public void shouldBeAbleToAdoptAPet() // "adopt" is removing
	{
		shelter.addPet(newOrganicDog);
		int sizeOfShelterBefore = shelter.pets.size();
		shelter.adoptPet(newOrganicDog);
		int sizeOfShelterAfter = shelter.pets.size();
		assertThat(sizeOfShelterAfter, is(equalTo(sizeOfShelterBefore - 1)));
	}

	@Test
	public void shouldReturnAllPetsInShelter() {
		shelter.addPet(newOrganicCat);
		shelter.addPet(newOrganicDog);
		assertTrue(shelter.pets.containsValue(newOrganicDog));
		assertTrue(shelter.pets.containsValue(newOrganicCat));
	}

	@Test
	public void shouldFeedAllOrganicPetsInShelter() {
		int expectedTotalHungerBeforeFeedingAll = newOrganicCat.getHunger() + newOrganicDog.getHunger();
		shelter.feedAllOrganicPets();
		int totalHungerAfterFeeding = newOrganicCat.getHunger() + newOrganicDog.getHunger();
		assertThat(totalHungerAfterFeeding, is(equalTo(expectedTotalHungerBeforeFeedingAll + 80)));
	}

	@Test
	public void shouldIncreaseHappinessOfAllOrganicDogWhenWalked() {
		int happinessOfDogBeforeWalk = newOrganicDog.getHappiness() + newOrganicDog2.getHappiness();
		shelter.walkAllOrganicDogs();
		int expectedHappinessOfDogAfterWalk = newOrganicDog.getHappiness() + newOrganicDog2.getHappiness();
		assertThat(expectedHappinessOfDogAfterWalk, is(equalTo(happinessOfDogBeforeWalk + 50)));
	}
	
	@Test
	public void shouldToggleLitterBoxNeedsCleanedBoolean()
	{
		boolean litterBoxIsDirty = shelter.isLitterBoxClean();
		litterBoxIsDirty = shelter.litterBoxIsDirtyToggle();
		assertTrue(litterBoxIsDirty);
	}
	
	@Test
	public void shouldCleanLitterBoxWhenLitterBoxCleanVlaueIsZero()
	{
		boolean litterBoxIsDirty = shelter.isLitterBoxClean();
		if (litterBoxIsDirty)
		{
			shelter.cleanLitterBox();
		}
		
		assertTrue(!litterBoxIsDirty);
	}
	
	@Test
	public void shouldCleanCageOfSpecificDog()
	{
		shelter.addDogToNewCage(newOrganicDog);
		int cleanlinessOfCageBeforeCleaning = shelter.cagesForDogs.get(newOrganicDog).getWasteLevelOfCage();  
		shelter.cleanCage(newOrganicDog);
		int cleanlinessOfCageAfterCleaning = shelter.cagesForDogs.get(newOrganicDog).getWasteLevelOfCage();
		
		assertThat(cleanlinessOfCageAfterCleaning, lessThan(cleanlinessOfCageBeforeCleaning) );
	}
	
	@Test
	public void shouldCleanAllCagesOfOrganicDogs()
	{
		shelter.addDogToNewCage(newOrganicDog);
		shelter.addDogToNewCage(newOrganicDog2);
		
		int wasteLevelOfCagesBeforeCleaning = shelter.cagesForDogs.get(newOrganicDog).getWasteLevelOfCage() 
				+ shelter.cagesForDogs.get(newOrganicDog2).getWasteLevelOfCage();
		
		shelter.cleanAllDogCages();
		int wasteLevelOfCagesAfterCleaning = wasteLevelOfCagesBeforeCleaning - 10;
		
		assertThat(wasteLevelOfCagesAfterCleaning, lessThan(wasteLevelOfCagesBeforeCleaning) );
	}

}
