package theta.blocking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import theta.AbstractServer;
import theta.Address;
import theta.Client;
import theta.GatewayThread;
import theta.RunnableGateway;
import theta.ServerListener;
import theta.ServerOnlyClient;

class BlockingServer extends AbstractServer implements
		RunnableGateway<ServerListener> {

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
			new GatewayThread(this).start();
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

	@Override
	public void run() {
		while (!getSocket().isClosed() && getSocket().isBound()) {
			Socket clientSocket;
			try {
				clientSocket = getSocket().accept();
			} catch (IOException e) {
				continue;
			}
			Client client = new ServerOnlyClient(new BlockingClient(
					getAddress(), clientSocket));
			getClients().add(client);
			for (ServerListener listener : getListeners())
				listener.connected(client);
		}
	}

}