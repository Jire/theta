package theta;

import java.util.Set;

class MockClient extends AbstractClient {

	protected MockClient(Set<ClientListener> listeners, Address address) {
		super(listeners, address);
	}

	protected MockClient(Address address) {
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