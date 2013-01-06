package theta;

// Not too sure about this because of the ugly implementation requirement
public interface RunnableGateway<L extends GatewayListener> extends Gateway<L>,
		Runnable {

}