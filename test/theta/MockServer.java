package theta;

import java.util.Set;

class MockServer extends AbstractServer {

	protected MockServer(Set<ServerListener> listeners, Address address) {
		super(listeners, address);
	}

	protected MockServer(Address address) {
		super(address);
	}

	@Override
	public boolean bind() {
		return true;
	}

	@Override
	public boolean close() {
		return true;
	}

}