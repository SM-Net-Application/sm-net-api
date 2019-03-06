package com.sm.net.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class JSON {

	private static final String USER_AGENT = "Firefox/63.0.1";

	public static String executeHttpPost(String url, JSONObject jsonObj) {

		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader(HTTP.USER_AGENT, USER_AGENT);

		StringEntity stringEntity = new StringEntity(jsonObj.toString(), ContentType.APPLICATION_JSON);
		httpPost.setEntity(stringEntity);

		CloseableHttpResponse response = null;
		try {

			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String entityText = EntityUtils.toString(entity);
				// System.out.println(entityText);
				return entityText;
			}

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

		return "";
	}

	public static JSONObject executeHttpPostJSON(String url, JSONObject jsonObj) {
		return httpPostResponseToJSON(executeHttpPost(url, jsonObj));
	}

	public static JSONObject httpPostResponseToJSON(String httpPostResponse) {
		try {
			return new JSONObject(httpPostResponse);
		} catch (Exception e) {
		}
		return null;
	}
}