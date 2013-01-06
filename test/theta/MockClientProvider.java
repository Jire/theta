package theta;

class MockClientProvider implements GatewayProvider<Client> {

	@Override
	public Client provide(Address address) {
		return new MockClient(address);
	}

}