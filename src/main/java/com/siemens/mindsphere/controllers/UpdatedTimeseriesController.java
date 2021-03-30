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
import com.siemens.mindsphere.services.UpdatedTimeseriesService;

@RestController
@RequestMapping(value = "/iottimeseries")
public class UpdatedTimeseriesController {

	@Autowired
	private UpdatedTimeseriesService timeSeriesService;

	@RequestMapping(method = RequestMethod.GET, value = "/puttimeseries")
	public String createTimeSeriesData(@RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
			throws MindsphereException {

		UpdatedTimeSeriesHelper.selectToken(timeSeriesService, token, request.getRequestURL().toString());
		return timeSeriesService.createOrUpdateTimeseries();
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/gettimeseries/{entityId}/{propertySetName}")
	public String retrieveTimeseriesTest(@PathVariable("entityId") String entityId,
            @PathVariable("propertySetName") String propertySetName,@RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
			throws MindsphereException {

		UpdatedTimeSeriesHelper.selectToken(timeSeriesService, token, request.getRequestURL().toString());
		return timeSeriesService.retrieveTimeseriesTest(entityId,propertySetName);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/gettimeserieswithfromto/{entityId}/{propertySetName}")
	public String retrieveTimeserieswithFromandToTest(@PathVariable("entityId") String entityId,
            @PathVariable("propertySetName") String propertySetName,@RequestParam("from") String from,
            @RequestParam("to") String to,@RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
			throws MindsphereException {

		UpdatedTimeSeriesHelper.selectToken(timeSeriesService, token, request.getRequestURL().toString());
		return timeSeriesService.retrieveTimeserieswithFromandToTest(entityId,propertySetName,from,to);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/puttimeseriesdata/{entityId}/{propertySetName}")
	public String createOrUpdateTimeseriesData(@PathVariable("entityId") String entityId,
            @PathVariable("propertySetName") String propertySetName,@RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
			throws MindsphereException {

		UpdatedTimeSeriesHelper.selectToken(timeSeriesService, token, request.getRequestURL().toString());
		return timeSeriesService.createOrUpdateTimeseriesData(entityId,propertySetName);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/deletetimeserieswithfromto/{entityId}/{propertySetName}")
	public String deleteTimeserieswithFromandToTest(@PathVariable("entityId") String entityId,
            @PathVariable("propertySetName") String propertySetName,@RequestParam("from") String from,
            @RequestParam("to") String to,@RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
			throws MindsphereException {

		UpdatedTimeSeriesHelper.selectToken(timeSeriesService, token, request.getRequestURL().toString());
		return timeSeriesService.deleteTimeserieswithFromandToTest(entityId,propertySetName,from,to);
	}
	
	
	
	

}
