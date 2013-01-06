package theta.blocking;

import theta.Address;
import theta.Client;
import theta.GatewayProvider;

public final class BlockingClientProvider implements GatewayProvider<Client> {

	@Override
	public Client provide(Address address) {
		return new BlockingClient(address);
	}

}