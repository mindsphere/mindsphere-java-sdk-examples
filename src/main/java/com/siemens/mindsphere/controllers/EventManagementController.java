package com.siemens.mindsphere.controllers;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.siemens.mindsphere.helpers.AssetManagementHelper;
import com.siemens.mindsphere.helpers.EventManagementHelper;
import com.siemens.mindsphere.sdk.core.RestClientConfig;
import com.siemens.mindsphere.sdk.core.exception.MindsphereClientException;
import com.siemens.mindsphere.sdk.core.exception.MindsphereException;
import com.siemens.mindsphere.sdk.eventmanagement.apiclient.EventTypesClient;
import com.siemens.mindsphere.sdk.eventmanagement.apiclient.EventsClient;
import com.siemens.mindsphere.sdk.eventmanagement.constants.Constants;
import com.siemens.mindsphere.sdk.eventmanagement.model.BaseEvent;
import com.siemens.mindsphere.sdk.eventmanagement.model.CustomEvent;
import com.siemens.mindsphere.sdk.eventmanagement.model.EventType;
import com.siemens.mindsphere.sdk.eventmanagement.model.EventType.ScopeEnum;
import com.siemens.mindsphere.sdk.eventmanagement.model.Events;
import com.siemens.mindsphere.sdk.eventmanagement.model.Field;
import com.siemens.mindsphere.sdk.eventmanagement.model.Field.TypeEnum;
import com.siemens.mindsphere.sdk.eventmanagement.model.MindSphereStandardEvent;
import com.siemens.mindsphere.sdk.eventmanagement.util.EventManagementUtil;
import com.siemens.mindsphere.services.EventManagementService;


@RestController
@RequestMapping(value = "/eventMgmt")
public class EventManagementController {

	@Autowired
	EventManagementService eventManagementService;

	@RequestMapping(method = RequestMethod.GET, value = "/createtEventType")
    public Object createtEventType(@RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request) throws MindsphereException, IOException {
	    EventManagementHelper.selectToken(eventManagementService, token, request.getRequestURL().toString());
	    return eventManagementService.createtEventType(token);
	    
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/createCustomEvent/{typeId}/{entityId}")
    public Object createCustomEvent(@PathVariable("typeId") String eventTypeId,@PathVariable("entityId") String entityId,
            @RequestHeader(required = false, value = "Authorization") String token,  HttpServletRequest request) throws MindsphereException, IOException {
        EventManagementHelper.selectToken(eventManagementService, token, request.getRequestURL().toString());
        return eventManagementService.createCustomEvent(eventTypeId, entityId, token);
    }
}
