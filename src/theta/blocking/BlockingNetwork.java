package theta.blocking;

import theta.Clients;
import theta.Servers;

public final class BlockingNetwork {

	public static void configureClient() {
		Clients.configureProvider(new BlockingClientProvider());
	}

	public static void configureServer() {
		Servers.configureProvider(new BlockingServerProvider());
	}

	public static void configure() {
		configureClient();
		configureServer();
	}

}