package wcci.virtualpetsamok;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class CageTest
{
	private VirtualPetShelter shelter = new VirtualPetShelter();
	private OrganicDog dogTest = new OrganicDog("Test", "likes testing", 100);
	
	@Test
	public void shouldBeAbleToAssignNewCageToShelterDog()
	{
		int numberOfCagesBeforeAssigningDogToNewCage = shelter.cagesForDogs.size();
		shelter.addDogToNewCage(dogTest);
		int numberOfCagesAfterAssigningDogToNewCage = shelter.cagesForDogs.size();
		assertThat(numberOfCagesAfterAssigningDogToNewCage, is(equalTo(numberOfCagesBeforeAssigningDogToNewCage + 1)));
	}

	@Test
	public void shouldBeAbleToGetSpecificWasteLevelOfDogCage()
	{
		shelter.addDogToNewCage(dogTest);
		int expectedTotalCleanlinessOfCages = 2;
		int actualTotalCleanlinessOfCages = shelter.cagesForDogs.get(dogTest).getWasteLevelOfCage();;
		
		assertThat(actualTotalCleanlinessOfCages, is(equalTo(expectedTotalCleanlinessOfCages)));
	}
	
	@Test
	public void tickMethodForCageShouldStopAddingWasteWhenWasteIsMaxLevel()
	{
		shelter.addDogToNewCage(dogTest);
		CageForDog cage =  shelter.cagesForDogs.get(dogTest);
		for (int i = 0; i < 8; i++) {
			cage.tick();
		}
		int wasteLevelOfCage = cage.getWasteLevelOfCage();
		int expectedWasteLevel = 10;
		assertThat(wasteLevelOfCage, is(equalTo(expectedWasteLevel)));
	}
}
