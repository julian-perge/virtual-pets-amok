package wcci.virtualpetsamok;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class OrganicPetTest
{
	@Test(expected = InstantiationException.class)
	public void shouldNotBeAbleToInstantiateAnOrganicVirtualPet() throws Exception
	{
		VirtualPet.class.newInstance();
	}
	
	@Test
	public void Negative100BladderInOrganicPetShouldLowerHappinessLevel()
	{
		OrganicPet dogTest = new OrganicDog("tim", "hambone", 100);
		int happinessBeforeBladderSet = dogTest.getHappiness();
//		dogTest.setBladder(-100);
		dogTest.tick();
		int expectedDogHappy = dogTest.getHappiness();
		
		assertThat(happinessBeforeBladderSet, lessThan(expectedDogHappy));
		
	}
}
