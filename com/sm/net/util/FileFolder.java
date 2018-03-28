package com.sm.net.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * 
 * @author SM-Net <http://sm-netzwerk.com>
 * 
 *         Utility Class for Files and Folders
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

	/**
	 * 
	 * Returns the instant (last modified time) of the file
	 * 
	 * @param file
	 * @return instant or null
	 * @throws IOException
	 */
	public static Instant getFileLastModifiedInstant(File file) {

		if (file != null) {
			if (file.exists()) {
				if (file.isFile()) {
					try {
						return Files.getLastModifiedTime(file.toPath()).toInstant();
					} catch (IOException e) {
					}
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * Returns the instant (last access time) of the file
	 * 
	 * @param file
	 * @return instant or null
	 * @throws IOException
	 */
	public static Instant getFileLastAccessInstant(File file) {

		if (file != null) {
			if (file.exists()) {
				if (file.isFile()) {
					BasicFileAttributes attributes = null;
					try {
						attributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
						return attributes.lastAccessTime().toInstant();
					} catch (IOException e) {
					}
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * Returns the string for the instant's output
	 * 
	 * @param instant
	 * @param format
	 *            For example: dd.MM.yyyy HH:mm:ss
	 * @return string or null
	 */
	public static String printInstant(Instant instant, String format) {

		if (instant != null) {
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format).withZone(ZoneId.systemDefault());

			return dateTimeFormatter.format(instant);
		}
		return null;
	}
}
