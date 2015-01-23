package re.notifica.liveapi.example;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import re.notifica.liveapi.HttpGateway;
import re.notifica.liveapi.LiveApiException;

import com.sun.jersey.api.core.PackagesResourceConfig;


@Path("api")
public class LiveApiHttpResource {

	@Context
	Application application;
	
	/**
	 * Verify this Live API endpoint
	 * @param incomingPublicKey The public key as found in the Notificare dashboard
	 * @param challenge A challenge to be signed by our private key
	 * @return The signed challenge
	 */
	@GET
    @Produces(MediaType.TEXT_PLAIN)
	public Response get(@HeaderParam("x-notificare-public-key") String incomingPublicKey, @QueryParam("challenge") String challenge) {
		if (challenge == null) {
			// No challenge, means this is not a valid verification request, send a 400 BAD REQUEST
			throw new WebApplicationException(Status.BAD_REQUEST);
		} else if (incomingPublicKey == null) {
			// Unknown public key, send a 404 NOT FOUND
			throw new WebApplicationException(Status.NOT_FOUND);
		} else {
			try {
				PackagesResourceConfig config = (PackagesResourceConfig) application;
				HttpGateway httpGateway = (HttpGateway) config.getProperty("re.notifica.liveapi.HttpGateway");
				String signature = httpGateway.verify(incomingPublicKey, challenge);
				return Response.ok(signature).build();
			} catch (LiveApiException e) {
				throw new WebApplicationException(Status.BAD_REQUEST);
			} catch (Exception e) {
				throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
			}
		}
    }
	
	/**
	 * Incoming event on the Live API, should be validated before processing 
	 * @param incomingPublicKey The public key as found in the Notificare dashboard, can be used to determine which private key should be used
	 * @param signature A HMAC-RSA-256 digest of the payload 
	 * @return 
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response post(@HeaderParam("x-notificare-public-key") String incomingPublicKey, @HeaderParam("x-notificare-signature") String signature, String payload) {
		System.out.println(payload);
		if (incomingPublicKey == null) {
			// Unknown public key, send a 404 NOT FOUND
			throw new WebApplicationException(Status.NOT_FOUND);
		} else if (signature == null) {
			// No signature, means this is not a valid request, send a 400 BAD REQUEST
			throw new WebApplicationException(Status.BAD_REQUEST);
		} else {
			try {
				
				PackagesResourceConfig config = (PackagesResourceConfig) application;
				HttpGateway httpGateway = (HttpGateway) config.getProperty("re.notifica.liveapi.HttpGateway");
				
				if (httpGateway.validate(incomingPublicKey, payload, signature)) {

					// ...
					
					// Process the payload
					
					// ...

					return Response.ok().build();

				} else {
					throw new LiveApiException("invalid signature");
				}
				
			} catch (WebApplicationException e) {
				throw new WebApplicationException(Status.BAD_REQUEST);
			} catch (Exception e) {
				throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
			}
		}
	}
}
