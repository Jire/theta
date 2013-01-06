package theta;

public interface Gateway<L extends GatewayListener> {

	void register(L listener);

	Address getAddress();

	void close();

	void bind();

}