package theta.blocking;

import java.io.IOException;
import java.net.ServerSocket;

import theta.AbstractServer;
import theta.Address;

class BlockingServer extends AbstractServer {

	private ServerSocket socket;

	protected BlockingServer(Address address) {
		super(address);
	}

	protected final ServerSocket getSocket() {
		return socket;
	}

	@Override
	public boolean bind() {
		try {
			socket = new ServerSocket(getAddress().getPort());
			return getSocket() != null && super.bind();
		} catch (IOException e) {
			return false;
		}
	}

	@Override
	public boolean close() {
		try {
			socket.close();
			return super.close();
		} catch (IOException e) {
			return false;
		}
	}

}