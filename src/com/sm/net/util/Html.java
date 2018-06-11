package com.sm.net.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 
 * @author SM-Net <http://sm-netzwerk.com>
 * @version 1.1.0
 * 
 *          Utility Class for HTML code
 */
public class Html {

	/*
	 * Metadata
	 */
	public static final String backslash = "\"";
	public static final String tagSpanStart = "<span>";
	public static final String tagSpanEnd = "</span>";
	public static final String tagDivStart = "<div>";
	public static final String tagDivEnd = "</div>";
	public static final String attrSrcStart = "src=\"";

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
}
