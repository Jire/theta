package theta.events;

import mint.event.Event;
import theta.Server;

public abstract class ServerEvent implements Event {

	private final Server server;

	protected ServerEvent(Server server) {
		this.server = server;
	}

	public final Server getServer() {
		return server;
	}

}