package theta.unblocking;

import theta.Clients;
import theta.Servers;

public final class UnblockingNetwork {

	private static final int DEFAULT_CYCLE_RATE = 100;

	private static int cycleRate = DEFAULT_CYCLE_RATE;

	static int getCycleRate() {
		return cycleRate;
	}

	public static void configureCycle(int cycleRate) {
		UnblockingNetwork.cycleRate = cycleRate;
	}

	public static void configureClient() {
		Clients.configureProvider(new UnblockingClientProvider());
	}

	public static void configureServer() {
		Servers.configureProvider(new UnblockingServerProvider());
	}

	public static void configure() {
		configureClient();
		configureServer();
	}

}