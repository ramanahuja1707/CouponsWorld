package com.couponsworld.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.logging.Logger;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;

import com.couponsworld.exceptions.MissingMandatoryParametersException;

public class HttpUrlService {
	// declaration of loggern
	private static final Logger log = Logger.getLogger(HttpUrlService.class.getName());

	public static HttpURLConnection getHttpURLConnection(String httpURL, String httpMethod, String contentType,
			String apiAuthorizationKey, boolean doOutputFlag, String objectJson, String username, String password,
			String accessId, String accessPlatform) throws IOException, MissingMandatoryParametersException {
		log.info("----------------------HTTP URL CONNECTION OPENING- START------------------------------------");
		if (checkMandatoryParameters(httpURL, httpMethod, contentType, apiAuthorizationKey, doOutputFlag, objectJson,
				username, password, accessId, accessPlatform)) {
			URL url = new URL(httpURL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(httpMethod);
			// connection.setRequestProperty("User-Agent", USER_AGENT);
			connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			connection.setRequestProperty("username", username);
			connection.setRequestProperty("password", password);
			connection.setRequestProperty("accessId", accessId);
			connection.setRequestProperty("accessPlatform", accessPlatform);

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
			String apiAuthorizationKey, boolean doOutputFlag, String objectJson, String username, String password,
			String accessId, String accessPlatform) throws MissingMandatoryParametersException {
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
		if (username == null || username.equals("")) {
			log.info("Throwing BAck Missing Mandatory Parameters Exception..");
			log.info("<--------------Checking Manadatory Parameters....END - FAILURE----------------->");

			throw new MissingMandatoryParametersException("Missing username..");
		}
		if (password == null || password.equals("")) {
			log.info("Throwing BAck Missing Mandatory Parameters Exception..");
			log.info("<--------------Checking Manadatory Parameters....END - FAILURE----------------->");

			throw new MissingMandatoryParametersException("Missing password..");
		}
		if (accessId == null || accessId.equals("")) {
			log.info("Throwing BAck Missing Mandatory Parameters Exception..");
			log.info("<--------------Checking Manadatory Parameters....END - FAILURE----------------->");

			throw new MissingMandatoryParametersException("Missing access Id..");
		}
		if (accessPlatform == null || accessPlatform.equals("")) {
			log.info("Throwing BAck Missing Mandatory Parameters Exception..");
			log.info("<--------------Checking Manadatory Parameters....END - FAILURE----------------->");

			throw new MissingMandatoryParametersException("Missing access platform..");
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

	public static String readHttpUrlResponseWeb(String url) throws MalformedURLException, Exception {
		log.info("----------------------READING URL - START------------------------------------");
		HttpClient client = new DefaultHttpClient();

		HttpGet request = new HttpGet(url);
		log.info("----------------------Adding parameters to Request------------------------------------");
		request.addHeader("username", "innovate.garr");
		request.addHeader("password", "garr.innovate");
		request.addHeader("accessId", "ADMIN");
		request.addHeader("accessPlatform", "Android");
		request.addHeader("Authorization", "Basic aW5ub3ZhdGUuZ2FycjpnYXJyLmlubm92YXRl");
		log.info("----------------------Parameters Added to Request------------------------------------");
		HttpResponse response = client.execute(request); // Get the response
		log.info("----------------------Response got Successfully by URL------------------------------------");
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		log.info("----------------------Reading the URL Response------------------------------------");
		String line = "";
		String text = "";
		while ((line = rd.readLine()) != null) {
			text += line + "";
		}
		log.info("----------------------Response read by URL Successfully------------------------------------");
		log.info("----------------------READING URL - END------------------------------------");
		return text;

	}

}
