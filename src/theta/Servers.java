package theta;

public final class Servers {

	private static final GatewayProvider<Server> DEFAULT_PROVIDER = null;

	private static GatewayProvider<Server> provider;

	public static GatewayProvider<Server> configureProvider(
			GatewayProvider<Server> provider) {
		if (Servers.provider != null)
			throw new IllegalStateException(
					"A provider has already been configured!");
		return Servers.provider = provider;
	}

	public static Server create(Address address) {
		if (provider == null)
			provider = DEFAULT_PROVIDER;
		return provider.provide(address);
	}

	public static Server create(String host, int port) {
		return create(Address.create(host, port));
	}

	public static Server create(int port) {
		return create(Address.create(port));
	}

}