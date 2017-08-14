package com.msunsoft.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

/**
 * 读取配置文件
 */
public class ConfigRead {

	static String configfile = "config/spring/config.properties";
	static Properties properties = new Properties();

	static {
		getProperties(configfile);
	}

	public static String getValue(String configfile, String key) {
		String value = null;
		if (!StringUtils.isBlank(configfile)) {
			InputStream inputStream = ConfigRead.class.getClassLoader().getResourceAsStream(configfile);
			Properties p = new Properties();
			try {
				p.load(inputStream);
				value = p.getProperty(key, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return value;
	}

	public static String getValue(String key) {
		return properties.getProperty(key);
	}

	public static Properties getProperties(String configfile) {
		if (!StringUtils.isEmpty(configfile)) {
			InputStream inputStream = ConfigRead.class.getClassLoader().getResourceAsStream(configfile);
			properties = new Properties();
			try {
				properties.load(inputStream);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return properties;
	}

}
