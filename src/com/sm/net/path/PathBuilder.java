package com.sm.net.path;

import java.io.File;

public class PathBuilder {

	public static String concatFolder(String initPath, String folder) {
		return concat(initPath, folder);
	}

	public static String concatFile(String initPath, String file) {
		return concat(initPath, file);
	}

	public static String concatFile(String initPath, String file, String extensionWithDot) {
		return concat(initPath, file) + extensionWithDot;
	}

	private static String concat(String parent, String child) {
		return parent + File.separatorChar + child;
	}
}