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
	 * Get folder from Application Path. CreateFolder is by default: false
	 * 
	 * @param folderName
	 * @return
	 */
	public static File getMyAppFolder(String folderName) {
		return getMyAppFolder(folderName, false);
	}

	/**
	 * Get file from Application Path. Folder is by default: Empty. Create is by
	 * default: false
	 * 
	 * @param file
	 * @return
	 */
	public static File getMyAppFile(String file) {
		return getMyAppFile("", file, false);
	}

	/**
	 * Get file from Application Path. Create is by default: false
	 * 
	 * @param folder
	 * @param file
	 * @return
	 */
	public static File getMyAppFile(String folder, String file) {
		return getMyAppFile(folder, file, false);
	}

	/**
	 * Get file from Application Path
	 * 
	 * @param folderName
	 * @param fileName
	 * @param create
	 * @return
	 */
	public static File getMyAppFile(String folderName, String fileName, boolean create) {

		String path = getMyAppPath();

		if (!folderName.isEmpty()) {
			path = FilesFolders.concat(path, folderName);
			if (create)
				FilesFolders.createDirectory(path);
		}

		path = FilesFolders.concat(path, fileName);
		if (create)
			return FilesFolders.createFile(path);

		return new File(path);
	}
}