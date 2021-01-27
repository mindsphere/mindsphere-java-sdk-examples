package com.siemens.mindsphere.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.siemens.mindsphere.helpers.EventAnalyticsHelper;
import com.siemens.mindsphere.sdk.core.exception.MindsphereException;
import com.siemens.mindsphere.sdk.eventanalytics.model.PatternMatchingOutput;
import com.siemens.mindsphere.services.EventAnalyticsService;

@RestController
@RequestMapping(value = "/eventAnalytics")
public class EventAnalyticsController {

	@Autowired
	EventAnalyticsService eventAnalyticsService;

	@RequestMapping(method = RequestMethod.GET, value = "/topevents")
	public String findEvents(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {

		EventAnalyticsHelper.selectToken(eventAnalyticsService, token, request.getRequestURL().toString());
		return eventAnalyticsService.findEvents();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/countEvents")
	public String countEvents(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {

		EventAnalyticsHelper.selectToken(eventAnalyticsService, token, request.getRequestURL().toString());
		return eventAnalyticsService.countEvent();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/removeDuplicateEvents")
	public String removeDuplicateEvents(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {

		EventAnalyticsHelper.selectToken(eventAnalyticsService, token, request.getRequestURL().toString());
		return eventAnalyticsService.removeDuplicateEvent();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/matchEventPatterns")
	public PatternMatchingOutput matchEventPatterns(
			@RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
			throws MindsphereException {

		EventAnalyticsHelper.selectToken(eventAnalyticsService, token, request.getRequestURL().toString());
		return eventAnalyticsService.matchEventPattern();
	}
}
