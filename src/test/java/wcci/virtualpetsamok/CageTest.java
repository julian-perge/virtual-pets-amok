package wcci.virtualpetsamok;

import org.junit.Test;

public class CageTest
{
	@Test(expected = InstantiationException.class)
	public void shouldNotBeAbleToInstantiateARoboticVirtualPet() throws Exception
	{
		CageForDog.class.newInstance();
	}
}
