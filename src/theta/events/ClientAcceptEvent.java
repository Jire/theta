package theta.events;

import theta.Client;
import theta.Server;

public final class ClientAcceptEvent extends ServerClientEvent {

	public ClientAcceptEvent(Server server, Client client) {
		super(server, client);
	}

}