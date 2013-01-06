package theta;

public final class ServerOnlyClient implements Client {

	private final Client client;

	public ServerOnlyClient(Client client) {
		this.client = client;
	}

	@Override
	public void register(ClientListener listener) {
		client.register(listener);
	}

	@Override
	public Address getAddress() {
		return client.getAddress();
	}

	@Override
	public boolean bind() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean close() {
		return client.close();
	}

}