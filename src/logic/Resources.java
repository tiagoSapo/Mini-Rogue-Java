
package logic;

import java.net.URL;

public class Resources {
	
	public static final URL getResourceFile(String name) {
            return Resources.class.getResource(name);
	}

}