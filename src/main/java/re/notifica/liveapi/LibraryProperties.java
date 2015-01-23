package re.notifica.liveapi;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Load properties for this library
 * @author Joris Verbogt <joris@notifica.re>
 */
public class LibraryProperties {
	
    public static final String PROPERTY_FILE = "liveapi.properties";
    public static final String VERSION_PROPERTY_NAME = "notificare.liveapi.version";

    private final Properties properties = loadProperties(PROPERTY_FILE);

    public String version() {
        return properties.getProperty(VERSION_PROPERTY_NAME);
    }

    private Properties loadProperties(String propertyFile) {
    	try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(propertyFile);
            Properties p = new Properties();
            p.load(is);
            return p;
        } catch (IOException e) {
            throw new LiveApiException("Couldn't load " + PROPERTY_FILE + " can't continue", e);
        }
	}
}

