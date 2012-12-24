package theta;

import java.util.Set;

public interface Server extends Socket {

	public void start();

	public Set<Client> getClients();

}