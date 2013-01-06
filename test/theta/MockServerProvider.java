package theta;

class MockServerProvider implements GatewayProvider<Server> {

	@Override
	public Server provide(Address address) {
		return new MockServer(address);
	}

}