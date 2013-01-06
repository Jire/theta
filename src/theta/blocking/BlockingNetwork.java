package theta.blocking;

import theta.Client;
import theta.Clients;
import theta.GatewayProvider;
import theta.Server;
import theta.Servers;

public final class BlockingNetwork {

	private static final GatewayProvider<Client> CLIENT_PROVIDER = new BlockingClientProvider();
	private static final GatewayProvider<Server> SERVER_PROVIDER = new BlockingServerProvider();

	public static void configureClient() {
		Clients.configureProvider(CLIENT_PROVIDER);
	}

	public static void configureServer() {
		Servers.configureProvider(SERVER_PROVIDER);
	}

	public static void configure() {
		configureClient();
		configureServer();
	}

}