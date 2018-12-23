package com.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipException;

import javax.net.ssl.HttpsURLConnection;

public class CommonUtils {

	public static String fetchJsonData(String href) {
		URL url;
		HttpsURLConnection connection;
		String output = null;
		try {
			url = new URL(href);
			connection = (HttpsURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			if (connection.getResponseCode() == 200) {
				try (BufferedReader reader = new BufferedReader(
						new InputStreamReader(new GZIPInputStream(connection.getInputStream())))) {
					output = readAll(reader);
				}
			}

		} catch (ZipException e) {
			output = fetchJsonDataWithoutCompression(href);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return output;
	}

	public static String fetchJsonDataWithoutCompression(String href) {
		URL url;
		HttpsURLConnection connection;
		String line = "";
		try {
			url = new URL(href);
			connection = (HttpsURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			if (connection.getResponseCode() == 200) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				line = readAll(reader);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;
	}

	private static String readAll(BufferedReader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}
}