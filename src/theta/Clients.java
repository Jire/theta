package theta;

public final class Clients {

	private static final GatewayProvider<Client> DEFAULT_PROVIDER = null;

	private static GatewayProvider<Client> provider;

	public static GatewayProvider<Client> configureProvider(
			GatewayProvider<Client> provider) {
		if (Clients.provider != null)
			throw new IllegalStateException(
					"A provider has already been configured!");
		return Clients.provider = provider;
	}

	public static Client create(Address address) {
		if (provider == null)
			provider = DEFAULT_PROVIDER;
		return provider.provide(address);
	}

	public static Client create(String host, int port) {
		return create(Address.create(host, port));
	}

	public static Client create(int port) {
		return create(Address.create(port));
	}

}