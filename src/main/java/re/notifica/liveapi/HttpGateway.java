package re.notifica.liveapi;

import re.notifica.liveapi.util.Crypto;

public class HttpGateway {

	private Configuration configuration;
	
	public HttpGateway(Configuration configuration) {
		this.configuration = configuration;
	}
	
	public String verify(String publicKey, String challenge) throws LiveApiException {
		if (publicKey.equals(configuration.getPublicKey())) {
			return Crypto.generateHmacBase64String(challenge, configuration.getPrivateKey());			
		} else {
			throw new LiveApiPublicKeyException("invalid public key");
		}
	}
	
	public Boolean validate(String publicKey, String payload, String signature) throws LiveApiException {
		if (publicKey.equals(configuration.getPublicKey())) {
			byte[] calculatedHmac = Crypto.generateHmacBase64(payload, configuration.getPrivateKey());
			return Crypto.compareHmac(calculatedHmac, signature.getBytes());
		} else {
			throw new LiveApiPublicKeyException("invalid public key");
		}
	}
	
}
