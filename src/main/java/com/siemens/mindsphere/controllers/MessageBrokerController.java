package com.siemens.mindsphere.controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.sql.Array;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.RequestEntity.BodyBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.siemens.mindsphere.model.MsgBrokerSubscriptionResponse;
import com.siemens.mindsphere.model.MsgBrokerSubscriptionRequest;
import com.siemens.mindsphere.sdk.assetmanagement.model.AspectType;
import com.siemens.mindsphere.sdk.auth.AccessTokenResponse;
import com.siemens.mindsphere.sdk.auth.AuthorizationUtil;
import com.siemens.mindsphere.sdk.auth.TechnicalToken;
import com.siemens.mindsphere.sdk.auth.TokenScope;
import com.siemens.mindsphere.sdk.auth.TokenUtility;
import com.siemens.mindsphere.sdk.core.ApiInvoker;
import com.siemens.mindsphere.sdk.core.CommonUtil;
import com.siemens.mindsphere.sdk.core.CoreCustomizationKeys;
import com.siemens.mindsphere.sdk.core.ErrorObject;
import com.siemens.mindsphere.sdk.core.Identification;
import com.siemens.mindsphere.sdk.core.Identification.GrantTypeEnum;
import com.siemens.mindsphere.sdk.core.InvokerConfig;
import com.siemens.mindsphere.sdk.core.RestClientConfig;
import com.siemens.mindsphere.sdk.core.RestClientUtil;
import com.siemens.mindsphere.sdk.core.constants.Constants;
import com.siemens.mindsphere.sdk.core.constants.ErrorConstants;
import com.siemens.mindsphere.sdk.core.constants.TokenManagerConstants;
import com.siemens.mindsphere.sdk.core.exception.MindsphereClientException;
import com.siemens.mindsphere.sdk.core.exception.MindsphereException;
import com.siemens.mindsphere.sdk.core.exception.MindsphereForbiddenAccessException;
import com.siemens.mindsphere.sdk.core.exception.MindsphereServiceException;
import com.siemens.mindsphere.sdk.core.model.AppCredentials;
import com.siemens.mindsphere.services.MessageBrokerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MessageBrokerController {

	@Autowired
	MessageBrokerService messageBrokerService;

	@RequestMapping(method = RequestMethod.POST, value = "/alertNotification", consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String alertfrommsgBroker(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException, IOException {

		String body = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);

		return messageBrokerService.storeNotificationDatatofile(body);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/readNotification")
	public String readNotification(HttpServletRequest request) throws MindsphereException, IOException {

		String response = messageBrokerService.readNotificationData();
		List<String> responselist = Arrays.asList(response.split(","));
		System.out.println(response);
		return responselist.toString();
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/subscribe/{bakcnedappName}/versions/{version}/topics/{topicName}", consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String subscibeToMsgBroker(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request, @PathVariable("bakcnedappName") String bakcnedappName,
			@PathVariable("version") String version, @PathVariable("topicName") String topicName,
			@RequestBody MsgBrokerSubscriptionRequest postbody) throws MindsphereException, IOException {

		return messageBrokerService.subscribetoMsgBroker(postbody, bakcnedappName, version, topicName, token);

	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/unsubscribe/{bakcnedappName}/versions/{version}/topics/{topicName}")
	public void unsubscibeToMsgBroker(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request, @PathVariable("bakcnedappName") String bakcnedappName,
			@PathVariable("version") String version, @PathVariable("topicName") String topicName) throws MindsphereException, IOException {

		 messageBrokerService.unsubscribetoMsgBroker( bakcnedappName, version, topicName, token);

	}
	

}
