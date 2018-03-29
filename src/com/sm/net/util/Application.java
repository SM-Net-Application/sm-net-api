package com.sm.net.util;

/**
 * 
 * @author SM-Net <http://sm-netzwerk.com>
 * 
 *         Utility Class for Application
 */
public class Application {

	/**
	 * Returns the Application path
	 * 
	 * @return String of property user.dir
	 */
	public static String getApplicationPath() {
		return System.getProperty("user.dir");
	}
}