package com.siemens.mindsphere.controllers;

import java.io.IOException;

import java.util.Arrays;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.siemens.mindsphere.model.MsgBrokerSubscriptionResponse;
import com.siemens.mindsphere.model.MsgBrokerSubscriptionRequest;

import com.siemens.mindsphere.sdk.core.exception.MindsphereException;

import com.siemens.mindsphere.services.MessageBrokerService;

@RestController
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
	public String readNotification(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException, IOException {

		String response = messageBrokerService.readNotificationData();
		List<String> responselist = Arrays.asList(response.split(","));
		System.out.println(response);
		return responselist.toString();
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/subscribe/{bakcnedappName}/versions/{version}/topics/{topicName}", consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public MsgBrokerSubscriptionResponse subscibeToMsgBroker(
			@RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request,
			@PathVariable("bakcnedappName") String bakcnedappName, @PathVariable("version") String version,
			@PathVariable("topicName") String topicName, @RequestBody MsgBrokerSubscriptionRequest postbody)
			throws MindsphereException, IOException {

		MsgBrokerSubscriptionResponse response = messageBrokerService.subscribetoMsgBroker(postbody, bakcnedappName,
				version, topicName, token);
		return response;

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/unsubscribe/{bakcnedappName}/versions/{version}/topics/{topicName}")
	public void unsubscibeToMsgBroker(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request, @PathVariable("bakcnedappName") String bakcnedappName,
			@PathVariable("version") String version, @PathVariable("topicName") String topicName)
			throws MindsphereException, IOException {

		messageBrokerService.unsubscribetoMsgBroker(bakcnedappName, version, topicName, token);

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/deleteContent")
	public String deleteContent(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException, IOException {

		return messageBrokerService.deleteContent();

	}

}
