package com.siemens.mindsphere.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.siemens.mindsphere.helpers.TimeSeriesHelper;
import com.siemens.mindsphere.sdk.core.exception.MindsphereException;
import com.siemens.mindsphere.services.TimeSeriesService;

@RestController
@RequestMapping(value = "/timeSeries")
public class TimeSeriesController {

    @Autowired
    private TimeSeriesService timeSeriesService;

    @RequestMapping(method = RequestMethod.GET, value = "/create/{entityId}/{propertySetName}")
    public String createTimeSeriesData(@PathVariable("entityId") String entityId,
            @PathVariable("propertySetName") String propertySetName,
            @RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
            throws MindsphereException {

        TimeSeriesHelper.selectToken(timeSeriesService, token, request.getRequestURL().toString());
        return timeSeriesService.createTimeSeriesData(entityId, propertySetName);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/create/{entityId}/{propertySetName}/exception")
    public String createTimeSeriesDataForException(@PathVariable("entityId") String entityId,
            @PathVariable("propertySetName") String propertySetName,
            @RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
            throws Exception {
        try {
            
        
        TimeSeriesHelper.selectToken(timeSeriesService, token, request.getRequestURL().toString());
        return timeSeriesService.createTimeSeriesDataWithException(entityId, propertySetName);
        }
        catch(MindsphereException e) {
            throw new Exception(e);
        }

    }

    @RequestMapping(method = RequestMethod.GET, value = "/delete/{entityId}/{propertySetName}")
    public String deleteTimeSeriesData(@PathVariable("entityId") String entity,
            @PathVariable("propertySetName") String propertySetName, @RequestParam("from") String from,
            @RequestParam("to") String to, @RequestHeader(required = false, value = "Authorization") String token,
            HttpServletRequest request) {
        TimeSeriesHelper.selectToken(timeSeriesService, token, request.getRequestURL().toString());
        return timeSeriesService.deleteAssets(entity, propertySetName, from, to);
    }

 // http://localhost:8080/timeseries/next/078b1908bc9347678168760934465587/TyreTemperature?from=2018-10-30T08:50:54.994Z&to=2018-10-31T09:53:54.994Z&limit=2
    // http://localhost:8080/tt/timeseries/next/078b1908bc9347678168760934465587/TyreTemperature?from=2019-08-13T08:50:54.994Z&to=2019-08-29T09:53:54.994Z&limit=02
     @RequestMapping(method = RequestMethod.GET, value = "/next/{entityId}/{propertySetName}")
     public String getNextTimeseries(@PathVariable("entityId") String entityId,
             @PathVariable("propertySetName") String propertySetName, @RequestParam("from") String from,
             @RequestParam("to") String to, @RequestParam("limit") String pageSize,
             @RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request) throws MindsphereException {
          TimeSeriesHelper.selectToken(timeSeriesService, token, request.getRequestURL().toString());
          return timeSeriesService.getNextTimeseries(entityId, propertySetName, from, to, pageSize);
         
     }
     
     //http://localhost:8080/timeSeries/page/078b1908bc9347678168760934465587/TyreTemperature?from=2018-10-30T08:50:54.994Z&to=2018-10-31T09:53:54.994Z&limit=2&page=2
     
     @RequestMapping(method = RequestMethod.GET, value = "/page/{entityId}/{propertySetName}")
     public String getPage(@PathVariable("entityId") String entityId,
             @PathVariable("propertySetName") String propertySetName, @RequestParam("from") String from,
             @RequestParam("to") String to, @RequestParam("limit") String pageSize, @RequestParam("page") String page,
             @RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request) throws MindsphereException {
         TimeSeriesHelper.selectToken(timeSeriesService, token, request.getRequestURL().toString());
         return timeSeriesService.getPage(entityId, propertySetName, from, to, pageSize, page);
     }

}
