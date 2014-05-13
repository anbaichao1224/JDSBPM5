package net.kzxd.dj.action;

import java.io.InputStream;

import net.itjds.common.property.XMLProperties;
import net.itjds.common.util.ClassUtility;

public class DjProcessConfig {
	
	public static final String CONFIG_FILENAME = "djprocess_config.xml";
	 private static XMLProperties properties = null;
	 
	 public static String getValue(String name) {
	        init();
	        if (properties != null)
	            return properties.getProperty(name);
	        else
	            return null;
	    }
	 public static String[] getValues(String name) {
	        init();
	        if (properties != null)
	            return properties.getProperties(name);
	        else
	            return new String[0];
	    }
	 private static void init() {
		    
	        if (properties == null) {
	            InputStream is = ClassUtility.loadResource(CONFIG_FILENAME);
	            properties = new XMLProperties(is);
	        }
	    }
}
