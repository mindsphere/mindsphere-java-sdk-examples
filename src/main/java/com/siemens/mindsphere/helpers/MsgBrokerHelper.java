package com.siemens.mindsphere.helpers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import com.siemens.mindsphere.model.MsgBrokerSubscriptionRequest;
import com.siemens.mindsphere.model.MsgBrokerSubscriptionResponse;
import com.siemens.mindsphere.sdk.core.ApiInvoker;
import com.siemens.mindsphere.sdk.core.InvokerConfig;
import com.siemens.mindsphere.sdk.core.MindsphereAPIClient;
import com.siemens.mindsphere.sdk.core.exception.MindsphereException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MsgBrokerHelper extends MindsphereAPIClient {
	String basePath = "api/messagebroker/v4/";

	public static class ClientBuilder extends MindsphereAPIClient.Builder<ClientBuilder> {

		@Override
		public MsgBrokerHelper build() {
			return new MsgBrokerHelper(this);
		}
	}

	public MsgBrokerHelper(ClientBuilder builder) {
		super(builder);
	}

	public static ClientBuilder builder() {
		return new MsgBrokerHelper.ClientBuilder();
	}

	private ApiInvoker apiClient = new ApiInvoker();

	public MsgBrokerSubscriptionResponse createsubscription(MsgBrokerSubscriptionRequest postbody, String bakcnedappName, String version,
			String topicName, String token) throws MindsphereException {

		basePath = "/api/messagebroker/v4";

		log.info("MsgBrokerHelper.createsubscription() invoked.");
		 Object postBody = postbody;
		// create path and map variables
		final Map<String, Object> uriVariables = new HashMap<>();
		uriVariables.put("bakcnedappName", bakcnedappName);
		uriVariables.put("version",version);
		uriVariables.put("topicName", topicName);
		String path = UriComponentsBuilder
				.fromPath(basePath + "/subscribers/{bakcnedappName}/versions/{version}/topics/{topicName}")
				.buildAndExpand(uriVariables).toUriString();

		final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
		final HttpHeaders headerParams = new HttpHeaders();
		final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<>();

		final String[] accepts = {};
		final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
		final String[] contentTypes = { "application/json" };
		final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

		InvokerConfig config = populateInvokerConfig();

		ParameterizedTypeReference<MsgBrokerSubscriptionResponse> returnType = new ParameterizedTypeReference<MsgBrokerSubscriptionResponse>() {
		};
		log.info("MsgBrokerHelper.createsubscription() --> Proceeding for API Invoker");
		return apiClient.invokeAPI(config, path, HttpMethod.PUT, queryParams, postBody, headerParams, formParams, accept,
				contentType, returnType);
	}
	
	
	public void unsubscribeMsgBroker( String bakcnedappName, String version,
			String topicName, String token) throws MindsphereException {

		basePath = "/api/messagebroker/v4";

		log.info("MsgBrokerHelper.unsubscribeMsgBroker() invoked.");
		 Object postBody = null;
		// create path and map variables
		final Map<String, Object> uriVariables = new HashMap<>();
		uriVariables.put("bakcnedappName", bakcnedappName);
		uriVariables.put("version",version);
		uriVariables.put("topicName", topicName);
		String path = UriComponentsBuilder
				.fromPath(basePath + "/subscribers/{bakcnedappName}/versions/{version}/topics/{topicName}")
				.buildAndExpand(uriVariables).toUriString();

		final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
		final HttpHeaders headerParams = new HttpHeaders();
		final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<>();

		final String[] accepts = {};
		final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
		final String[] contentTypes = { "application/json" };
		final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

		InvokerConfig config = populateInvokerConfig();

		ParameterizedTypeReference<Void> returnType = new ParameterizedTypeReference<Void>() {
		};
		log.info("MsgBrokerHelper.unsubscribeMsgBroker() --> Proceeding for API Invoker");
		apiClient.invokeAPI(config, path, HttpMethod.DELETE, queryParams, postBody, headerParams, formParams, accept,
				contentType, returnType);
	}


	private InvokerConfig populateInvokerConfig() {
		return new InvokerConfig(this.restClientConfig, this.credentials, this.restTemplate);
	}

}
