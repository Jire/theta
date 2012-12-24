Theta
=====

_Theta_ is a simple networking library that combines the high-performance Java networking with the easy-to-use [Mint](http://mintlib.org) event system; you get the best of both worlds.

### Usage

Creating a high-performance server with _Theta_ is simple. Just create a _Server_ (theta.Server). In this example, I will be using a _CycleServer_ (theta.CycleServer). A _cycle server_ is a server which is non-blocking and reacts with polling at some cycle for data rather than accepting data and connections in real-time. Cycle servers are good for scaling for many thousands of simultaneous connections while leveraging work load as minimal for the host.

    import theta.Address;
    import theta.Server;
    import theta.CycleServer;
    import theta.events.ClientAcceptEvent;
    import theta.events.ClientDataEvent;
    
    import mint.event.EventManager;
    import mint.event.EventListener;
    
    class Example implements EventListener {
    
        public static void main(String[] args) {
            // Theta's reactor server depends on a Mint event manager
            EventManager eventManager = new UniversalEventManager();
            
            // This is our network address; it allows us to specify
            // the host and port that we want to bind the server to
            Address address = Address.create(55555); // we will use ("localhost", 55555)
            
            // Note that a cycle server requires a cycle rate (in milliseconds)
            // as well as the maximum number of connections to accept per cycle
            Server server = new CycleServer(eventManager, address, 100, 10); // 100ms cycles, 10 accepts per cycle

            // Let's create our 'Example' instance and register it to listen for events
            eventManager.register(new Example());

            // Finally, start the server; note that this is non-blocking
            server.start();
        }
        
        @EventHandler
        public void onClientAccept(ClientAcceptEvent event) {
            System.out.println("Accepted client: " + event.getClient().getAddress().getHost());
        }
        
        @EventHandler
        public void onClientData(ClientDataEvent event) {
            System.out.println("Client sent byte of value: " + event.getClient().getInput().get());
        }
        
    }