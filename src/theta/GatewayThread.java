package theta;

public final class GatewayThread extends Thread {

	public GatewayThread(String name) {
		super(name);
	}

	public GatewayThread(RunnableGateway<?> gateway, String name) {
		super(gateway, name);
	}

	public GatewayThread(RunnableGateway<?> gateway) {
		this(gateway, gateway.getAddress().toString());
	}

}