package wcci.virtualpetsamok;

import org.junit.Test;

public class VirtualPetTest
{
	@Test(expected = InstantiationException.class)
	public void shouldNotBeAbleToInstantiateAVirtualPet() throws Exception
	{
		VirtualPet.class.newInstance();
	}
}
