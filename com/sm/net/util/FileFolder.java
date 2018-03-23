package com.sm.net.util;

import java.io.File;

/**
 * 
 * @author SM-Net <http://sm-netzwerk.com>
 * 
 *         Utility Class for Files and Folders
 *
 */
public class FileFolder {

	/**
	 * 
	 * Returns all files and folders in a directory
	 * 
	 * @param directory
	 * @return list files and folders or null
	 */
	public static File[] listAllFiles(File directory) {

		if (directory != null) {
			if (directory.exists()) {
				if (directory.isDirectory()) {
					return directory.listFiles();
				}
			}
		}
		return null;
	}
}
