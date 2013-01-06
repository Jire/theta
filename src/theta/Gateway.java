package theta;

public interface Gateway<L extends GatewayListener> {

	void register(L listener);

	Address getAddress();

	boolean bind();

	boolean close();

}