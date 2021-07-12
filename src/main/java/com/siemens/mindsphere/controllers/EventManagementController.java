package com.siemens.mindsphere.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.siemens.mindsphere.helpers.EventManagementHelper;
import com.siemens.mindsphere.sdk.core.exception.MindsphereException;
import com.siemens.mindsphere.services.EventManagementService;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping(value = "/eventMgmt")
@Slf4j
public class EventManagementController {
	
	/**
	 * For complete API specification of eventmanagement service refer :
	 * https://developer.mindsphere.io/apis/advanced-eventmanagement/api-eventmanagement-api.html
	 */

	@Autowired
	EventManagementService eventManagementService;

	/**
	 * @route /createtEventType
	 * @param token   - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return Created event type on successful execution.
	 * @returnType : Object
	 * @description This method - createtEventType internally calls method
	 *              createEventType(EventType object) on object of
	 *              EventTypesClient .This class is available as part of dependency :
	 *              eventmanagement-sdk-<version-here>.jar. Creation of event type is a POST call and requires request body to be passed.
	 *              Generation of request body can be found in eventManagementService.createtEventType(<arguments>) call.
	 *              EventManagementController line number : 52.
	 *           
	 * @apiEndpoint : POST /api/eventmanagement/v3/events of eventmanagement service.
	 * @apiNote Create new event.
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/createtEventType")
    public Object createtEventType(@RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request) throws MindsphereException, IOException {
		log.info("/eventMgmt/createtEventType invoked.");
	    EventManagementHelper.selectToken(eventManagementService, token, request.getRequestURL().toString());
	    return eventManagementService.createtEventType(token);
	    
    }
	
	
	/**
	 * @route /createCustomEvent/{typeId}/{entityId}
	 * @param entityId - Id of an asset to be associated with event.
	 * @typeId - Valid Id of type from based on which event is to be created. If there is a valid typeId in the request body then the 
	 * 		      event DTO must match to the corresponding event type given by the typeId.
	 * @param token   - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return Created event type on successful execution.
	 * @returnType : Object
	 * @description This method - createCustomEvent internally calls method
	 *              createCustomEvent(CustomEvent object) on object of
	 *              EventTypesClient .This class is available as part of dependency :
	 *              eventmanagement-sdk-<version-here>.jar. Creation of event type is a POST call and requires request body to be passed.
	 *              Generation of request body can be found in eventManagementService.createCustomEvent(<arguments>) call.
	 *              EventManagementController line number : 82.
	 *           
	 * @apiEndpoint : POST /api/eventmanagement/v3/events of eventmanagement service.
	 * @apiNote Creates custom event with the provided content.
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
    @RequestMapping(method = RequestMethod.GET, value = "/createCustomEvent/{typeId}/{entityId}")
    public Object createCustomEvent(@PathVariable("typeId") String eventTypeId,@PathVariable("entityId") String entityId,
            @RequestHeader(required = false, value = "Authorization") String token,  HttpServletRequest request) throws MindsphereException, IOException {
    	log.info("/eventMgmt/createCustomEvent/"+eventTypeId+"/"+entityId+" invoked.");
        EventManagementHelper.selectToken(eventManagementService, token, request.getRequestURL().toString());
        return eventManagementService.createCustomEvent(eventTypeId, entityId, token);
    }
}
