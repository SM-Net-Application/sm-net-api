package com.sm.net.project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Language {

	public static final String KEY_LANG = "language";

	private File fileProperties;
	private Properties properties;
	private String language;

	public Language(File fileProperties) throws FileNotFoundException, IOException {
		super();

		if (fileProperties != null) {

			this.fileProperties = fileProperties;

			this.properties = new Properties();
			this.properties.load(new FileInputStream(fileProperties));

			this.language = this.getString(KEY_LANG);
		}
	}

	public boolean isLoaded() {
		if (properties != null)
			return true;
		return false;
	}

	public String getString(String key) {
		if (this.isLoaded()) {
			String string = this.properties.getProperty(key);
			if (string != null)
				return string;
		}
		return "null";
	}

	public String getStringWithNewLine(String key) {
		if (this.isLoaded()) {
			String string = this.properties.getProperty(key);
			if (string != null)
				return string.replaceAll("\\\\n", "\n");
		}
		return "null";
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public String toString() {
		return this.language;
	}

	public File getFileProperties() {
		return fileProperties;
	}

	public void setFileProperties(File fileProperties) {
		this.fileProperties = fileProperties;
	}
}