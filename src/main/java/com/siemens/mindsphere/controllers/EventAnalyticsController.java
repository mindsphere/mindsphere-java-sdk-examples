package com.siemens.mindsphere.controllers;

import java.io.IOException;

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

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/eventAnalytics")
@Slf4j
public class EventAnalyticsController {
	
	/**
	 * For complete API specification of eventanalytics service refer :
	 * https://developer.mindsphere.io/apis/analytics-eventanalytics/api-eventanalytics-api.html
	 */


	@Autowired
	EventAnalyticsService eventAnalyticsService;

	/**
	 * @route /topevents
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return The most frequent events, which are sorted by the number of appearances in a dataset in a descending order in String format.
	 * @returnType String
	 * @description This method - findEvents internally calls method topEvents of EventOperationsClient class.
	 * 				This class is available as dependency in eventanalytics-sdk-<version-here>.jar.
	 *              
	* @apiEndpoint : POST /api/eventanalytics/v3/findTopEvents of eventanalytics
	 *              service.
	 * @apiNote Finds the most frequent events, which are sorted by the number of appearances in a dataset in a descending order.
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/topevents")
	public String findEvents(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {
		log.info("/eventAnalytics/topevents invoked.");
		EventAnalyticsHelper.selectToken(eventAnalyticsService, token, request.getRequestURL().toString());
		return eventAnalyticsService.findEvents();
	}

	/**
	 * @route /countEvents
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return Output the given time interval (startTime, endTime) and the resulted number of event occurrences.
	 * @returnType String
	 * @description This method - countEvents internally calls method countEvents of EventOperationsClient class.
	 * 				This class is available as dependency in eventanalytics-sdk-<version-here>.jar. The method countEvents take CountEventsRequest
	 * 				as parameter. This request object is formed and passed dynamically.
	 * 				This method takes data as part of request body.
	 * 				`data` is a Data structure with two parts eventsMetadata, events.
	 * 				eventsMetadata Metadata ->  for the events list specifying the property name of the item in the events list that contains 
	 *				the text of the event (eventTextPropertyName) and time window length in miliseconds of the period in which time interval 
	 *				will be split (splitInterval).
	 *				events List -->  with the events that will be processed.  Please refer : EventAnalyticsHelper::getEventsList() for list
	 * 				of events.
	 *              
	* @apiEndpoint : POST /api/eventanalytics/v3/countEvents of eventanalytics
	 *              service.
	 * @apiNote Determines the number of events for a required time resolution.
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/countEvents")
	public String countEvents(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {
		log.info("/eventAnalytics/countEvents invoked.");
		EventAnalyticsHelper.selectToken(eventAnalyticsService, token, request.getRequestURL().toString());
		return eventAnalyticsService.countEvent();
	}

	
	/**
	 * @route /removeDuplicateEvents
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return List of events after removal of duplicate events in String format.
	 * @returnType String
	 * @description This method - removeDuplicateEvents internally calls method removeDuplicateEvents of EventOperationsClient class.
	 * 				This class is available as dependency in eventanalytics-sdk-<version-here>.jar. The method countEvents take CountEventsRequest
	 * 				as parameter. This request object is formed and passed dynamically.
	 * 				This method takes data as part of request body.
	 * 				`data` is a Data structure with two parts eventsMetadata, events.
	 * 				eventsMetadata Metadata ->  for the events list specifying the property name of the item in the events list that contains 
	 *				the text of the event (eventTextPropertyName) and time window length in miliseconds of the period in which time interval 
	 *				will be split (splitInterval).
	 *				events List -->  with the events that will be processed.  Please refer : EventAnalyticsHelper::getRemoveDuplicateData() for list
	 * 				of events.
	 *              
	* @apiEndpoint : POST /api/eventanalytics/v3/removeDuplicateEvents of eventanalytics
	 *              service.
	 * @apiNote Removes the duplicate events. Determine pre-existing relationships between events for a requested temporal resolution 
	 * 			(example 500ms) and reduce the data set by aggregating events with duplicate value.
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/removeDuplicateEvents")
	public String removeDuplicateEvents(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {
		log.info("/eventAnalytics/removeDuplicateEvents invoked.");
		EventAnalyticsHelper.selectToken(eventAnalyticsService, token, request.getRequestURL().toString());
		return eventAnalyticsService.removeDuplicateEvent();
	}

	/**
	 * @route /matchEventPatterns
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return List of events matching pattern.
	 * @returnType Object of PatternMatchingOutput class.
	 * @description This method - matchEventPatterns internally calls method matchPatternsOverEvents of PatternOperationsClient class.
	 * 				This class is available as dependency in eventanalytics-sdk-<version-here>.jar. The method countEvents take CountEventsRequest
	 * 				as parameter. This request object is formed and passed dynamically.
	 * 				This method takes data as part of request body.
	 * 				Data structure with four parts - maxPatternInterval, patternsList, nonEvents and eventsInput.
	 * 
	 * 				maxPatternInterval ---> The maximum time length (in milliseconds) of the sliding window where the pattern occurs 
	 * 				(Maximum difference allowed between the first event of the pattern and the last one).
	 * 				
	 * 				patternsList ---> The patterns to be found in events. The eventText can contain regular expressions. The acceptable syntax for 
	 * 				the regular expressions is the java syntax. minRepetitions and maxRepetitions represent the minimum and maximum number of 
	 * 				events of the specified type that are allowed to occur in order for the pattern to be matched on the events.
	 * 				
	 * 				nonEvents ---> A list of events that is not allowed to be part of a pattern. Any pattern which contains a non-event is 
	 * 							   excluded from the final report.
	 * 				
	 * 				eventsInput ---> Metadata for the events list specifying the property name of the item in the events list that contains 
	 * 				the text of the event and the list with the events that will be processed.
	 *              
	* @apiEndpoint : POST /api/eventanalytics/v3/matchEventPatterns of eventanalytics
	 *              service.
	 * @apiNote Applies the patterns specified in body on a list of events. Finds all instances of the specified pattern(s) in a collection of events.
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/matchEventPatterns")
	public PatternMatchingOutput matchEventPatterns(
			@RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
			throws MindsphereException {
		log.info("/eventAnalytics/matchEventPatterns invoked.");
		EventAnalyticsHelper.selectToken(eventAnalyticsService, token, request.getRequestURL().toString());
		return eventAnalyticsService.matchEventPattern();
	}
}
