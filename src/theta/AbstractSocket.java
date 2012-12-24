package theta;

import mint.event.EventManager;

public abstract class AbstractSocket implements Socket {

	private final EventManager eventManager;
	private final Address address;

	public AbstractSocket(EventManager eventManager, Address address) {
		this.eventManager = eventManager;
		this.address = address;
	}

	@Override
	public final EventManager getEventManager() {
		return eventManager;
	}

	@Override
	public final Address getAddress() {
		return address;
	}

}