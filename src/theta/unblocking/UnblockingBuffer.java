package theta.unblocking;

import java.nio.ByteBuffer;

import theta.Buffer;

final class UnblockingBuffer implements Buffer {

	private final ByteBuffer backing;

	public UnblockingBuffer(ByteBuffer backing) {
		this.backing = backing;
	}

	final ByteBuffer getBacking() {
		return backing;
	}

	@Override
	public int read() {
		return getBacking().get();
	}

	@Override
	public void read(byte[] bytes) {
		getBacking().get(bytes);
	}

	@Override
	public void read(byte[] bytes, int off, int len) {
		getBacking().get(bytes, off, len);
	}

	@Override
	public byte readByte() {
		return getBacking().get();
	}

	@Override
	public short readShort() {
		return getBacking().getShort();
	}

	@Override
	public int readInt() {
		return getBacking().getInt();
	}

	@Override
	public long readLong() {
		return getBacking().getLong();
	}

	@Override
	public float readFloat() {
		return getBacking().getFloat();
	}

	@Override
	public double readDouble() {
		return getBacking().getDouble();
	}

	@Override
	public char readChar() {
		return getBacking().getChar();
	}

	@Override
	public boolean readBoolean() {
		return getBacking().get() == 1;
	}

	@Override
	public String readString() {
		StringBuilder builder = new StringBuilder();
		char c;
		while ((c = readChar()) != '\n')
			builder.append(c);
		return builder.toString();
	}

	@Override
	public Buffer write(int value) {
		getBacking().put((byte) value);
		return this;
	}

	@Override
	public Buffer write(byte[] bytes) {
		getBacking().put(bytes);
		return this;
	}

	@Override
	public Buffer write(byte[] bytes, int off, int len) {
		getBacking().put(bytes, off, len);
		return this;
	}

	@Override
	public Buffer writeByte(byte value) {
		getBacking().put(value);
		return this;
	}

	@Override
	public Buffer writeShort(short value) {
		getBacking().putShort(value);
		return this;
	}

	@Override
	public Buffer writeInt(int value) {
		getBacking().putInt(value);
		return this;
	}

	@Override
	public Buffer writeLong(long value) {
		getBacking().putLong(value);
		return this;
	}

	@Override
	public Buffer writeFloat(float value) {
		getBacking().putFloat(value);
		return this;
	}

	@Override
	public Buffer writeDouble(double value) {
		getBacking().putDouble(value);
		return this;
	}

	@Override
	public Buffer writeChar(char value) {
		getBacking().putChar(value);
		return this;
	}

	@Override
	public Buffer writeBoolean(boolean value) {
		write(value ? 1 : 0);
		return this;
	}

	@Override
	public Buffer writeString(String value) {
		for (char c : value.toCharArray())
			writeChar(c);
		writeChar('\n');
		return this;
	}

}