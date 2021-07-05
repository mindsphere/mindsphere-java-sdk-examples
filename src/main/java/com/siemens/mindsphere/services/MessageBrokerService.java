package com.siemens.mindsphere.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.RequestEntity.BodyBuilder;
import org.springframework.http.RequestEntity.HeadersBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.siemens.mindsphere.model.MsgBrokerSubscriptionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.siemens.mindsphere.model.MsgBrokerSubscriptionRequest;
import com.siemens.mindsphere.sdk.auth.AccessTokenResponse;
import com.siemens.mindsphere.sdk.auth.TokenUtility;
import com.siemens.mindsphere.sdk.core.CommonUtil;
import com.siemens.mindsphere.sdk.core.ErrorObject;
import com.siemens.mindsphere.sdk.core.Identification;
import com.siemens.mindsphere.sdk.core.Identification.GrantTypeEnum;
import com.siemens.mindsphere.sdk.core.constants.Constants;
import com.siemens.mindsphere.sdk.core.constants.TokenManagerConstants;
import com.siemens.mindsphere.sdk.core.exception.MindsphereException;
import com.siemens.mindsphere.sdk.core.exception.MindsphereForbiddenAccessException;
import com.siemens.mindsphere.sdk.core.exception.MindsphereServiceException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MessageBrokerService {

	String basePath = "api/messagebroker/v4/";
	protected RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());

	public String subscribetoMsgBroker(MsgBrokerSubscriptionRequest postbody, String bakcnedappName, String version,
			String topicName, String token) throws MindsphereException {

		final Map<String, Object> uriVariables = new HashMap<>();
		uriVariables.put("bakcnedappName", bakcnedappName);
		uriVariables.put("version", version);
		uriVariables.put("topicName", topicName);
		String path = UriComponentsBuilder
				.fromPath(basePath + "/subscribers/{bakcnedappName}/versions/{version}/topics/{topicName}")
				.buildAndExpand(uriVariables).toUriString();

		final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(getEnvironmentSpecificGateWayUrl())
				.path(path);

		// UriComponentsBuilder builder =
		// UriComponentsBuilder.fromUriString(path.toString());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		URI uri = builder.build().encode().toUri();
		String encodedString = CommonUtil.encodeFilter(uri.toString());
		uri = URI.create(encodedString);
		log.info("Entering ApiInvoker.invokeAPI() for the api : {} ", uri);
		if (token == null) {

			token = getTokenwithappcreds();
		}
		headers.set("Authorization", "bearer " + token);
		final BodyBuilder requestBuilder = RequestEntity.method(HttpMethod.PUT, uri);
		requestBuilder.headers(headers);
		RequestEntity<Object> requestEntity = requestBuilder.body(postbody);
		ResponseEntity<MsgBrokerSubscriptionResponse> result = null;

		try {
			result = restTemplate.exchange(builder.build().toUri(), HttpMethod.PUT, requestEntity,
					MsgBrokerSubscriptionResponse.class);
		} catch (Exception e) {
			throw new MindsphereForbiddenAccessException(e.getMessage(), HttpStatus.UNAUTHORIZED, e);
		}
		MsgBrokerSubscriptionResponse response = result.getBody();
		System.out.println(response.getURL());
		return response.getURL();

	}

	public void unsubscribetoMsgBroker(String bakcnedappName, String version, String topicName, String token)
			throws MindsphereException {

		final Map<String, Object> uriVariables = new HashMap<>();
		uriVariables.put("bakcnedappName", bakcnedappName);
		uriVariables.put("version", version);
		uriVariables.put("topicName", topicName);
		String path = UriComponentsBuilder
				.fromPath(basePath + "/subscribers/{bakcnedappName}/versions/{version}/topics/{topicName}")
				.buildAndExpand(uriVariables).toUriString();

		final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(getEnvironmentSpecificGateWayUrl())
				.path(path);

		// UriComponentsBuilder builder =
		// UriComponentsBuilder.fromUriString(path.toString());

		HttpHeaders headers = new HttpHeaders();
		// headers.setContentType(MediaType.APPLICATION_JSON);
		URI uri = builder.build().encode().toUri();
		String encodedString = CommonUtil.encodeFilter(uri.toString());
		uri = URI.create(encodedString);
		log.info("Entering ApiInvoker.invokeAPI() for the api : {} ", uri);
		if (token == null) {

			token = getTokenwithappcreds();
		}
		headers.set("Authorization", "bearer " + token);
		final HeadersBuilder<?> requestBuilder = RequestEntity.delete(uri);
		requestBuilder.headers(headers);
		RequestEntity<Void> requestEntity = requestBuilder.build();

		try {
			restTemplate.exchange(builder.build().toUri(), HttpMethod.DELETE, requestEntity, Void.class);
		} catch (Exception e) {
			throw new MindsphereForbiddenAccessException(e.getMessage(), HttpStatus.UNAUTHORIZED, e);
		}

	}

	public static HttpComponentsClientHttpRequestFactory getClientHttpRequestFactory() {
		HttpComponentsClientHttpRequestFactory clientHttpReq = null;
		/* Connection timeout settings */
		RequestConfig config = null;

		/* Proxy setting for Http client */
		String httpProxySchema = Constants.DEFAULT_HTTP_SCHEMA;

		PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
		poolingHttpClientConnectionManager.setDefaultMaxPerRoute(Constants.DEFAULT_MAX_CONNECTION_PER_ROUTE);
		poolingHttpClientConnectionManager.setMaxTotal(Constants.MAX_POOL_CONNECTION);

		/* Httpclient builder using http client connection pooling */
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create().disableCookieManagement()
				.setConnectionManager(poolingHttpClientConnectionManager).useSystemProperties()
				.setDefaultRequestConfig(config);

		// httpClientBuilder.setProxy(new HttpHost("194.138.0.25", 9400,
		// httpProxySchema));

		CloseableHttpClient httpClient = httpClientBuilder.build();
		clientHttpReq = new HttpComponentsClientHttpRequestFactory(httpClient);
		return clientHttpReq;
	}

	public String getTokenwithappcreds() throws MindsphereException {

		AccessTokenResponse response = null;
		HttpHeaders defaultHeaders = new HttpHeaders();
		Identification identification = new Identification();
		identification.setAppName(System.getenv("MDSP_OS_VM_APP_NAME"));
		identification.setAppVersion(System.getenv("MDSP_OS_VM_APP_VERSION"));
		identification.setGrantType(GrantTypeEnum.CREDENTIALS);
		identification.setHostTenant(System.getenv("MDSP_HOST_TENANT"));
		identification.setUserTenant(System.getenv("MDSP_USER_TENANT"));
		String env = System.getenv("HOST_ENVIRONMENT");
		Object body = identification;

		String path = UriComponentsBuilder.fromPath(TokenManagerConstants.KEYMANAGER_URL).buildAndExpand()
				.toUriString();

		final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(getEnvironmentSpecificGateWayUrl())
				.path(path).queryParam("grant_type", "client_credentials");
		URI uri = builder.build().encode().toUri();
		String encodedString = CommonUtil.encodeFilter(uri.toString());
		uri = URI.create(encodedString);
		log.info("Entering ApiInvoker.invokeAPI() for the api : {} ", uri);

		final BodyBuilder requestBuilder = RequestEntity.method(HttpMethod.POST, uri);
		final List<MediaType> accept = Arrays.asList(MediaType.APPLICATION_JSON);
		final MediaType contentType = MediaType.APPLICATION_JSON;

		if (accept != null) {
			requestBuilder.accept(accept.toArray(new MediaType[accept.size()]));
		}
		if (contentType != null) {
			requestBuilder.contentType(contentType);
		}

		final HttpHeaders headerParams = new HttpHeaders();
		headerParams.add("X-SPACE-AUTH-KEY", "Basic " + java.util.Base64.getEncoder().encodeToString(
				(System.getenv("MDSP_KEY_STORE_CLIENT_ID") + ":" + System.getenv("MDSP_KEY_STORE_CLIENT_SECRET"))
						.getBytes(StandardCharsets.UTF_8)));

		addHeadersToRequest(headerParams, requestBuilder);
		addHeadersToRequest(defaultHeaders, requestBuilder);

		RequestEntity<Object> requestEntity = requestBuilder.body(selectBody(body, null, contentType));

		ResponseEntity<AccessTokenResponse> responseEntity = null;

		try {
			responseEntity = restTemplate.exchange(requestEntity, AccessTokenResponse.class);
			response = responseEntity.getBody();
		} catch (HttpClientErrorException | HttpServerErrorException ex) {
			ErrorObject error = CommonUtil.parseException(ex.getResponseBodyAsString());
			throw new MindsphereServiceException(error.getErrorMessage(), error.getCode(), ex.getStatusCode(),
					error.getLogref(), ex);
		} catch (Exception e) {
			throw new MindsphereServiceException("API failed ", HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
		return response.getAccessToken();

	}

	protected void addHeadersToRequest(HttpHeaders headers, BodyBuilder requestBuilder) {
		for (Entry<String, List<String>> entry : headers.entrySet()) {
			List<String> values = entry.getValue();
			for (String value : values) {
				if (value != null) {
					requestBuilder.header(entry.getKey(), value);
				}
			}
		}
	}

	protected Object selectBody(Object obj, MultiValueMap<String, Object> formParams, MediaType contentType) {
		boolean isForm = MediaType.MULTIPART_FORM_DATA.isCompatibleWith(contentType)
				|| MediaType.APPLICATION_FORM_URLENCODED.isCompatibleWith(contentType);
		return isForm ? formParams : obj;
	}

	public static String getEnvironmentSpecificGateWayUrl() {
		String hostEnvironment = Constants.DEFAULT_HOST_ENVIRONMENT;
		String hostDomain = Constants.DEFAULT_HOST_BASEDOMAIN;
		if (TokenUtility.getEnvironmentVarValue(Constants.HOST_ENVIRONMENT) != null) {
			hostEnvironment = TokenUtility.getEnvironmentVarValue(Constants.HOST_ENVIRONMENT);
		}
		if (TokenUtility.getEnvironmentVarValue(Constants.HOST_BASEDOMAIN) != null) {
			hostDomain = TokenUtility.getEnvironmentVarValue(Constants.HOST_BASEDOMAIN);
		}
		return MessageFormat.format(Constants.GATEWAY_URL_FORMAT, hostEnvironment, hostDomain);
	}

	public String storeNotificationDatatofile(String body) throws IOException {

		try {
			File file = new File("src/main/resources/msgBrokerResponse.json");

			if (!file.exists())
				file.createNewFile();
			System.out.println(file.length());
			if (file.length() != 0)
				body = "," + body;
			FileWriter myWriter = new FileWriter("src/main/resources/msgBrokerResponse.json", true);
			myWriter.write(body);
			myWriter.close();
			log.info("Successfully wrote to the file.");
			return "Successfully wrote to the file.";
		} catch (IOException e) {
			log.info("An error occurred while writing file. " + e.getMessage());
			return e.getMessage();
		}
	}

	public String readNotificationData() throws IOException {

		String response = "";
		BufferedReader in = new BufferedReader(new FileReader("src/main/resources/msgBrokerResponse.json"));

		String line;
		while ((line = in.readLine()) != null) {
			response = response + line;
		}
		in.close();

		return response;
	}

}
