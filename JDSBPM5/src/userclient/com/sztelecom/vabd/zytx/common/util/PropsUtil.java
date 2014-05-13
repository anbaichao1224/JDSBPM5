package com.sztelecom.vabd.zytx.common.util;

import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileNotFoundException;
import org.apache.log4j.Logger;

/**
 * @Title: 该类主要用于系统配置文件的读与写
 * @Description:
 * @Copyright: Copyright (c) 2007
 * @Company: 深圳电信局增值业务部
 * @author 谢亨骏
 * @version 1.0
 */

public class PropsUtil {
	private static Logger logger = Logger.getLogger(PropsUtil.class.getName());

	private final static String CONFIG_FILE = "/conf/sysConfig.properties";

	private static PropsUtil property;

	private Properties properties;

	private Object initLock = new Object();

	public static PropsUtil getInstance() {
		logger.info("进入ProsUtil");
		if (property == null) {
			property = new PropsUtil();
		}
		return property;
	}

	private PropsUtil() {
		synchronized (initLock) {
			try {
				logger.info("初始化属性文件!");
				properties = new Properties();
				InputStream fis = getClass().getResourceAsStream(CONFIG_FILE);
				logger.info("获得InputStream fis");
				if (fis == null) {
					logger.info("InputStream fis为空");
				}
				properties.load(fis);
				logger.info("载入成功");
			} catch (FileNotFoundException e) {
				logger.error("属性文件不存在", e);
			} catch (IOException e) {
				logger.error("IO异常", e);
			}
		}
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	public void setProperty(String key, String value) {
		properties.setProperty(key, value);
	}

}