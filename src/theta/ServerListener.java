package theta;

public interface ServerListener extends GatewayListener {

	void connected(Client client);

	void disconnected(Client client);

}