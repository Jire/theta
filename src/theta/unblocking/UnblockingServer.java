package theta.unblocking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import theta.AbstractServer;
import theta.Address;
import theta.Client;
import theta.GatewayThread;
import theta.RunnableGateway;
import theta.ServerListener;
import theta.ServerOnlyClient;

class UnblockingServer extends AbstractServer implements
		RunnableGateway<ServerListener> {

	private final ByteBuffer buffer = ByteBuffer.allocate(512);

	private ServerSocketChannel channel;
	private Selector selector;
	private SelectionKey key;

	protected UnblockingServer(Address address) {
		super(address);
	}

	@Override
	public boolean bind() {
		try {
			channel = ServerSocketChannel.open();
			selector = Selector.open();

			channel.socket().bind(getAddress().toSocketAddress());
			channel.configureBlocking(false);

			key = channel.register(selector, SelectionKey.OP_ACCEPT);
			key.attach(this);

			new GatewayThread(this).start();

			return super.bind();
		} catch (IOException e) {
			return false;
		}
	}

	@Override
	public boolean close() {
		try {
			for (Client client : getClients())
				client.close();
			channel.close();
			return super.close();
		} catch (IOException e) {
			return false;
		}
	}

	@Override
	public void run() {
		while (selector.isOpen()) {
			long start = System.currentTimeMillis();
			try {
				if (selector.selectNow() != 0) {
					for (Iterator<SelectionKey> i = selector.selectedKeys()
							.iterator(); i.hasNext();) {
						SelectionKey key = i.next();
						i.remove();
						if (key.isConnectable()) {
							((SocketChannel) key.channel()).finishConnect();
						} else if (key.isAcceptable()) {
							SocketChannel clientChannel = channel.accept();
							clientChannel.configureBlocking(false);
							clientChannel.socket().setTcpNoDelay(true);
							clientChannel.register(selector,
									SelectionKey.OP_READ);

							Client client = new UnblockingClient(
									Address.create((InetSocketAddress) clientChannel
											.socket().getRemoteSocketAddress()),
									clientChannel, buffer);
							key.attach(client);
							if (getClients().add(
									client = new ServerOnlyClient(client)))
								for (ServerListener listener : getListeners())
									listener.connected(client);
						} else if (key.isReadable()) {
							UnblockingClient client = (UnblockingClient) this.key
									.attachment();
							if (client != null
									&& client.getChannel().read(buffer) != -1) {
								buffer.flip();
								for (ServerListener listener : getListeners())
									listener.dataReceived(client);
								buffer.compact();
								buffer.clear();
							}
						} else if (key.isWritable()) {
						}
					}
				}
			} catch (IOException e) {
			}
			try {
				long elapsed = UnblockingNetwork.getCycleRate()
						- (System.currentTimeMillis() - start);
				if (elapsed > 0)
					Thread.sleep(elapsed);
			} catch (InterruptedException e) {
				return;
			}
		}
	}

}