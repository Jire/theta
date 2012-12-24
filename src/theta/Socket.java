package theta;

import mint.event.EventManager;

public interface Socket {

	EventManager getEventManager();

	Address getAddress();

}