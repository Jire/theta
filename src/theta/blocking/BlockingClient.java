package theta.blocking;

import java.io.IOException;
import java.net.Socket;

import theta.AbstractClient;
import theta.Address;

class BlockingClient extends AbstractClient {

	private Socket socket;

	protected BlockingClient(Address address) {
		super(address);
	}

	protected BlockingClient(Address address, Socket socket) {
		this(address);
		this.socket = socket;
	}

	protected final Socket getSocket() {
		return socket;
	}

	@Override
	public boolean bind() {
		try {
			socket = new Socket(getAddress().getHost(), getAddress().getPort());
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