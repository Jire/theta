package theta;

public interface Gateway<L extends GatewayListener> {

	Address getAddress();

	void close();

	void bind();

	void register(L listener);

}