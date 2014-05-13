package com.kzxd.doctt;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class TaoTouConfigReader {

	private static Properties properties = new Properties();
	static {
		InputStream is = TaoTouConfigReader.class.getClassLoader().getResourceAsStream("taotou.properties");
	    try {
			properties.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Map<String, String> ttP = new HashMap<String, String>();
		Enumeration<?> names = properties.propertyNames();   
        while (names.hasMoreElements()) {   
            String name = (String) names.nextElement();   
            ttP.put(name, properties.getProperty(name)); 
        }  
        for(Object key:ttP.keySet()){
        }
	}
	
	/**
	 * 得到套头时候需要向文档中插入数据的字段名称，以及word中的书签名字
	 * @return
	 */
	public static Map<String, String>getTaotouProperties(){
		Map<String, String> ttP = new HashMap<String, String>();
		Enumeration<?> names = properties.propertyNames();   
        while (names.hasMoreElements()) {   
            String name = (String) names.nextElement();   
            ttP.put(name, properties.getProperty(name)); 
        } 
		
		return ttP;
	}
	
	public static String getDriverClass(){
		return properties.getProperty("driverClass");
	}
	public static String getURL(){
		return properties.getProperty("url");
	}
	public static String getUsername(){
		return properties.getProperty("username");
	}
	public static String getPassword(){
		return properties.getProperty("password");
	}
}
