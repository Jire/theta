package theta;

import mint.event.EventManager;

public interface Socket {

	public EventManager getEventManager();

	public Address getAddress();

}