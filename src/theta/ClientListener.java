package theta;

public interface ClientListener extends GatewayListener {

	void received(Address address, Buffer buffer);

}