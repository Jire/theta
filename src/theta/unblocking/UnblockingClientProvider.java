package theta.unblocking;

import theta.Address;
import theta.Client;
import theta.GatewayProvider;

public final class UnblockingClientProvider implements GatewayProvider<Client> {

	@Override
	public Client provide(Address address) {
		return new UnblockingClient(address);
	}

}