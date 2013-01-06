package theta;

public abstract class ClientAdapter extends GatewayAdapter implements
		ClientListener {

	@Override
	public void received(Address address, Buffer buffer) {
		// Intended to be overridden
	}

}