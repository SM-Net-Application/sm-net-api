package com.sm.net.file;

import org.apache.commons.io.FilenameUtils;

public class FileUtils {

	public static String getExtension(String filename) {
		return FilenameUtils.getExtension(filename);
	}

	public static String getExtensionWithDot(String filename) {
		return "." + getExtension(filename);
	}
}
