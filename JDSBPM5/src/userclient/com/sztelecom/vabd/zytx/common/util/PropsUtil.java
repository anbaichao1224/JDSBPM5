package com.sztelecom.vabd.zytx.common.util;

import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileNotFoundException;
import org.apache.log4j.Logger;

/**
 * @Title: ������Ҫ����ϵͳ�����ļ��Ķ���д
 * @Description:
 * @Copyright: Copyright (c) 2007
 * @Company: ���ڵ��ž���ֵҵ��
 * @author л�࿥
 * @version 1.0
 */

public class PropsUtil {
	private static Logger logger = Logger.getLogger(PropsUtil.class.getName());

	private final static String CONFIG_FILE = "/conf/sysConfig.properties";

	private static PropsUtil property;

	private Properties properties;

	private Object initLock = new Object();

	public static PropsUtil getInstance() {
		logger.info("����ProsUtil");
		if (property == null) {
			property = new PropsUtil();
		}
		return property;
	}

	private PropsUtil() {
		synchronized (initLock) {
			try {
				logger.info("��ʼ�������ļ�!");
				properties = new Properties();
				InputStream fis = getClass().getResourceAsStream(CONFIG_FILE);
				logger.info("���InputStream fis");
				if (fis == null) {
					logger.info("InputStream fisΪ��");
				}
				properties.load(fis);
				logger.info("����ɹ�");
			} catch (FileNotFoundException e) {
				logger.error("�����ļ�������", e);
			} catch (IOException e) {
				logger.error("IO�쳣", e);
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