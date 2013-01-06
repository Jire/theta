package theta;

public interface GatewayListener {

	void bound(Address address);

	void closed(Address address);

}