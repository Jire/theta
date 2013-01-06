package theta;

public abstract class GatewayAdapter implements GatewayListener {

	@Override
	public void bound(Address address) {
		// Intended to be overridden
	}

	@Override
	public void closed(Address address) {
		// Intended to be overridden
	}

}