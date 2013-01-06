package theta;

import java.util.Set;

public abstract class AbstractServer extends AbstractGateway<ServerListener>
		implements Server {

	protected AbstractServer(Set<ServerListener> listeners, Address address) {
		super(listeners, address);
	}

	protected AbstractServer(Address address) {
		super(address);
	}

}