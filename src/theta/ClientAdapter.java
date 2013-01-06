package theta;

public abstract class ClientAdapter implements ClientListener {

	@Override
	public void connected(Address address) {
		// Intended to be overridden
	}

	@Override
	public void disconnected(Address address) {
		// Intended to be overridden
	}

}