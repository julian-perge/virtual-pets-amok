package wcci.virtualpetsamok;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class OrganicDogTest
{
	private VirtualPet underTestDog = new OrganicDog("Coal", "Miniature dachshund with a bengal-tiger coat", 100);
	
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
