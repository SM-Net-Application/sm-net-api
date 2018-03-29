package com.sm.net.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * 
 * @author SM-Net <http://sm-netzwerk.com>
 * 
 *         Utility Class for HTML code
 */
public class Html {

	/**
	 * 
	 * HTML encoding of foreign language characters
	 * 
	 * @param htmlCode
	 * @return recoded or the same HTML code
	 */
	public static String encodingForeignChars(String htmlCode) {

		String recodedHtmlCode = htmlCode;

		recodedHtmlCode = recodedHtmlCode.replace("&auml;", "ä");
		recodedHtmlCode = recodedHtmlCode.replace("&Auml;", "Ä");
		recodedHtmlCode = recodedHtmlCode.replace("&ouml;", "ö");
		recodedHtmlCode = recodedHtmlCode.replace("&Ouml;", "Ö");
		recodedHtmlCode = recodedHtmlCode.replace("&uuml;", "ü");
		recodedHtmlCode = recodedHtmlCode.replace("&Uuml;", "Ü");
		recodedHtmlCode = recodedHtmlCode.replace("&szlig;", "ß");
		recodedHtmlCode = recodedHtmlCode.replace("&amp;", "&");
		recodedHtmlCode = recodedHtmlCode.replace("&nbsp;", " ");
		recodedHtmlCode = recodedHtmlCode.replace("&quot;", "\"");

		return recodedHtmlCode;
	}

	/**
	 * 
	 * Returns the text between 2 pieces of code.
	 * 
	 * @param sourceCode
	 * @param startCode
	 * @param endCode
	 * @return Subsource code or empty
	 */
	public static String getSubsourceCode(String sourceCode, String startCode, String endCode) {

		String part = "";

		int start = sourceCode.indexOf(startCode);
		if (start > -1) {

			start = start + startCode.length();
			int end = sourceCode.indexOf(endCode, start);
			if (end > -1) {

				part = sourceCode.substring(start, end);
				part = part.replace("\n", "");
				part = part.trim();
			}
		}

		return part;
	}
	
	/**
	 * 
	 * Returns source code for a web page
	 * 
	 * @param url
	 * @return source code or empty
	 */
	public static String getSourceCode(String url) {

		String sourceCode = "";

		InputStream openStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		try {
			URL urlResource = new URL(url);
			openStream = urlResource.openStream();
			inputStreamReader = new InputStreamReader(openStream);
			bufferedReader = new BufferedReader(inputStreamReader);

			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				sourceCode += line + "\n";
			}

		} catch (Exception e) {
			sourceCode = "";
		} finally {
			try {
				if (openStream != null)
					openStream.close();
				if (inputStreamReader != null)
					inputStreamReader.close();
				if (bufferedReader != null)
					bufferedReader.close();
			} catch (Exception e) {
			}
		}
		return sourceCode;
	}
}
