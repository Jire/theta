package theta.events;

import theta.Client;
import theta.Server;

public abstract class ServerClientEvent extends ServerEvent {

	private final Client client;

	protected ServerClientEvent(Server server, Client client) {
		super(server);
		this.client = client;
	}

	public final Client getClient() {
		return client;
	}

}