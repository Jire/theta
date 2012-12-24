package theta;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;

import mint.event.EventManager;
import mint.inject.Singleton;
import theta.events.ClientAcceptEvent;

@Singleton
public final class CycleServer extends AbstractServer implements Runnable {

	private final int cycleRate;
	private final int cycleAccepts;

	private Selector selector;
	private ServerSocketChannel channel;

	public CycleServer(EventManager eventManager, Address address,
			int cycleRate, int cycleAccepts) {
		super(eventManager, address);
		this.cycleRate = cycleRate;
		this.cycleAccepts = cycleAccepts;
	}

	@Override
	public void start() {
		new Thread(this, "Server").start();
	}

	@Override
	public void run() {
		try {
			selector = Selector.open();
			channel = ServerSocketChannel.open();
			channel.configureBlocking(false);
			channel.socket().bind(getAddress().toSocketAddress());
			channel.register(selector, SelectionKey.OP_ACCEPT);

			while (channel.isOpen()) {
				Map<SelectionKey, Client> clients = new HashMap<SelectionKey, Client>();
				for (Client client : getClients())
					clients.put(client.getKey(), client);

				selector.selectNow();
				for (SelectionKey selectionKey : selector.selectedKeys()) {
					if (selectionKey.isAcceptable()) {
						for (int i = 0; i < cycleAccepts; i++) {
							SocketChannel clientSocket = channel.accept();
							if (clientSocket == null) {
								break;
							}
							clientSocket.configureBlocking(false);
							SelectionKey key = clientSocket.register(selector,
									SelectionKey.OP_READ);
							Client client = new Client(getEventManager(),
									Address.create(clientSocket.socket()
											.getInetAddress(), getAddress()
											.getPort()), this, key);
							getClients().add(client);
							getEventManager().dispatchEvent(
									new ClientAcceptEvent(this, client));
						}
					}
					if (selectionKey.isReadable()) {
						Client client = clients.get(selectionKey);
						client.handleIncomingData();
					}
				}
				Thread.sleep(cycleRate);
			}
		} catch (Exception e) {
		}
	}

}