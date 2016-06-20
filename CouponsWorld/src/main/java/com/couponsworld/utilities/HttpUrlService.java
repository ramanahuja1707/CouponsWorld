package com.couponsworld.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;

import com.couponsworld.exceptions.MissingMandatoryParametersException;

import sun.misc.BASE64Encoder;

public class HttpUrlService {

	public static HttpURLConnection getHttpURLConnection(String httpURL, String httpMethod, String contentType,
			String apiAuthorizationKey, boolean doOutputFlag, String objectJson)
			throws IOException, MissingMandatoryParametersException {
		if (checkMandatoryParameters(httpURL, httpMethod, contentType, apiAuthorizationKey, doOutputFlag, objectJson)) {
			URL url = new URL(httpURL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(httpMethod);
			// connection.setRequestProperty("User-Agent", USER_AGENT);
			connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			connection.setRequestProperty("content-type", contentType);
			connection.setRequestProperty("Authorization", apiAuthorizationKey);
			connection.setDoOutput(doOutputFlag);

			if ((!objectJson.equals("")) || objectJson != null) {
				OutputStream os = connection.getOutputStream();
				os.write(objectJson.getBytes());
				os.close();
			}

			System.out.println("content-type : " + contentType);
			System.out.println("Authorization : " + apiAuthorizationKey);
			System.out.println("Method : " + httpMethod);
			System.out.println("URL : " + httpURL);
			System.out.println("DoOutputFlag : " + doOutputFlag);
			System.out.println("objectJson : " + objectJson);

			return connection;

		} else {
			throw new MissingMandatoryParametersException("MAndatory Parameters Missing");
		}

	}

	private static boolean checkMandatoryParameters(String httpURL, String httpMethod, String contentType,
			String apiAuthorizationKey, boolean doOutputFlag, String objectJson)
			throws MissingMandatoryParametersException {
		if (httpURL == null || httpURL.equals("")) {
			throw new MissingMandatoryParametersException("Missing Http URL");
		}
		if (httpMethod == null || httpMethod.equals("")) {
			throw new MissingMandatoryParametersException("Missing Http Method");
		}
		if (contentType == null || contentType.equals("")) {
			throw new MissingMandatoryParametersException("Missing Request Content Type");
		}
		if (apiAuthorizationKey == null || apiAuthorizationKey.equals("")) {
			throw new MissingMandatoryParametersException("Missing Auhtorization Key");
		}
		return true;
	}

	public static String readHttpUrlResponse(InputStream responseInputStream) throws IOException {
		String response = new String();
		String responseString = "";
		InputStreamReader insr = new InputStreamReader(responseInputStream);
		BufferedReader br = new BufferedReader(insr);
		while ((responseString = br.readLine()) != null) {
			response += responseString;
		}
		System.out.println(response);
		return response;
	}

}
