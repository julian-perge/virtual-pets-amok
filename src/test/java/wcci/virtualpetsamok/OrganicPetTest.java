package wcci.virtualpetsamok;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class OrganicPetTest
{
	@Test(expected = InstantiationException.class)
	public void shouldNotBeAbleToInstantiateAnOrganicVirtualPet() throws Exception
	{
		VirtualPet.class.newInstance();
	}
	
	@Test
	public void NegativeBladderLevelInOrganicPetShouldLowerHappinessLevel()
	{
		OrganicDog dogTest = new OrganicDog("tim", "hambone", 100);
		int happinessBeforeBladderSet = dogTest.getBladderLevel();
//		dogTest.tick();
		int expectedDogHappy = dogTest.getBladderLevel();
		assertThat(expectedDogHappy, lessThan(happinessBeforeBladderSet));
		
	}
	
	@Test
	public void shouldReturnModifiedHappinessValueOfDog()
	{
		OrganicPet dogTest = new OrganicDog("Test", "Testing", 100, 100, -100, 100, 100, 100);
		int happyBefore = dogTest.getHappiness();
		dogTest.calculateHappiness(dogTest.getHunger(), dogTest.getBladderLevel(), dogTest.getFun(), dogTest.getEnergy());
		
		assertThat(50, is(equalTo(happyBefore)));
	}
}
