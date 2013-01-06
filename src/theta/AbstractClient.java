package theta;

import java.util.Set;

public abstract class AbstractClient extends AbstractGateway<ClientListener>
		implements Client {

	private Buffer buffer;

	protected AbstractClient(Set<ClientListener> listeners, Address address) {
		super(listeners, address);
	}

	protected AbstractClient(Address address) {
		super(address);
	}

	protected final void setBuffer(Buffer buffer) {
		this.buffer = buffer;
	}

	@Override
	public final Buffer getBuffer() {
		return buffer;
	}

}