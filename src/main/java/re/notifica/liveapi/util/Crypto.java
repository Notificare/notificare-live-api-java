package re.notifica.liveapi.util;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import re.notifica.liveapi.LiveApiException;

public class Crypto {

	private static final String HMAC_SHA256_ALGORITHM = "HmacSHA256";
	
	public static byte[] generateHmac(String data, String key) throws LiveApiException {
		byte[] result;
		try {
			// Construct an HMAC-SHA-256 key from the private key
			SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA256_ALGORITHM);
	
			// Initialize an HMAC-SHA-256 Mac instance with the signing key generated above
			Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
			mac.init(signingKey);
	
			// Compute the HMAC on the challenge bytes
			result = mac.doFinal(data.getBytes());
	
		} catch (Exception e) {
			// Error occurred in creating the HMAC
			throw new LiveApiException("could not generate hmac");
		}
		return result;
	}
	
	public static byte[] generateHmacBase64(String data, String key) throws LiveApiException {
		return Base64.encode(generateHmac(data, key));
	}
	
	public static String generateHmacBase64String(String data, String key) throws LiveApiException {
		String result;
		try {
			result = new String(generateHmacBase64(data, key), "US-ASCII");
		} catch (UnsupportedEncodingException e) {
			throw new LiveApiException("could not generate hmac");
		}
		return result;
	}
	
	public static Boolean compareHmac(String base64Hmac1, String base64Hmac2) {
		return compareHmac(base64Hmac1.getBytes(), base64Hmac2.getBytes());
	}
	
	public static Boolean compareHmac(byte[] hmac1, byte[] hmac2) {
		return Arrays.equals(hmac1, hmac2);
	}
	
}
