package com.couponsworld.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import com.couponsworld.exceptions.MissingMandatoryParametersException;

public class HttpUrlService {
	// declaration of loggern
	private static final Logger log = Logger.getLogger(HttpUrlService.class.getName());

	public static HttpURLConnection getHttpURLConnection(String httpURL, String httpMethod, String contentType,
			String apiAuthorizationKey, boolean doOutputFlag, String objectJson)
			throws IOException, MissingMandatoryParametersException {
		log.info("----------------------HTTP URL CONNECTION OPENING- START------------------------------------");
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
			log.info("<----------HTTP - URL - PARAMETERS------------->");
			if (!(contentType.equals(""))) {
				log.info("content-type : " + contentType);
			}
			log.info("Authorization : " + apiAuthorizationKey);
			log.info("Method : " + httpMethod);
			log.info("URL : " + httpURL);
			log.info("DoOutputFlag : " + doOutputFlag);
			log.info("objectJson : " + objectJson);
			log.info("<----------HTTP - URL - PARAMETERS------------->");

			log.info("Returning the HTTP URL CONNECTION Object Successfully....");
			log.info(
					"----------------------HTTP URL CONNECTION OPENING - END - SUCCESS------------------------------------");
			return connection;

		} else {
			log.info("Throwing Missing Mandatory Parameters Exception....");
			log.info(
					"----------------------HTTP URL CONNECTION OPENING - END -FAILURE ------------------------------------");
			throw new MissingMandatoryParametersException("MAndatory Parameters Missing");
		}

	}

	private static boolean checkMandatoryParameters(String httpURL, String httpMethod, String contentType,
			String apiAuthorizationKey, boolean doOutputFlag, String objectJson)
			throws MissingMandatoryParametersException {
		log.info("<--------------Checking Manadatory Parameters....START----------------->");
		if (httpURL == null || httpURL.equals("")) {
			log.info("Throwing BAck Missing Mandatory Parameters Exception..");
			log.info("<--------------Checking Manadatory Parameters....END - FAILURE----------------->");
			throw new MissingMandatoryParametersException("Missing Http URL");
		}
		if (httpMethod == null || httpMethod.equals("")) {
			log.info("Throwing BAck Missing Mandatory Parameters Exception..");
			log.info("<--------------Checking Manadatory Parameters....END - FAILURE----------------->");
			throw new MissingMandatoryParametersException("Missing Http Method");
		}
		if (apiAuthorizationKey == null || apiAuthorizationKey.equals("")) {
			log.info("Throwing BAck Missing Mandatory Parameters Exception..");
			log.info("<--------------Checking Manadatory Parameters....END - FAILURE----------------->");

			throw new MissingMandatoryParametersException("Missing Auhtorization Key");
		}
		log.info("<--------------Checking Manadatory Parameters....END - SUCCESS----------------->");
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
