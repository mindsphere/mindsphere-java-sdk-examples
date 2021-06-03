package com.siemens.mindsphere.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.siemens.mindsphere.helpers.UpdatedTimeSeriesHelper;
import com.siemens.mindsphere.sdk.core.exception.MindsphereException;
import com.siemens.mindsphere.sdk.timeseries.apiclient.TimeSeriesOperationsClient;
import com.siemens.mindsphere.sdk.timeseries.model.CreateOrUpdateTimeseriesDataRequest;
import com.siemens.mindsphere.sdk.timeseries.model.CreateOrUpdateTimeseriesRequest;
import com.siemens.mindsphere.sdk.timeseries.model.DeleteUpdatedTimeseriesRequest;
import com.siemens.mindsphere.sdk.timeseries.model.MultiStatusError;
import com.siemens.mindsphere.sdk.timeseries.model.RetrieveTimeseriesRequest;
import com.siemens.mindsphere.sdk.timeseries.model.TimeSeries;
import com.siemens.mindsphere.sdk.timeseries.model.TimeSeriesDataItem;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UpdatedTimeseriesService extends MindsphereService {

	private UpdatedTimeSeriesHelper timeSeriesHelper = new UpdatedTimeSeriesHelper();
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	public String createOrUpdateTimeseries() throws MindsphereException {
		TimeSeriesOperationsClient ts = timeSeriesHelper.getTimeSeriesOperationsClient(getToken(), getHostName());
		log.info("timeSeriesOperationsClient object created successfully : {}");
		CreateOrUpdateTimeseriesRequest createOrUpdateTimeseriesRequest = timeSeriesHelper
				.CreateOrUpdateTimeseriesRequest();
		MultiStatusError multiStatusError = ts.createOrUpdateTimeseries(createOrUpdateTimeseriesRequest);
		if (multiStatusError != null) {
			log.info("Getting error for createOrUpdateTimeseries"+multiStatusError);
			return multiStatusError.toString();
		}
		else {
			log.info("Timeseries created successfully");
			return "created successfully";
		}
	}
	
	
	public MultiStatusError putOrUpdateTimeseries(TimeSeries timeSeries) throws MindsphereException {
		TimeSeriesOperationsClient ts = timeSeriesHelper.getTimeSeriesOperationsClient(getToken(), getHostName());
		log.info("timeSeriesOperationsClient object created successfully : {}");
		CreateOrUpdateTimeseriesRequest createOrUpdateTimeseriesRequest = new CreateOrUpdateTimeseriesRequest();
		createOrUpdateTimeseriesRequest.setTimeseries(timeSeries);
		MultiStatusError multiStatusError = ts.createOrUpdateTimeseries(createOrUpdateTimeseriesRequest);
		if (multiStatusError != null) {
			log.info("Getting error for createOrUpdateTimeseries"+multiStatusError);
			return multiStatusError;
		}
		else {
			log.info("Timeseries created successfully");
			return null;
		}
	}
	
	

	public String retrieveTimeseriesTest(String entityId, String propertySetName) throws MindsphereException {
		TimeSeriesOperationsClient ts = timeSeriesHelper.getTimeSeriesOperationsClient(getToken(), getHostName());
		log.info("timeSeriesOperationsClient object created successfully : {}");
		RetrieveTimeseriesRequest requestObject = new RetrieveTimeseriesRequest();
		requestObject.setEntityId(entityId);
		requestObject.setPropertySetName(propertySetName);
		List<TimeSeriesDataItem> response = ts.retrieveTimeseries(requestObject);
		if (response != null) {
			log.info("Getting response Successfully for retrieveTimeseries :" + response);
			return response.toString();
		} else {
			log.info("Getting null response for retrieveTimeseries");
			return null;
		}

	}

	public String retrieveTimeserieswithFromandToTest(String entityId, String propertySetName, String from, String to)
			throws MindsphereException {
		TimeSeriesOperationsClient ts = timeSeriesHelper.getTimeSeriesOperationsClient(getToken(), getHostName());
		log.info("timeSeriesOperationsClient object created successfully : {}");
		RetrieveTimeseriesRequest requestObject = new RetrieveTimeseriesRequest();
		requestObject.setEntityId(entityId);
		requestObject.setPropertySetName(propertySetName);
		requestObject.setFrom(from);
		requestObject.setTo(to);
		List<TimeSeriesDataItem> response = ts.retrieveTimeseries(requestObject);
		if (response != null) {
			log.info("Getting response Successfully for retrieveTimeserieswithFromandTo:" + response);
			return response.toString();
		} else {
			log.info("Getting null response for retrieveTimeserieswithFromandTo");
			return null;
		}

	}
	
	
	public String putOrUpdateTimeseriesData(String entityId, String propertySetName,List<TimeSeriesDataItem> timeSeriesDataItems) throws MindsphereException {
		TimeSeriesOperationsClient ts = timeSeriesHelper.getTimeSeriesOperationsClient(getToken(), getHostName());
		log.info("timeSeriesOperationsClient object created successfully : {}");
		CreateOrUpdateTimeseriesDataRequest requestObject = new CreateOrUpdateTimeseriesDataRequest();
		requestObject.setEntityId(entityId);
		requestObject.setPropertySetName(propertySetName);
		requestObject.setTimeseries(timeSeriesDataItems);
		ts.createOrUpdateTimeseriesData(requestObject);
		log.info("Timeseries created successfully");
		return "success";
	}
	

	public String createOrUpdateTimeseriesData(String entityId, String propertySetName) throws MindsphereException {
		TimeSeriesOperationsClient ts = timeSeriesHelper.getTimeSeriesOperationsClient(getToken(), getHostName());
		log.info("timeSeriesOperationsClient object created successfully : {}");
		CreateOrUpdateTimeseriesDataRequest requestObject = timeSeriesHelper.CreateOrUpdateTimeseriesDataRequest(entityId,propertySetName);
		ts.createOrUpdateTimeseriesData(requestObject);
		log.info("Timeseries created successfully");
		return "success";
	}

	public String deleteTimeserieswithFromandToTest(String entityId, String propertySetName, String from, String to) throws MindsphereException {
		TimeSeriesOperationsClient ts = timeSeriesHelper.getTimeSeriesOperationsClient(getToken(), getHostName());
		log.info("timeSeriesOperationsClient object created successfully : {}");
		DeleteUpdatedTimeseriesRequest requestObject = new DeleteUpdatedTimeseriesRequest();
		requestObject.setEntityId(entityId);
		requestObject.setPropertySetName(propertySetName);
		requestObject.setFrom(from);
		requestObject.setTo(to);
		ts.deleteTimeseries(requestObject);
		log.info("Timeseries deleted successfully");
		return "success";

		// deleteTimeseries api call
	}

	

}
