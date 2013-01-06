package theta;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public final class MockServerProviderTest {

	private GatewayProvider<Server> provider;
	private Address address;

	@Before
	public void setup() {
		provider = new MockServerProvider();
		address = Address.create(80);
	}

	@Test
	public void ensureProviderReturns() {
		Assert.assertNotNull(provider.provide(address));
	}

}