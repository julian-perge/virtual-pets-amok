package wcci.virtualpetsamok;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class OrganicCatTest
{
	private OrganicPet underTestCat = new OrganicCat("Rainbow", "Black & white cat", 100);
	
	@Test
	public void _01_shouldBeEqualToItself() {
		assertTrue(underTestCat.equals(underTestCat));
	}
	
	@Test
	public void _shouldNotBeEqualToNull() {
		assertFalse(underTestCat.equals(null));
	}
	
	@Test
	public void shouldReturnOrganicPetOfTypeDog()
	{
		OrganicPet equivalent = new OrganicCat("Rainbow", "Black and white dog", 100);
		
		assertEquals(equivalent.hashCode(), underTestCat.hashCode());
	}
	
	@Test
	public void shouldNotBeEqualToObjectOfDifferentType() {
		String other = "definitely not a city";
		
		assertFalse(underTestCat.equals(other));
	}
}
