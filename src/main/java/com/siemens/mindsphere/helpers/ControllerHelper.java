package com.siemens.mindsphere.helpers;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.siemens.mindsphere.sdk.auth.TokenUtility;
import com.siemens.mindsphere.sdk.core.MindsphereCredentials;
import com.siemens.mindsphere.sdk.core.RestClientConfig;
import com.siemens.mindsphere.sdk.core.constants.Constants;
import com.siemens.mindsphere.sdk.core.exception.MindsphereException;
import com.siemens.mindsphere.services.MindsphereService;

public class ControllerHelper {
	private final static Logger LOGGER = LoggerFactory.getLogger(ControllerHelper.class);
	private RestClientConfig config;
	private MindsphereCredentials creds;

	public RestClientConfig getConfig(String host) {
		if (host.contains("localhost")) {
			LOGGER.info("Getting config from local");
           config = new RestClientConfig.Builder().proxyHost(Constants.PROXY_HOST)
                   .proxyPort(Integer.parseInt((Constants.PROXY_PORT))).build();
			//config = new RestClientConfig.Builder().build();

			return config;
		} else {
			LOGGER.info("Getting config from developer cockpit");
			config = new RestClientConfig.Builder().build();
			return config;
		}
	}

	public void setConfig(RestClientConfig config) {
		this.config = config;
	}

	public MindsphereCredentials getCreds(String token) {
		creds = null;
		if (MindsphereService.presentTokenType.equals(MindsphereService.TokenType.USER) && token != null) {
			LOGGER.info("Using User token to build creds");
			creds = MindsphereCredentials.builder().authorization(token).build();
		} else if (MindsphereService.presentTokenType.equals(MindsphereService.TokenType.APP)) {
			creds = MindsphereCredentials.appCredentialsBuilder().build();
		} else if (MindsphereService.presentTokenType.equals(MindsphereService.TokenType.TENANT)) {
			creds = MindsphereCredentials.tenantCredentialsBuilder().build();
		} else {
			LOGGER.error("Error while setting creds");
		}
		return creds;
	}

	public int getRandomNumber() {
		Random r = new Random();
		int low = 1;
		int high = 10000;
		return r.nextInt(high - low) + low;
	}

	public String getToken() {
		String token = null;
		try {
			token = TokenUtility.getAuthorizationToken(config, creds);
		} catch (MindsphereException e) {
			System.out.println(e.getErrorMessage());
		}
		return token;
	}

	public static void selectToken(MindsphereService service, String token, String host) {
		service.setHostName(host);
		if (MindsphereService.presentTokenType.equals(MindsphereService.TokenType.USER)) {
			if (token != null) {
				service.setToken(token);
			} else {
				LOGGER.info("Didn't receive user token from header. "
						+ "This might be because the app is hosted locally. Toggle token type to TENANT");
			}

		}
	}
}
