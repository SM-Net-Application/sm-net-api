package com.sm.net.file;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;

public class FileUtils {

	public static String getExtension(String filename) {
		return FilenameUtils.getExtension(filename);
	}

	public static String getExtensionWithDot(String filename) {
		return "." + getExtension(filename);
	}

	/**
	 * Create the File and returns it as File-Object
	 * 
	 * @param absolutePath
	 * @return
	 */
	public static File createFile(String absolutePath, boolean mkdirs) {
		return createFile(new File(absolutePath), mkdirs);
	}

	/**
	 * Create the File and returns it as File-Object
	 * 
	 * @param file
	 * @return
	 */
	public static File createFile(File file, boolean mkdirs) {

		if (file != null)
			if (!file.exists())
				try {
					if (mkdirs)
						file.getParentFile().mkdirs();

					file.createNewFile();
				} catch (IOException e) {
					return null;
				}

		return file;
	}
}
