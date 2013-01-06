package theta.blocking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import theta.Buffer;

public final class BlockingBuffer implements Buffer {

	private final DataInputStream in;
	private final DataOutputStream out;

	public BlockingBuffer(DataInputStream in, DataOutputStream out) {
		this.in = in;
		this.out = out;
	}

	public BlockingBuffer(Socket socket) throws IOException {
		this(new DataInputStream(socket.getInputStream()),
				new DataOutputStream(socket.getOutputStream()));
	}

	@Override
	public int read() {
		try {
			return in.read();
		} catch (IOException e) {
			return -1;
		}
	}

	@Override
	public void read(byte[] bytes) {
		try {
			in.read(bytes);
		} catch (IOException e) {
		}
	}

	@Override
	public void read(byte[] bytes, int off, int len) {
		try {
			in.read(bytes, off, len);
		} catch (IOException e) {
		}
	}

	@Override
	public byte readByte() {
		try {
			return in.readByte();
		} catch (IOException e) {
			return -1;
		}
	}

	@Override
	public short readShort() {
		try {
			return in.readShort();
		} catch (IOException e) {
			return -1;
		}
	}

	@Override
	public int readInt() {
		try {
			return in.readInt();
		} catch (IOException e) {
			return -1;
		}
	}

	@Override
	public long readLong() {
		try {
			return in.readLong();
		} catch (IOException e) {
			return -1;
		}
	}

	@Override
	public float readFloat() {
		try {
			return in.readFloat();
		} catch (IOException e) {
			return -1;
		}
	}

	@Override
	public double readDouble() {
		try {
			return in.readDouble();
		} catch (IOException e) {
			return -1;
		}
	}

	@Override
	public char readChar() {
		try {
			return in.readChar();
		} catch (IOException e) {
			return 0;
		}
	}

	@Override
	public boolean readBoolean() {
		try {
			return in.readBoolean();
		} catch (IOException e) {
			return false;
		}
	}

	@Override
	public String readString() {
		try {
			return in.readUTF();
		} catch (IOException e) {
			return "";
		}
	}

	@Override
	public Buffer write(int value) {
		try {
			out.write(value);
		} catch (IOException e) {
		}
		return this;
	}

	@Override
	public Buffer write(byte[] bytes) {
		try {
			out.write(bytes);
		} catch (IOException e) {
		}
		return this;
	}

	@Override
	public Buffer write(byte[] bytes, int off, int len) {
		try {
			out.write(bytes, off, len);
		} catch (IOException e) {
		}
		return this;
	}

	@Override
	public Buffer writeByte(byte value) {
		try {
			out.writeByte(value);
		} catch (IOException e) {
		}
		return this;
	}

	@Override
	public Buffer writeShort(short value) {
		try {
			out.writeShort(value);
		} catch (IOException e) {
		}
		return this;
	}

	@Override
	public Buffer writeInt(int value) {
		try {
			out.writeInt(value);
		} catch (IOException e) {
		}
		return this;
	}

	@Override
	public Buffer writeLong(long value) {
		try {
			out.writeLong(value);
		} catch (IOException e) {
		}
		return this;
	}

	@Override
	public Buffer writeFloat(float value) {
		try {
			out.writeFloat(value);
		} catch (IOException e) {
		}
		return this;
	}

	@Override
	public Buffer writeDouble(double value) {
		try {
			out.writeDouble(value);
		} catch (IOException e) {
		}
		return this;
	}

	@Override
	public Buffer writeChar(char value) {
		try {
			out.writeChar(value);
		} catch (IOException e) {
		}
		return this;
	}

	@Override
	public Buffer writeBoolean(boolean value) {
		try {
			out.writeBoolean(value);
		} catch (IOException e) {
		}
		return this;
	}

	@Override
	public Buffer writeString(String value) {
		try {
			out.writeUTF(value);
		} catch (IOException e) {
		}
		return this;
	}

}