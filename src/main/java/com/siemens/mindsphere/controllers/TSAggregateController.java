package com.siemens.mindsphere.controllers;

import java.io.IOException;

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
	
	/**
	 * The aggregate service enables querying aggregated time series data for performance assets based on pre-calculated aggregate values.
	 */
	
	/**
	 * For complete API specification of timeseries aggregate service refer :
	 * https://developer.mindsphere.io/apis/iot-iottsaggregates/api-iottsaggregates-api.html
	 */

	@Autowired
	TSAggregateService tSAggregateService;
	
	
	/**
	 * @route /gettimeseries/{entityId}/{propertySetName}
	 * @param entityId - An Asset Id for which aggregates are to be retrieved
	 * @param propertySetName - property setname for which aggregates will be be retrieved.
	 * @note Non existent/Incorrect entityId and propertySetName will result in MindsphereException.
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return Time series aggregates in String format.
	 * @returnType String
	 * @description This method - retrieveTimeseriesTest internally calls method retrieveAggregates of AggregatesClientV4 class.
	 * 				This class is available as dependency in tsaggregates-sdk-<version-here>.jar. 
	 * 				For retrieveTimeseriesTest, two parameters are passed - entityId - An Asset Id for which aggregates are to be retrieved and
	 * 				property setname for which aggregates will be be retrieved.
	 * 				With an absense of any other parameters aggregates will be returned by following rule : 
	 * 				The parameters from, to, intervalUnit, intervalValue, and count are used to determine the time range and interval length to 
	 * 				return aggregates for. Intelligent auto-completion is applied to allow clients to only provide a subset of the parameters, according to the following rules:
	 * 				In case none of the parameters is provided, intervalUnit is set to DAY, intervalValue is set to 1, to is set to the current 
	 * 				time, and from is set to the current time minus 7 days.
	 * @apiEndpoint : GET /api/iottsaggregates/v4/aggregates of aggregate service.
	 *              service.
	 * @apiNote Returns a list of aggregates for a given asset and aspect. The time range of the aggregates can be defined by a 
	 * 			combination of parameters; such as from, to, intervalUnit, intervalValue and count. Time range can be specified anywhere 
	 * 			in past or future for which timeseries data is present. In the case no time series data was available for an aggregation 
	 * 			interval, no aggregate will be returned. Pre-computed aggregates are aligned with the tenant's time zone.
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/gettimeseries/{entityId}/{propertySetName}")
	public String retrieveTimeseriesTest(@PathVariable("entityId") String entityId,
            @PathVariable("propertySetName") String propertySetName,@RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
			throws MindsphereException {

		UpdatedTimeSeriesHelper.selectToken(tSAggregateService, token, request.getRequestURL().toString());
		String st = tSAggregateService.retrieveTimeseriesTest(entityId,propertySetName);
		return tSAggregateService.retrieveTimeseriesTest(entityId,propertySetName);
	}
	
	
	/**
	 * @route /gettimeserieswithfromandto/{entityId}/{propertySetName}
	 * @param entityId - An Asset Id for which aggregates are to be retrieved
	 * @param propertySetName - property setname for which aggregates will be be retrieved.
	 * @param from - Point in time from which aggregates are to be retrieved.
	 * @param to - 	Point in time to which aggregates are to be retrieved.
	 * @note Non existent/Incorrect entityId and propertySetName will result in MindsphereException.
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return Time series aggregates in String format.
	 * @returnType String
	 * @description This method - retrieveTimeseriesTest internally calls method retrieveAggregates of AggregatesClientV4 class.
	 * 				This class is available as dependency in tsaggregates-sdk-<version-here>.jar. 
	 * 				The parameters from, to, intervalUnit, intervalValue, and count are used to determine the time range and interval length to 
	 * 				return aggregates for. 
	 * If intervalUnit and intervalValue are not provided, the largest available interval length fitting into the used time range is chosen.
	 * If count is not provided, but the other parameters are, count will be derived based on the time range divided by the intervalUnit and 
	 * intervalValue.
	 * In case parameters from or to are provided but do not coincide with the pre-calculated interval boundaries of the used interval, from and 
	 * to are shifted such that the overall time range contains the provided one and time range boundaries coincide with interval boundaries.
	 * If from, to and count are provided, intervalUnit, intervalValue is determined based on the time range divided by count.
	 * 
	 * 
	 * @apiEndpoint : GET /api/iottsaggregates/v4/aggregates of aggregate service.
	 *              service.
	 * @apiNote Returns a list of aggregates for a given asset and aspect. The time range of the aggregates can be defined by a 
	 * 			combination of parameters; such as from, to, intervalUnit, intervalValue and count. Time range can be specified anywhere 
	 * 			in past or future for which timeseries data is present. In the case no time series data was available for an aggregation 
	 * 			interval, no aggregate will be returned. Pre-computed aggregates are aligned with the tenant's time zone.
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
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
