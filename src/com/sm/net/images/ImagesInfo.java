package com.sm.net.images;

import java.io.File;
import java.io.IOException;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDirectory;

/**
 * 
 * @author SM-Net <http://sm-netzwerk.com>
 * 
 *         Utility Class for Image Files
 */
public class ImagesInfo {

	/**
	 * Returns the object Metadata of a image file
	 * 
	 * @param file
	 * @return Metadata object or null
	 */
	public static Metadata getImageMetadata(File file) {

		try {
			return ImageMetadataReader.readMetadata(file);
		} catch (ImageProcessingException | IOException e) {
		}

		return null;
	}

	/**
	 * Returns the Camera Model metadata
	 * 
	 * @param metadata
	 * @return
	 */
	public static String getCameraModel(Metadata metadata) {

		String cameraModel = "";

		ExifIFD0Directory exifIFD0Directory = metadata
				.getFirstDirectoryOfType(com.drew.metadata.exif.ExifIFD0Directory.class);

		if (exifIFD0Directory != null) {

			cameraModel = exifIFD0Directory.getDescription(272);
			if (cameraModel.length() > 0) {
				return cameraModel.trim();
			}
		}
		return cameraModel;
	}

	/**
	 * Returns the DateTimeOriginal
	 * 
	 * @param metadata
	 * @return
	 */
	public static String getDateTimeOriginal(Metadata metadata) {

		String dateTimeOriginal = "";

		ExifSubIFDDirectory exifSubIFDDirectory = metadata
				.getFirstDirectoryOfType(com.drew.metadata.exif.ExifSubIFDDirectory.class);

		if (exifSubIFDDirectory != null) {
			dateTimeOriginal = exifSubIFDDirectory.getDescription(36867);
			if (dateTimeOriginal.length() > 0) {
				return dateTimeOriginal.trim();
			}
		}

		return dateTimeOriginal;
	}
}