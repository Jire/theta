package theta;

import java.util.Set;

public abstract class AbstractClient extends AbstractGateway<ClientListener>
		implements Client {

	protected AbstractClient(Set<ClientListener> listeners, Address address) {
		super(listeners, address);
	}

	protected AbstractClient(Address address) {
		super(address);
	}

}