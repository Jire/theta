package theta.unblocking;

import theta.Address;
import theta.GatewayProvider;
import theta.Server;

public final class UnblockingServerProvider implements GatewayProvider<Server> {

	@Override
	public Server provide(Address address) {
		return new UnblockingServer(address);
	}

}