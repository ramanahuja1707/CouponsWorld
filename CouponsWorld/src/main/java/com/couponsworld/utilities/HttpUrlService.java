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
		System.out.println(
				"----------------------HTTP URL CONNECTION OPENING- START------------------------------------");
		if (checkMandatoryParameters(httpURL, httpMethod, contentType, apiAuthorizationKey, doOutputFlag, objectJson)) {
			URL url = new URL(httpURL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(httpMethod);
			// connection.setRequestProperty("User-Agent", USER_AGENT);
			connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			if (!(contentType.equals(""))) {
				connection.setRequestProperty("content-type", contentType);
			}
			connection.setRequestProperty("Authorization", apiAuthorizationKey);
			connection.setDoOutput(doOutputFlag);

			if ((!objectJson.equals(""))) {
				OutputStream os = connection.getOutputStream();
				os.write(objectJson.getBytes());
				os.close();
			}
			System.out.println("<----------HTTP - URL - PARAMETERS------------->");
			if (!(contentType.equals(""))) {
				System.out.println("content-type : " + contentType);
			}
			System.out.println("Authorization : " + apiAuthorizationKey);
			System.out.println("Method : " + httpMethod);
			System.out.println("URL : " + httpURL);
			System.out.println("DoOutputFlag : " + doOutputFlag);
			System.out.println("objectJson : " + objectJson);
			System.out.println("<----------HTTP - URL - PARAMETERS------------->");

			System.out.println("Returning the HTTP URL CONNECTION Object Successfully....");
			System.out.println(
					"----------------------HTTP URL CONNECTION OPENING - END - SUCCESS------------------------------------");
			return connection;

		} else {
			System.out.println("Throwing Missing Mandatory Parameters Exception....");
			System.out.println(
					"----------------------HTTP URL CONNECTION OPENING - END -FAILURE ------------------------------------");
			throw new MissingMandatoryParametersException("MAndatory Parameters Missing");
		}

	}

	private static boolean checkMandatoryParameters(String httpURL, String httpMethod, String contentType,
			String apiAuthorizationKey, boolean doOutputFlag, String objectJson)
			throws MissingMandatoryParametersException {
		System.out.println("<--------------Checking Manadatory Parameters....START----------------->");
		if (httpURL == null || httpURL.equals("")) {
			System.out.println("Throwing BAck Missing Mandatory Parameters Exception..");
			System.out.println("<--------------Checking Manadatory Parameters....END - FAILURE----------------->");
			throw new MissingMandatoryParametersException("Missing Http URL");
		}
		if (httpMethod == null || httpMethod.equals("")) {
			System.out.println("Throwing BAck Missing Mandatory Parameters Exception..");
			System.out.println("<--------------Checking Manadatory Parameters....END - FAILURE----------------->");
			throw new MissingMandatoryParametersException("Missing Http Method");
		}
		if (apiAuthorizationKey == null || apiAuthorizationKey.equals("")) {
			System.out.println("Throwing BAck Missing Mandatory Parameters Exception..");
			System.out.println("<--------------Checking Manadatory Parameters....END - FAILURE----------------->");

			throw new MissingMandatoryParametersException("Missing Auhtorization Key");
		}
		System.out.println("<--------------Checking Manadatory Parameters....END - SUCCESS----------------->");
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
		return response;
	}

}
