package re.notifica.liveapi.example;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.Map;

import javax.ws.rs.core.UriBuilder;

import re.notifica.liveapi.LiveApi;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.net.httpserver.HttpServer;


@SuppressWarnings("restriction")
public class LiveApiHttpServer {
	
	public static void main(String[] args) throws IOException {
		if (args.length < 3) {
	        throw new IllegalStateException("Please start Notificare Live API Server with PORT, PRIVATE_KEY and PUBLIC_KEY as program arguments");
		} else {
			int port;
			try {
				port = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				throw new IllegalStateException("Please start Notificare Live API Server with PORT, PRIVATE_KEY and PUBLIC_KEY as program arguments");
			}
	        System.out.println("Starting Notificare Live API Server...\n");
	        HttpServer notificareLiveApiHTTPServer = createHttpServer(port, args[1], args[2]);
	        notificareLiveApiHTTPServer.start();
	        System.out.println(String.format("Jersey Application Server started with WADL available at %sapplication.wadl\n", getServerURI(port)));
	        System.out.println("Started Notificare Live API Server Successfully !!!");			
		}
    }
 
    private static HttpServer createHttpServer(int port, String privateKey, String publicKey) throws IOException {
        ResourceConfig notificareLiveApiResourceConfig = new PackagesResourceConfig("re.notifica.liveapi.example");
        LiveApi liveApi = new LiveApi(privateKey, publicKey);
        System.out.println("using Notificare LiveApi version " + LiveApi.VERSION);
        notificareLiveApiResourceConfig.getProperties().put("re.notifica.liveapi.HttpGateway", liveApi.httpGateway());
        return HttpServerFactory.create(getServerURI(port), notificareLiveApiResourceConfig);
    }
 
    private static URI getServerURI(int port) {
        return UriBuilder.fromUri("http://" + getHostName() + "/").port(port).build();
    }
 
    private static String getHostName() {
        String hostName = "localhost";
        try {
            hostName = InetAddress.getLocalHost().getCanonicalHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return hostName;
    }
}
