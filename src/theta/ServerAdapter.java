package theta;

public abstract class ServerAdapter implements ServerListener {

	@Override
	public void connected(Client client) {
		// Intended to be overridden
	}

	@Override
	public void disconnected(Client client) {
		// Intended to be overridden
	}

}