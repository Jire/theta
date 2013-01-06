package theta.unblocking;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import theta.AbstractClient;
import theta.Address;
import theta.ClientListener;
import theta.GatewayThread;
import theta.RunnableGateway;

class UnblockingClient extends AbstractClient implements
		RunnableGateway<ClientListener> {

	private SocketChannel channel;
	private final ByteBuffer buffer = ByteBuffer.allocate(512);

	protected UnblockingClient(Address address) {
		super(address);
	}

	protected UnblockingClient(Address address, SocketChannel channel,
			ByteBuffer buffer) throws IOException {
		this(address);
		this.channel = channel;
		setBuffer(new UnblockingBuffer(buffer));
	}

	protected final SocketChannel getChannel() {
		return channel;
	}

	@Override
	public boolean bind() {
		try {
			channel = SocketChannel.open();
			channel.configureBlocking(true);
			channel.socket().connect(getAddress().toSocketAddress());
			setBuffer(new UnblockingBuffer(buffer));
			new GatewayThread(this).start();
			return getChannel() != null && super.bind();
		} catch (IOException e) {
			return false;
		}
	}

	@Override
	public boolean close() {
		try {
			channel.close();
			return super.close();
		} catch (IOException e) {
			return false;
		}
	}

	@Override
	public void run() {
		while (channel != null) {
			long start = System.currentTimeMillis();
			try {
				buffer.flip();
				channel.write(buffer);
			} catch (IOException e) {
				e.printStackTrace();
			}
			long elapsed = UnblockingNetwork.getCycleRate()
					- (System.currentTimeMillis() - start);
			if (elapsed > 0) {
				try {
					Thread.sleep(elapsed);
				} catch (InterruptedException e) {
					return;
				}
			}
		}
	}

}