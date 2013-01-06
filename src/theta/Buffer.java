package theta;

public interface Buffer {

	int read();

	byte readByte();

	short readShort();

	int readInt();

	long readLong();

	float readFloat();

	double readDouble();

	char readChar();

	boolean readBoolean();

	String readString();

	Buffer write(int value);

	Buffer writeByte(byte value);

	Buffer writeShort(short value);

	Buffer writeInt(int value);

	Buffer writeLong(long value);

	Buffer writeFloat(float value);

	Buffer writeDouble(double value);

	Buffer writeChar(char value);

	Buffer writeBoolean(boolean value);

	Buffer writeString(String value);

}