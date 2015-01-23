package re.notifica.liveapi;

public class LiveApi {

    public static final String VERSION = new LibraryProperties().version();

    private Configuration configuration;
    
    /**
     * Create a new instance of a Notificare LiveApi 
     * @param privateKey
     * @param publicKey
     */
    public LiveApi(String privateKey, String publicKey) {
    	this.configuration = new Configuration(privateKey, publicKey);
    }
    
    /**
     * Get a gateway to process incoming HTTP requests
     * @return
     */
    public HttpGateway httpGateway() {
    	return new HttpGateway(configuration);
    }
    
}
