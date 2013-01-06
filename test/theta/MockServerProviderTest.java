package theta;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public final class MockServerProviderTest {

	private final GatewayProvider<Server> provider = new MockServerProvider();
	private final Address address = Address.create(80);

	private Server provided;

	@Test
	@Before
	public void ensureProviderReturns() {
		Assert.assertNotNull(provided = provider.provide(address));
	}

	@Test
	public void ensureProvidedCanBind() {
		Assert.assertTrue(provided.bind());
	}

	@Test
	public void ensureProvidedCanClose() {
		Assert.assertTrue(provided.close());
	}

	@Test
	public void ensureProvidedAddressIsSynchronized() {
		Assert.assertEquals(Address.create("localhost", 80),
				provided.getAddress());
	}

}