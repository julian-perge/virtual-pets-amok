package wcci.virtualpetsamok;

import org.junit.Test;

public class OrganicPetTest
{
	@Test(expected = InstantiationException.class)
	public void shouldNotBeAbleToInstantiateAnOrganicVirtualPet() throws Exception
	{
		VirtualPet.class.newInstance();
	}
}
