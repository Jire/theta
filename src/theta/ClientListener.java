package theta;

public interface ClientListener extends GatewayListener {

	void connected(Address address);

	void disconnected(Address address);

}