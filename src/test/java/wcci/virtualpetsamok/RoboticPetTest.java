package wcci.virtualpetsamok;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RoboticPetTest
{
	public RoboticPet underTestDog = new RoboticDog("Coal", "mini-dachshund", 100);
	public RoboticPet underTestCat = new RoboticCat("Coal", "mini-dachshund", 100);
	
	@Test(expected = InstantiationException.class)
	public void shouldNotBeAbleToInstantiateARoboticVirtualPet() throws Exception
	{
		RoboticPet.class.newInstance();
	}
	
	@Test
	public void _01_roboticDogShouldBeEqualToItself()
	{
		assertTrue(underTestDog.equals(underTestDog));
	}
	
	@Test
	public void _02_roboticCatShouldBeEqualToItself()
	{
		assertTrue(underTestCat.equals(underTestCat));
	}

	@Test
	public void _eitherRoboticPetSubclassesShouldNotBeEqualToNull()
	{
		assertFalse(underTestDog.equals(null));
		assertFalse(underTestCat.equals(null));
	}

	@Test
	public void shouldReturnRoboticPetOfTypeDog()
	{
		VirtualPet equivalent = new RoboticDog("Rainbow", "Black and white dog", 100);

		assertEquals(equivalent.hashCode(), underTestDog.hashCode());
	}
	@Test
	public void shouldReturnRoboticPetOfTypeCat()
	{
		VirtualPet equivalent = new RoboticCat("Rainbow", "Black and white dog", 100);
		
		assertEquals(equivalent.hashCode(), underTestCat.hashCode());
	}
	
	@Test
	public void shouldReturnRoboticPetOilLevel()
	{
		int actualOilLevel = underTestCat.getOilLevel();
		int expectedOilLevel = 50; 
		
		assertEquals(actualOilLevel, expectedOilLevel);
	}

	@Test
	public void shouldNotBeEqualToObjectOfDifferentType()
	{
		String other = "definitely not a city";

		assertFalse(underTestDog.equals(other));
	}
}
