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
import com.siemens.mindsphere.sdk.timeseries.model.TimeSeriesDataItem;

@Service
public class UpdatedTimeseriesService extends MindsphereService {

	private UpdatedTimeSeriesHelper timeSeriesHelper = new UpdatedTimeSeriesHelper();
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	public String createOrUpdateTimeseries() throws MindsphereException {
		TimeSeriesOperationsClient ts = timeSeriesHelper.getTimeSeriesOperationsClient(getToken(), getHostName());

		CreateOrUpdateTimeseriesRequest createOrUpdateTimeseriesRequest = timeSeriesHelper
				.CreateOrUpdateTimeseriesRequest();
		MultiStatusError multiStatusError = ts.createOrUpdateTimeseries(createOrUpdateTimeseriesRequest);
		if (multiStatusError != null)
			return multiStatusError.toString();
		else
			return "created successfully";
	}

	public String retrieveTimeseriesTest(String entityId, String propertySetName) throws MindsphereException {
		TimeSeriesOperationsClient ts = timeSeriesHelper.getTimeSeriesOperationsClient(getToken(), getHostName());

		RetrieveTimeseriesRequest requestObject = new RetrieveTimeseriesRequest();
		requestObject.setEntityId(entityId);
		requestObject.setPropertySetName(propertySetName);
		List<TimeSeriesDataItem> response = ts.retrieveTimeseries(requestObject);
		if (response != null)
			return response.toString();
		else
			return null;

	}

	public String retrieveTimeserieswithFromandToTest(String entityId, String propertySetName, String from, String to)
			throws MindsphereException {
		TimeSeriesOperationsClient ts = timeSeriesHelper.getTimeSeriesOperationsClient(getToken(), getHostName());

		RetrieveTimeseriesRequest requestObject = new RetrieveTimeseriesRequest();
		requestObject.setEntityId(entityId);
		requestObject.setPropertySetName(propertySetName);
		requestObject.setFrom(from);
		requestObject.setTo(to);
		List<TimeSeriesDataItem> response = ts.retrieveTimeseries(requestObject);
		if (response != null)
			return response.toString();
		else
			return null;

	}

	public String createOrUpdateTimeseriesData(String entityId, String propertySetName) throws MindsphereException {
		TimeSeriesOperationsClient ts = timeSeriesHelper.getTimeSeriesOperationsClient(getToken(), getHostName());
		CreateOrUpdateTimeseriesDataRequest requestObject = timeSeriesHelper.CreateOrUpdateTimeseriesDataRequest(entityId,propertySetName);
		ts.createOrUpdateTimeseriesData(requestObject);
		return "success";
	}

	public String deleteTimeserieswithFromandToTest(String entityId, String propertySetName, String from, String to) throws MindsphereException {
		TimeSeriesOperationsClient ts = timeSeriesHelper.getTimeSeriesOperationsClient(getToken(), getHostName());

		DeleteUpdatedTimeseriesRequest requestObject = new DeleteUpdatedTimeseriesRequest();
		requestObject.setEntityId(entityId);
		requestObject.setPropertySetName(propertySetName);
		requestObject.setFrom(from);
		requestObject.setTo(to);
		ts.deleteTimeseries(requestObject);
		return "success";

		// deleteTimeseries api call
	}

	

}
