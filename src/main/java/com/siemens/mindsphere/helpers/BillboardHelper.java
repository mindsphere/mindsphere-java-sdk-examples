package com.siemens.mindsphere.helpers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.siemens.mindsphere.sdk.core.constants.AuthorizationUtilConstants;

public class BillboardHelper extends ControllerHelper {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	public List<String> getRolesFromJwt(String token) {
		token = checkForBearer(token);
		DecodedJWT jwt = null;
		try {
			jwt = JWT.decode(token);
		} catch (JWTDecodeException exception) {
			LOGGER.info("INVALID JWT TOKEN");
		}

		jwt.getClaim(token);
		String clientAppName = jwt.getClaim("client_id").asString();
		LOGGER.info(clientAppName);
		List<String> scopes = jwt.getClaims().get("scope").asList(String.class);

		return scopes;
	}

	private static String checkForBearer(String token) {
		if (token != null && token.length() > 7
				&& token.substring(0, 6).equalsIgnoreCase(AuthorizationUtilConstants.BEARER_TOKEN_PREFIX)) {
			token = token.substring(7, token.lastIndexOf('.') + 1);
		}
		return token;
	}
}
