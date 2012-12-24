package theta;

import java.util.Set;

import mint.inject.ImplementedBy;

@ImplementedBy(CycleServer.class)
public interface Server extends Socket {

	void start();

	Set<Client> getClients();

}