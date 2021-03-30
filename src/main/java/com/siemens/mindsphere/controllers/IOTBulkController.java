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
import com.siemens.mindsphere.services.IOTBulkService;

@RestController
@RequestMapping(value = "/iotbulkdata")
public class IOTBulkController {
	
	
	@Autowired 
    IOTBulkService iotBulkService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/importjobs")
	public String createTimeSeriesData(@RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
			throws MindsphereException {

		UpdatedTimeSeriesHelper.selectToken(iotBulkService, token, request.getRequestURL().toString());
		return iotBulkService.importjobs();
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/importjobs/{id}")
	public String retrieveImportJobTest(@PathVariable("id") String id,@RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
			throws MindsphereException {

		UpdatedTimeSeriesHelper.selectToken(iotBulkService, token, request.getRequestURL().toString());
		return iotBulkService.retrieveImportJobTest(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/gettimeseries/{entityId}/{propertySetName}")
	public String retrieveTimeseriesTest(@PathVariable("entityId") String entityId,
            @PathVariable("propertySetName") String propertySetName,@RequestParam("from") String from,
            @RequestParam("to") String to,@RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
			throws MindsphereException {

		UpdatedTimeSeriesHelper.selectToken(iotBulkService, token, request.getRequestURL().toString());
		return iotBulkService.retrieveTimeseriesTest( entityId,propertySetName,from,to);
	}
	
	
	
}
