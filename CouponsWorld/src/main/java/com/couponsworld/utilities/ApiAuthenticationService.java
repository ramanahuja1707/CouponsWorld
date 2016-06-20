package com.couponsworld.utilities;

import org.apache.commons.codec.binary.Base64;

public class ApiAuthenticationService {

	public String generateAuthorizationKey(String username, String password) {
		return Base64.encodeBase64String((username + ":" + password).getBytes());
	}
}
