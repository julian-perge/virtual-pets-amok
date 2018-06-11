package wcci.virtualpetsamok;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

public class OrganicDogTest
{
	private VirtualPetShelter shelter = new VirtualPetShelter();
	private OrganicDog underTestDog = new OrganicDog("Coal", "Miniature dachshund with a bengal-tiger coat", 100);
	
	@Test
	public void walkingOrganicDogShouldIncreaseHappinessAndDecreaseChanceToSoilCage()
	{
		OrganicDog dogTest = new OrganicDog("Testy", "tasty", 100);
		int happinessBeforeWalk = dogTest.getHappiness();
		
		BigDecimal chanceToSoilBeforeWalk = dogTest.getChanceToSoilCage();
		shelter.walkOneDog(dogTest);
		BigDecimal chanceToSoilAfterWalk = dogTest.getChanceToSoilCage();
		
		int happinessAfterWalk = dogTest.getHappiness();
		
		assertThat(happinessAfterWalk, is(equalTo(happinessBeforeWalk + 25)));
		assertThat(chanceToSoilAfterWalk, lessThan(chanceToSoilBeforeWalk));
	}
	
	@Test
	public void chanceToSoilShouldIncreaseWhenTickIsCalled()
	{
		BigDecimal chanceToSoilBefore = underTestDog.getChanceToSoilCage();
		underTestDog.tick();
		BigDecimal chanceToSoilAfter = underTestDog.getChanceToSoilCage();
		assertThat(chanceToSoilAfter, greaterThan(chanceToSoilBefore));
	}
	
	@Test
	public void _01_shouldBeEqualToItself() {
		assertTrue(underTestDog.equals(underTestDog));
	}
	
	@Test
	public void _shouldNotBeEqualToNull() {
		assertFalse(underTestDog.equals(null));
	}
	
	@Test
	public void shouldReturnOrganicPetOfTypeDog()
	{
		OrganicPet equivalent = new OrganicDog("Rainbow", "Black and white dog", 100);
		
		assertEquals(equivalent.hashCode(), underTestDog.hashCode());
	}
	
	@Test
	public void shouldNotBeEqualToObjectOfDifferentType() {
		String other = "definitely not a city";
		
		assertFalse(underTestDog.equals(other));
	}
}
