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
import com.siemens.mindsphere.services.UpdatedTimeseriesService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/iottimeseries")
@Slf4j
public class UpdatedTimeseriesController {
	
	/**
	 * Time Series Service : Create, update, and query time series data with a precision of 1 millisecond.
	 */
	
	/**
	 * For complete API specification of timeseries service refer :
	 * https://developer.mindsphere.io/apis/iot-iottimeseries/api-iottimeseries-api.html
	 */

	@Autowired
	private UpdatedTimeseriesService timeSeriesService;

	
	/**
	 * @route /puttimeseries
	 
	 * @param token   - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return "created successfully" on successful execution.
	 * @returnType String
	 * @description This method - createTimeSeriesData internally calls method createOrUpdateTimeseries of
	 *              TimeSeriesOperationsClient class. This class is available as dependency
	 *              in timeseries-sdk-<version-here>.jar. Creation of timeseries requires `timeseries` data structure to be passed
	 *              in request body. This data structure is created with the help of CreateOrUpdateTimeseriesRequest of helper class 
	 *              - UpdatedTimeSeriesHelper.
	 *              Request Body Limitations are as follows : 
	 *              1) A maximum of 5 asset-aspect (entity-property set) combinations can be provided
	 *              2) The request body size must be equal or less than 100 kb
	 *              3) A maximum of 100 time series data items can be provided overall
	 * @apiEndpoint : PUT /api/iottimeseries/v3/timeseries of timeseries service.
	 *              service. service.
	 * @apiNote Create or update time series data for mutiple unique asset-aspect (entity-property set) combinations.
	 * @throws MindsphereException if an error occurs while attempting to invoke the
	 *                             sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/puttimeseries")
	public String createTimeSeriesData(@RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
			throws MindsphereException {

		log.info("/iottimeseries/puttimeseries invoked.");
		UpdatedTimeSeriesHelper.selectToken(timeSeriesService, token, request.getRequestURL().toString());
		return timeSeriesService.createOrUpdateTimeseries();
	}
	
	/**
	 * @route /gettimeseries/{entityId}/{propertySetName}
	 * @param entityId - unique identifier of the asset (entity)
	 * @param propertySetName - Name of the aspect (property set).
	 * @param token   - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return Timeseries data in String format on successful execution.
	 * @returnType String
	 * @description This method - retrieveTimeseriesTest internally calls method retrieveTimeseries of
	 *              TimeSeriesOperationsClient class. This class is available as dependency
	 *              in timeseries-sdk-<version-here>.jar. The method retrieveTimeseries takes object RetrieveTimeseriesRequest as a parameter.
	 *              Values of RetrieveTimeseriesRequest's object fields are set in timeSeriesService.retrieveTimeseriesTest(entityId,propertySetName)
	 *              method.
	 *              entityId and propertySetName are passed in RetrieveTimeseriesRequest's object as given by user(path variables) 
	 *              and hence incorrect/non-existent values will result in MindsphereException.
	 *              
	 * @apiEndpoint : GET /api/iottimeseries/v3/timeseries/{entityId}/{propertySetName} of timeseries service.
	 *              service. service.
	 * @apiNote Retrieve time series data for one combination of an asset (entity) and an(a) aspect (property set). 
	 * @throws MindsphereException if an error occurs while attempting to invoke the
	 *                             sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/gettimeseries/{entityId}/{propertySetName}")
	public String retrieveTimeseriesTest(@PathVariable("entityId") String entityId,
            @PathVariable("propertySetName") String propertySetName,@RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
			throws MindsphereException {

		log.info("/iottimeseries/gettimeseries/"+entityId+"/"+propertySetName+" invoked.");
		UpdatedTimeSeriesHelper.selectToken(timeSeriesService, token, request.getRequestURL().toString());
		return timeSeriesService.retrieveTimeseriesTest(entityId,propertySetName);
	}
	
	/**
	 * @route /gettimeserieswithfromto/{entityId}/{propertySetName}
	 * @param entityId - unique identifier of the asset (entity)
	 * @param propertySetName - Name of the aspect (property set).
	 * @param from - Beginning of the time range to be retrieved (exclusive).
	 * @param to - End of the time range to be retrieved (inclusive).
	 * @param token   - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return Timeseries data in String format on successful execution.
	 * @returnType String
	 * @description This method - retrieveTimeserieswithFromandToTest internally calls method retrieveTimeseries of
	 *              TimeSeriesOperationsClient class. This class is available as dependency
	 *              in timeseries-sdk-<version-here>.jar. The method retrieveTimeseries takes object RetrieveTimeseriesRequest as a parameter.
	 *              Values of RetrieveTimeseriesRequest's object fields are set in timeSeriesService.retrieveTimeserieswithFromandToTest(entityId,propertySetName,from,to)
	 *              method.
	 *              entityId and propertySetName are passed in RetrieveTimeseriesRequest's object as given by user and hence incorrect/non-existent
	 *              values will result in MindsphereException.
	 *              This method is essentially same as above method --> retrieveTimeseriesTest except for the fact that here user is allowed
	 *              to pass two extra parameters - 	`from` and `to`.
	 *              
	 * @apiEndpoint : GET /api/iottimeseries/v3/timeseries/{entityId}/{propertySetName} of timeseries service.
	 *              service. service.
	 * @apiNote Retrieve time series data for one combination of an asset (entity) and an(a) aspect (property set). 
	 * @throws MindsphereException if an error occurs while attempting to invoke the
	 *                             sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/gettimeserieswithfromto/{entityId}/{propertySetName}")
	public String retrieveTimeserieswithFromandToTest(@PathVariable("entityId") String entityId,
            @PathVariable("propertySetName") String propertySetName,@RequestParam("from") String from,
            @RequestParam("to") String to,@RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
			throws MindsphereException {
		
		log.info("/iottimeseries/gettimeserieswithfromto/"+entityId+"/"+propertySetName+" invoked with from :"+from +" and to :"+to);
		UpdatedTimeSeriesHelper.selectToken(timeSeriesService, token, request.getRequestURL().toString());
		return timeSeriesService.retrieveTimeserieswithFromandToTest(entityId,propertySetName,from,to);
	}
	
	/**
	 * @route /puttimeseriesdata/{entityId}/{propertySetName}
	 * @param entityId - unique identifier of the asset (entity)
	 * @param propertySetName - Name of the aspect (property set).
	 * @param token   - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return "success" on successful execution.
	 * @returnType String
	 * @description This method - createOrUpdateTimeseriesData internally calls method createOrUpdateTimeseries of
	 *              TimeSeriesOperationsClient class. This class is available as dependency
	 *              in timeseries-sdk-<version-here>.jar. 
	 *              createOrUpdateTimeseries method of TimeSeriesOperationsClient class takes CreateOrUpdateTimeseriesDataRequest object as a parameter.
	 *              entityId and propertySetName are passed in CreateOrUpdateTimeseriesDataRequest's object as given by user and hence incorrect/non-existent
	 *              values will result in MindsphereException.
	 *              Request Body Limitations are as follows : 
	 *              1) A maximum of 5 asset-aspect (entity-property set) combinations can be provided
	 *              2) The request body size must be equal or less than 100 kb
	 *              3) A maximum of 100 time series data items can be provided overall.
	 *  Creation of timeseries requires `timeseries` data structure to be passed in request body. This data structure is created with the help of 
	 *  CreateOrUpdateTimeseriesDataRequest method of helper class  - UpdatedTimeSeriesHelper. 
	 * @apiEndpoint : PUT /api/iottimeseries/v3/timeseries of timeseries service.
	 *              service. service.
	 * @apiNote Create or update time series data for mutiple unique asset-aspect (entity-property set) combinations.
	 * @throws MindsphereException if an error occurs while attempting to invoke the
	 *                             sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/puttimeseriesdata/{entityId}/{propertySetName}")
	public String createOrUpdateTimeseriesData(@PathVariable("entityId") String entityId,
            @PathVariable("propertySetName") String propertySetName,@RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
			throws MindsphereException {
		log.info("/iottimeseries/puttimeseriesdata/"+entityId+"/"+propertySetName+" invoked.");
		UpdatedTimeSeriesHelper.selectToken(timeSeriesService, token, request.getRequestURL().toString());
		return timeSeriesService.createOrUpdateTimeseriesData(entityId,propertySetName);
	}
	
	
	/**
	 * @route /deletetimeserieswithfromto/{entityId}/{propertySetName}
	 * @param entityId - unique identifier of the asset (entity), (required)
	 * @param propertySetName - Name of the aspect (property set), (required)
	 * @param from - beginning of the timerange to delete (exclusive), (required)
	 * @param to - end of the timerange to delete (inclusive),   (required)
	 * @param token   - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return "success"(String) on successful execution.
	 * @returnType String
	 * @description This method - deleteTimeserieswithFromandToTest internally calls method deleteTimeseries of
	 *              TimeSeriesOperationsClient class. This class is available as dependency
	 *              in timeseries-sdk-<version-here>.jar. The method deleteTimeseries takes DeleteUpdatedTimeseriesRequest object as a parameter.
	 *              Values of DeleteUpdatedTimeseriesRequest's object fields are set in timeSeriesService.deleteTimeserieswithFromandToTest(entityId,propertySetName,from,to)
	 *              method.
	 *              entityId and propertySetName are passed in DeleteUpdatedTimeseriesRequest's object as given by user and hence incorrect/non-existent
	 *              values will result in MindsphereException.
	 * @apiEndpoint : DELETE /api/iottimeseries/v3/timeseries/{entityId}/{propertySetName} of timeseries service.
	 *              service. service.
	 * @apiNote Delete time series data for one combination of an asset (entity) and an(a) aspect (property set). 
	 * 			All property values within the given time range are deleted.
	 * @throws MindsphereException if an error occurs while attempting to invoke the
	 *                             sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/deletetimeserieswithfromto/{entityId}/{propertySetName}")
	public String deleteTimeserieswithFromandToTest(@PathVariable("entityId") String entityId,
            @PathVariable("propertySetName") String propertySetName,@RequestParam("from") String from,
            @RequestParam("to") String to,@RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
			throws MindsphereException {
		log.info("/iottimeseries/deletetimeserieswithfromto/"+entityId+"/"+propertySetName+" invoked with from :"+from +" and to :"+to);
		UpdatedTimeSeriesHelper.selectToken(timeSeriesService, token, request.getRequestURL().toString());
		return timeSeriesService.deleteTimeserieswithFromandToTest(entityId,propertySetName,from,to);
	}
	
	
	
	

}
