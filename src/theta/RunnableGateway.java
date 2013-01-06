package theta;

// Not too sure about this because of the ugly implementation requirement
public interface RunnableGateway<L extends ServerListener> extends Gateway<L>,
		Runnable {

}