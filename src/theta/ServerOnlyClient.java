package theta;

import java.util.Set;

public abstract class ServerOnlyClient extends AbstractClient {

	protected ServerOnlyClient(Set<ClientListener> listeners, Address address) {
		super(listeners, address);
	}

	protected ServerOnlyClient(Address address) {
		super(address);
	}

	@Override
	public final void bind() {
		throw new UnsupportedOperationException();
	}

}