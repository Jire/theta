package theta;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

import mint.event.EventManager;
import theta.events.ClientDataEvent;

public final class Client extends AbstractSocket {

	private static final int DEFAULT_BUFFER_SIZE = 512;

	private final Server server;
	private final SelectionKey key;
	private final ByteBuffer inData = ByteBuffer
			.allocateDirect(DEFAULT_BUFFER_SIZE);
	private final SocketChannel channel;

	public Client(EventManager eventManager, Address address, Server server,
			SelectionKey key) {
		super(eventManager, address);
		this.server = server;
		this.key = key;
		this.channel = (SocketChannel) key.channel();
	}

	public Server getServer() {
		return server;
	}

	public SelectionKey getKey() {
		return key;
	}

	public ByteBuffer getInput() {
		return inData;
	}

	public SocketChannel getChannel() {
		return channel;
	}

	public void send(ByteBuffer buffer) {
		buffer.flip();

		try {
			channel.write(buffer);
		} catch (IOException ex) {
			ex.printStackTrace();
			disconnect();
		}
	}

	public void disconnect() {
		try {
			channel.close();
			key.cancel();
		} catch (IOException e) {
		}
	}

	public void handleIncomingData() {
		try {
			if (channel.read(inData) == -1) {
				disconnect();
				return;
			}
			inData.flip();
			getEventManager().dispatchEvent(new ClientDataEvent(server, this));
			inData.clear();
		} catch (Exception e) {
			disconnect();
		}
	}

}