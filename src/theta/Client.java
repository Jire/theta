package theta;

public interface Client extends Gateway<ClientListener> {

	Buffer getBuffer();

}