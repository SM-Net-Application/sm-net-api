package com.sm.net.directory;

import java.io.File;

public class Directory {

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
