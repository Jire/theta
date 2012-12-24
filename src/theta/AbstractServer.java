package theta;

import java.util.HashSet;
import java.util.Set;

import mint.event.EventManager;

public abstract class AbstractServer extends AbstractSocket implements Server {

	private final Set<Client> clients;

	public AbstractServer(EventManager eventManager, Address address,
			Set<Client> clients) {
		super(eventManager, address);
		this.clients = clients;
	}

	public AbstractServer(EventManager eventManager, Address address) {
		this(eventManager, address, new HashSet<Client>());
	}

	@Override
	public final Set<Client> getClients() {
		return clients;
	}

}