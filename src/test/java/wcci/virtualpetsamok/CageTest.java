package wcci.virtualpetsamok;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class CageTest
{
	private VirtualPetShelter shelter = new VirtualPetShelter();
	private OrganicDog dogTest = new OrganicDog("Test", "likes testing", 100);
	private OrganicDog dogTestTwo = new OrganicDog("Test", "likes testing", 100);
	
	@Test
	public void shouldBeAbleToAssignNewCageToShelterDog()
	{
		int numberOfCagesBeforeAssigningDogToNewCage = shelter.cagesForDogs.size();
		shelter.addDogToNewCage(dogTest);
		int numberOfCagesAfterAssigningDogToNewCage = shelter.cagesForDogs.size();
		assertThat(numberOfCagesAfterAssigningDogToNewCage, is(equalTo(numberOfCagesBeforeAssigningDogToNewCage + 1)));
	}
	
	@Test
	public void shouldBeAbleToGetSpecificCleanlinessOfDogCage()
	{
		shelter.addDogToNewCage(dogTest);
		shelter.addDogToNewCage(dogTestTwo);
		int expectedTotalCleanlinessOfCages = 0;
		int actualTotalCleanlinessOfCages = 0;
		for (CageForDog cage : shelter.cagesForDogs.values()) {
			expectedTotalCleanlinessOfCages += cage.getCleanlinessOfCage();
		}
		assertThat(expectedTotalCleanlinessOfCages, is(equalTo(actualTotalCleanlinessOfCages)));
	}
}
