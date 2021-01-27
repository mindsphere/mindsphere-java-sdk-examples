package com.siemens.mindsphere.controllers;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.siemens.mindsphere.helpers.TimeseriesAggregateHelper;
import com.siemens.mindsphere.sdk.core.exception.MindsphereException;
import com.siemens.mindsphere.sdk.tsaggregates.model.Aggregates;
import com.siemens.mindsphere.services.TimeseriesAggregateService;

@RestController
@RequestMapping(value = "/timeseriesaggregate")
public class TimeseriesAggregatesController {

    @Autowired
    private TimeseriesAggregateService timeseriesAggregateService;

    @RequestMapping(method = RequestMethod.GET, value = "/get/{entityId}/{propertySetName}")
    public List<Aggregates> createTimeSeriesData(@PathVariable("entityId") String entityId,
            @PathVariable("propertySetName") String propertySetName, @RequestParam("from") String from,
            @RequestParam("to") String to, @RequestParam("limit") int limit,
            @RequestParam("intervalValue") BigDecimal intervalValue, @RequestParam("intervalUnit") String intervalUnit,
            @RequestParam("select") String select,
            @RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
            throws MindsphereException {

        TimeseriesAggregateHelper.selectToken(timeseriesAggregateService, token,request.getRequestURL().toString());
        return timeseriesAggregateService.getTimeseriesAggregate(entityId, propertySetName, from, to, intervalValue,
                intervalUnit, select);
    }
}
