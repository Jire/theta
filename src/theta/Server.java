package theta;

import java.util.Set;

public interface Server extends Gateway<ServerListener> {

	Set<Client> getClients();

	boolean isBound();

}