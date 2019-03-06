package com.sm.net.util;

import java.io.IOException;

import org.apache.commons.validator.routines.UrlValidator;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 
 * @author SM-Net <https://sm-netzwerk.com>
 * @version 1.1.0
 * 
 *          Utility Class for HTML code
 */
public class EasyHtml {

	/*
	 * Metadata
	 */
	public static final String backslash = "\"";

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
		if (start != -1) {

			start += startCode.length();
			int end = sourceCode.indexOf(endCode, start);
			if (end != -1) {

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

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = null;

		try {

			response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if (entity != null)
				sourceCode = EntityUtils.toString(entity);

		} catch (ClientProtocolException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (httpClient != null)
					httpClient.close();
				if (response != null)
					response.close();
			} catch (IOException e) {
			}
		}

		return sourceCode;
	}

	/**
	 * Remove Tag from code isTagOpened = false by default
	 * 
	 * @param code
	 * @return
	 */
	public static String removeTag(String code) {
		return removeTag(code, false);
	}

	/**
	 * Remove Tag from code
	 * 
	 * @param code
	 * @return
	 */
	public static String removeTag(String code, boolean isTagOpened) {

		String okChars = "";

		if (!code.isEmpty()) {

			char[] chars = code.toCharArray();

			boolean tag = isTagOpened;
			for (int i = 0; i < chars.length; i++) {
				if (chars[i] == '<')
					tag = true;
				if (!tag)
					okChars = okChars.isEmpty() ? String.valueOf(chars[i]) : okChars + chars[i];
				if (chars[i] == '>')
					tag = false;
			}
		}

		return okChars;
	}

	/**
	 * Convert HTML nbsp; to Space
	 * 
	 * @param string
	 * @return
	 */
	public static String replaceNoBreakSpace(String string) {
		return string.replaceAll("\\" + EasyHtml.Unicode.NOBREAKSPACE, " ");
	}

	/**
	 * Check if the URL is valid
	 * 
	 * @param url
	 * @return
	 */
	public static boolean isValidUrl(String url) {
		return UrlValidator.getInstance().isValid(url);
	}

	/**
	 * HTML Tags
	 */
	public static class Tags {
		public static final String spanOpen = "<span>";
		public static final String spanClose = "</span>";
		public static final String divOpen = "<div>";
		public static final String divClose = "</div>";
	}

	/**
	 * HTML Attributes
	 */
	public static class Attributes {
		public static final String src = "src=\"";
	}

	/**
	 * HTML Unicode Characters
	 */
	public static class Unicode {
		public static final String NOBREAKSPACE = "u00A0";
	}
}
