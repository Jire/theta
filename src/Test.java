import theta.Client;
import theta.Server;
import theta.ServerAdapter;
import theta.Servers;

public final class Test {

	public static void main(String[] args) {
		Server server = Servers.create(43594);
		server.register(new ServerAdapter() {
			@Override
			public void connected(Client client) {
				System.out.println("Connected client: " + client);
			}
		});
		server.bind();
	}

}