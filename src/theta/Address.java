package theta;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

public final class Address {

	private final String host;
	private final int port;

	private Address(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public SocketAddress toSocketAddress() {
		return new InetSocketAddress(getHost(), getPort());
	}

	public static Address create(String host, int port) {
		return new Address(host, port);
	}

	public static Address create(int port) {
		return create("localhost", port);
	}

	public static Address create(InetAddress address, int port) {
		return create(address.getHostName(), port);
	}

}