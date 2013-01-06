package theta;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractServer extends AbstractGateway<ServerListener>
		implements Server {

	private final Set<Client> clients;

	private boolean bound;

	protected AbstractServer(Set<ServerListener> listeners, Address address,
			Set<Client> clients) {
		super(listeners, address);
		this.clients = clients;
	}

	protected AbstractServer(Set<ServerListener> listeners, Address address) {
		super(listeners, address);
		this.clients = new HashSet<Client>();
	}

	protected AbstractServer(Address address, Set<Client> clients) {
		super(address);
		this.clients = clients;
	}

	protected AbstractServer(Address address) {
		this(address, new HashSet<Client>());
	}

	protected final void setBound(boolean bound) {
		this.bound = bound;
	}

	@Override
	public boolean bind() {
		setBound(true);
		return super.bind();
	}

	@Override
	public boolean close() {
		setBound(false);
		return super.close();
	}

	@Override
	public final Set<Client> getClients() {
		return clients;
	}

	@Override
	public boolean isBound() {
		return bound;
	}

}