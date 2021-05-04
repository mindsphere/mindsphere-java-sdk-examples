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
import com.siemens.mindsphere.services.IOTBulkService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/iotbulkdata")
@Slf4j
public class IOTBulkController {
	
	/**
	 * For complete API specification of IoT Bulk service refer :
	 * https://developer.mindsphere.io/apis/iot-iottsbulk/api-iottsbulk-api.html
	 */
	
	
	@Autowired 
    IOTBulkService iotBulkService;
	
	
	/**
	 * @route /importjobs
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return Created import job object(JobStatus) in String format
	 * @returnType String
	 * @description This method - createTimeSeriesData internally calls method createImportJob of BulkImportOperationsClient class.
	 * 				This class is available as dependency in iotbulk-sdk-<version-here>.jar.
	 * 				Request body for import job is dynamically created in createImportJobRequest method of IOTBulkHelper class.
	 
	 * @apiEndpoint : POST /api/iottsbulk/v3/importJobs of iot bulk service.
	 *              service.
	 * @apiNote Create bulk import job for importing time series data.
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/importjobs")
	public String createTimeSeriesData(@RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
			throws MindsphereException {

		log.info("/iotbulkdata/importjobs invoked.");
		UpdatedTimeSeriesHelper.selectToken(iotBulkService, token, request.getRequestURL().toString());
		return iotBulkService.importjobs();
	}
	
	
	/**
	 * @route /importjobs/{id}
	 * @param id - Bulk import job ID as obtained on job creation. (This ID is part of response after creating import job). Refer 
	 * 				`createTimeSeriesData` method above.
	 * @note - incorrect/non-existent id will result in MindsphereException.
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return JobStatus corresponding to provided ID in String format.
	 * @returnType String
	 * @description This method - retrieveImportJobTest internally calls method retrieveImportJob of BulkImportOperationsClient class.
	 * 				This class is available as dependency in iotbulk-sdk-<version-here>.jar.
	 * 				retrieveImportJob of BulkImportOperationsClient class takes RetrieveImportJobRequest as a parameter.
	 * 				The object of RetrieveImportJobRequest is created based on id passed by user.
	 
	 * @apiEndpoint : GET /api/iottsbulk/v3/importJobs/{id} of iot bulk service.
	 *              service.
	 * @apiNote Retrieve status of bulk import job.
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/importjobs/{id}")
	public String retrieveImportJobTest(@PathVariable("id") String id,@RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
			throws MindsphereException {
		log.info("/iotbulkdata/importjobs/"+id+" invoked.");
		UpdatedTimeSeriesHelper.selectToken(iotBulkService, token, request.getRequestURL().toString());
		return iotBulkService.retrieveImportJobTest(id);
	}
	
	
	/**
	 * @route /gettimeseries/{entityId}/{propertySetName}
	 * @param entityId - Unique identifier of the asset (entity).
	 * @param propertySetName - Unique name of the aspect (property set).
	 * @param from - Beginning of the time range to read (exclusive). ‘from’ time must be less than ‘to’ time. 
	 * 				 Range between ‘from’ and ‘to’ time must be less than 90 days.
	 * @param to -   End of the time range to retrieve (inclusive).
	 * @note - incorrect/non-existent entityId/propertySetName will result in MindsphereException. All the four parameters mentioned above are
	 * 			required.
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return Time series data in String format.
	 * @returnType String
	 * @description This method - retrieveTimeseriesTest internally calls method retrieveTimeseries of ReadOperationsClient class.
	 * 				This class is available as dependency in iotbulk-sdk-<version-here>.jar.
	 * 				retrieveTimeseries of BulkImportOperationsClient class takes RetrieveTimeseriesRequest as a parameter.
	 * 				The object of RetrieveTimeseriesRequest is created based on values of entityId, propertySetName, from and to as passed by user.
	 
	 * @apiEndpoint : GET /api/iottsbulk/v3/timeseries/{entity}/{propertySetName} of iot bulk service.
	 *              service.
	 * @apiNote Retrieve time series data.
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/gettimeseries/{entityId}/{propertySetName}")
	public String retrieveTimeseriesTest(@PathVariable("entityId") String entityId,
            @PathVariable("propertySetName") String propertySetName,@RequestParam("from") String from,
            @RequestParam("to") String to,@RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
			throws MindsphereException {

		log.info("/iotbulkdata/gettimeseries/"+entityId+"/"+propertySetName+" invoked with from :"+from+" and to :"+to);
		UpdatedTimeSeriesHelper.selectToken(iotBulkService, token, request.getRequestURL().toString());
		return iotBulkService.retrieveTimeseriesTest( entityId,propertySetName,from,to);
	}
	
	
	
}
