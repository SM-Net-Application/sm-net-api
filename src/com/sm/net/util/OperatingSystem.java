package com.sm.net.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.sm.net.util.enumeration.OS;
import com.sm.net.util.enumeration.OSArch;

/**
 * 
 * @author SM-Net <http://sm-netzwerk.com>
 * 
 *         Utility Class for Operating System
 */
public class OperatingSystem {

	/**
	 * Returns the OS Object (Enumeration)
	 * 
	 * @return OS Object or null
	 */
	public static OS getOperatingSystem() {

		final String osName = System.getProperty("os.name").toLowerCase();

		if (osName.length() > 0) {
			if (osName.contains("windows")) {
				return OS.WINDOWS;
			}
		}

		return null;
	}

	/**
	 * Returns the OS Architecture Object (Enumeration)
	 * 
	 * @return OS Architecture or null
	 */
	public static OSArch getOSArchitecture() {

		final String osArch = System.getProperty("os.arch").toLowerCase();

		if (osArch.length() > 0) {
			if (osArch.contains("86")) {
				return OSArch.BIT32;
			} else {
				return OSArch.BIT64;
			}
		}

		return null;
	}

	/**
	 * Run command line and get output
	 *
	 *	For example:
	 *	- command: "ffmpeg.exe -i video.mp4"
	 *
	 *	String[] = { "ffmpeg.exe", "-i", "video.mp4" }
	 * 	
	 * @param commandLine
	 * @return
	 */
	public static String runCommand(String[] commandLine) {

		String output = "";

		try {

			ProcessBuilder processBuilder = new ProcessBuilder(commandLine);
			processBuilder.redirectErrorStream(true);

			final Process process = processBuilder.start();

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				output += line;
				output += "\n";
			}

			bufferedReader.close();

		} catch (IOException e) {
		}

		return output;
	}
}