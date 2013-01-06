package theta.blocking;

import theta.Address;
import theta.GatewayProvider;
import theta.Server;

public final class BlockingServerProvider implements GatewayProvider<Server> {

	@Override
	public Server provide(Address address) {
		return new BlockingServer(address);
	}

}