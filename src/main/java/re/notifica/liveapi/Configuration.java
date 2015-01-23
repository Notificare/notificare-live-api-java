package re.notifica.liveapi;

public class Configuration {

	private String privateKey;
	private String publicKey;
	
	public Configuration(String privateKey, String publicKey) {
		this.privateKey = privateKey;
		this.publicKey = publicKey;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public String getPublicKey() {
		return publicKey;
	}
	
}
