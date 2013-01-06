package theta;

public interface GatewayProvider<T extends Gateway<?>> {

	T provide(Address address);

}