package com.siemens.mindsphere.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.siemens.mindsphere.helpers.UpdatedTimeSeriesHelper;
import com.siemens.mindsphere.sdk.core.exception.MindsphereException;
import com.siemens.mindsphere.services.TSAggregateService;

@RestController
@RequestMapping(value = "/iottsaggregates")
public class TSAggregateController {

	@Autowired
	TSAggregateService tSAggregateService;
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/gettimeseries/{entityId}/{propertySetName}")
	public String retrieveTimeseriesTest(@PathVariable("entityId") String entityId,
            @PathVariable("propertySetName") String propertySetName,@RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
			throws MindsphereException {

		UpdatedTimeSeriesHelper.selectToken(tSAggregateService, token, request.getRequestURL().toString());
		String st = tSAggregateService.retrieveTimeseriesTest(entityId,propertySetName);
		return tSAggregateService.retrieveTimeseriesTest(entityId,propertySetName);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/gettimeserieswithfromandto/{entityId}/{propertySetName}")
	public String getAggregateTimeseriesWithselect(@PathVariable("entityId") String entityId,
            @PathVariable("propertySetName") String propertySetName, @RequestParam("from") String from,
            @RequestParam("to") String to,@RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
			throws MindsphereException {

		UpdatedTimeSeriesHelper.selectToken(tSAggregateService, token, request.getRequestURL().toString());
		String st = tSAggregateService.retrieveTimeseriesTest(entityId,propertySetName);
		return tSAggregateService.getAggregateTimeseriesWithselect(entityId,propertySetName,from,to);
	}
	
	
	

	
	
}
