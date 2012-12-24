package theta.events;

import theta.Client;
import theta.Server;

public final class ClientDataEvent extends ServerClientEvent {

	public ClientDataEvent(Server server, Client client) {
		super(server, client);
	}

}