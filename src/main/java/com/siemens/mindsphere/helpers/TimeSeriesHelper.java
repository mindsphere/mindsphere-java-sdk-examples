package com.siemens.mindsphere.helpers;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.siemens.mindsphere.sdk.timeseries.apiclient.TimeSeriesClient;
import com.siemens.mindsphere.sdk.timeseries.model.DeleteTimeseriesRequest;
import com.siemens.mindsphere.sdk.timeseries.model.GetTimeseriesRequest;
import com.siemens.mindsphere.sdk.timeseries.model.PutTimeseriesRequest;
import com.siemens.mindsphere.sdk.timeseries.model.Timeseries;

public class TimeSeriesHelper extends ControllerHelper {

	public TimeSeriesClient getTimeseriesClient(String token, String host) {
		TimeSeriesClient timeseriesClient = TimeSeriesClient.builder().restClientConfig(getConfig(host))
				.mindsphereCredentials(getCreds(token)).build();

		return timeseriesClient;
	}

	public List<Timeseries> getTimeSeriesData() {
		List<Timeseries> timeseries = new ArrayList<>();
		Timeseries timeseriesData = new Timeseries();
		timeseriesData.setFields("FLWheel", "2");
		timeseriesData.setFields("FRWheel", "2");
		timeseriesData.setFields("RLWheel", "2");
		timeseriesData.setFields("RRWheel", "2");
		timeseriesData.setTime(getInstantTime());
		timeseries.add(timeseriesData);
		return timeseries;
	}

	public String getInstantTime() {
        String time = Instant.now().toString();
        time = time.substring(0, 23);
        time = time+"Z";
        return time;
     }

    public GetTimeseriesRequest getTimeseriesObject(String entityId, String propertySetName, String from, String to,
            int limit, String select) {
        GetTimeseriesRequest getTimeseriesRequest = new GetTimeseriesRequest();
        getTimeseriesRequest.setEntity(entityId);
        getTimeseriesRequest.setFrom(from);
        getTimeseriesRequest.setTo(to);
        getTimeseriesRequest.setLimit(limit);
        getTimeseriesRequest.setPropertysetname(propertySetName);
        getTimeseriesRequest.setSelect(select);
        return getTimeseriesRequest;
    }

    public PutTimeseriesRequest putTimeseriesObject(String entityId,String propertySetName,List<Timeseries> timeseriesData) {
        PutTimeseriesRequest putTimeseriesRequest = new PutTimeseriesRequest();
        putTimeseriesRequest.setEntity(entityId);
        putTimeseriesRequest.setPropertysetname(propertySetName);
        putTimeseriesRequest.setTimeseries(timeseriesData);
        return putTimeseriesRequest;
    }
    
    public PutTimeseriesRequest putTimeseriesObjectWithException(String entityId,String propertySetName,List<Timeseries> timeseriesData) {
        PutTimeseriesRequest putTimeseriesRequest = new PutTimeseriesRequest();
        putTimeseriesRequest.setEntity(entityId);
        //putTimeseriesRequest.setPropertysetname(propertySetName);
        putTimeseriesRequest.setTimeseries(timeseriesData);
        return putTimeseriesRequest;
    }

    public DeleteTimeseriesRequest deleteTimeseriesObject(String entity, String propertySetName, String from,
            String to) {
        DeleteTimeseriesRequest deleteTimeseriesRequest = new DeleteTimeseriesRequest();
        deleteTimeseriesRequest.setEntity(entity);
        deleteTimeseriesRequest.setPropertysetname(propertySetName);
        deleteTimeseriesRequest.setFrom(from);
        deleteTimeseriesRequest.setTo(to);
        return deleteTimeseriesRequest;
    }
}
