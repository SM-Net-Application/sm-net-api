package com.sm.net.util;

import java.io.File;

/**
 * 
 * @author SM-Net <http://sm-netzwerk.com>
 * 
 *         Utility Class for Application
 */
public class MyApp {

	/**
	 * Returns the Application path
	 * 
	 * @return String of property user.dir
	 */
	public static String getMyAppPath() {
		return System.getProperty("user.dir");
	}

	/**
	 * Get folder from Application Path. CreateFolder is by default: false
	 * 
	 * @param folderName
	 * @return
	 */
	public static File getMyAppFolder(String folderName) {
		return getMyAppFolder(folderName, false);
	}

	/**
	 * Get folder from Application Path.
	 * 
	 * @param folderName
	 * @return
	 */
	public static File getMyAppFolder(String folderName, boolean createFolders) {
		String path = getMyAppPath();
		path += File.separatorChar;
		path += folderName;

		File file = new File(path);
		if (file != null)
			if (createFolders)
				file.mkdirs();

		return file;
	}

	/**
	 * Get file from the indicated path
	 * 
	 * @param initPath
	 * @param fileName
	 * @return
	 */
	public static File getMyAppFile(File initPath, String fileName) {

		String iniDirPath = initPath.getAbsolutePath();
		iniDirPath += File.separatorChar;
		iniDirPath += fileName;

		return new File(iniDirPath);
	}
}