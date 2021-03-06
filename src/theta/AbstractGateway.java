package theta;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractGateway<L extends GatewayListener> implements
		Gateway<L> {

	private final Set<L> listeners;
	private final Address address;

	protected AbstractGateway(Set<L> listeners, Address address) {
		this.listeners = listeners;
		this.address = address;
	}

	protected AbstractGateway(Address address) {
		this(new HashSet<L>(), address);
	}

	@Override
	public void register(L listener) {
		listeners.add(listener);
	}

	@Override
	public final Address getAddress() {
		return address;
	}

	@Override
	public boolean bind() {
		for (L listener : getListeners())
			listener.bound(getAddress());
		return true;
	}

	@Override
	public boolean close() {
		for (L listener : getListeners())
			listener.closed(getAddress());
		return true;
	}

	@Override
	public String toString() {
		return getAddress().toString();
	}

	protected final Set<L> getListeners() {
		return listeners;
	}

}