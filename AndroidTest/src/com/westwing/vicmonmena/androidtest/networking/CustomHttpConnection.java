package com.westwing.vicmonmena.androidtest.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

/**
 * @author Vicente Montaño Mena
 * Customized http connections.
 */
public class CustomHttpConnection {

	/**
	 * TAG for log messages.
	 */
	private static final String TAG = CustomHttpConnection.class.getSimpleName();
	
	/**
	 * Code confirmation ok when launch a request. 
	 */
	private static final int CODE_OK = 200;
	
	/**
	 * Get products information in url
	 * @param url - contains products information
	 * @return products information in url.
	 */
	public static String getProducts(String url) {
		
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		try {
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == CODE_OK) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
			} else {
				Log.i(TAG, "No data obtained from " + url);
			}
		} catch (ClientProtocolException e) {
			Log.e(TAG, "A ClientProtocolException was caught getting response from " + url);
		} catch (IOException e) {
			Log.e(TAG, "An IOException was caught reading from " + url);
		}
		return builder.toString();
	}
	
	/**
	 * Download an image from URL.
	 * @param url - contains an image.
	 * @return image.
	 */
	public static InputStream getImage(String url) {
		
		InputStream is = null;
		try {
			HttpGet httpRequest = new HttpGet(url);
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse response = (HttpResponse) httpClient.execute(httpRequest);
			HttpEntity entity = response.getEntity();
			BufferedHttpEntity bufHttpEntity = new BufferedHttpEntity(entity); 
			is = bufHttpEntity.getContent();
		} catch (Exception e) {
			Log.e(TAG, "Error loading image from " + url);
		}
	    return is;
	}
}