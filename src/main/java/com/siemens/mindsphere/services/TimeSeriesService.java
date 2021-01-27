package com.siemens.mindsphere.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.siemens.mindsphere.helpers.TimeSeriesHelper;
import com.siemens.mindsphere.sdk.core.exception.MindsphereException;
import com.siemens.mindsphere.sdk.timeseries.apiclient.TimeSeriesClient;
import com.siemens.mindsphere.sdk.timeseries.iterator.model.PageIterator;
import com.siemens.mindsphere.sdk.timeseries.iterator.model.TimeseriesPageIterator;
import com.siemens.mindsphere.sdk.timeseries.model.Timeseries;

@Service
public class TimeSeriesService extends MindsphereService {

    private TimeSeriesHelper timeSeriesHelper = new TimeSeriesHelper();
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public String createTimeSeriesData(String entityId, String propertySetName) throws MindsphereException {
        TimeSeriesClient timeseriesClient = timeSeriesHelper.getTimeseriesClient(getToken(), getHostName());

        List<Timeseries> timeseriesData = timeSeriesHelper.getTimeSeriesData();
        List<Timeseries> timeseriesResponseList = null;
        // putTimeseries API call
        timeseriesClient.putTimeseries(timeSeriesHelper.putTimeseriesObject(entityId, propertySetName, timeseriesData));
        LOGGER.info("successfully uploaded timeseries + time =" + timeSeriesHelper.getInstantTime());

        String output = timeSeriesHelper.getInstantTime().toString();

        return output;
    }
    
    public String createTimeSeriesDataWithException(String entityId, String propertySetName) throws MindsphereException {
        TimeSeriesClient timeseriesClient = timeSeriesHelper.getTimeseriesClient(getToken(), getHostName());

        List<Timeseries> timeseriesData = timeSeriesHelper.getTimeSeriesData();
        List<Timeseries> timeseriesResponseList = null;
        // putTimeseries API call
        timeseriesClient.putTimeseries(timeSeriesHelper.putTimeseriesObjectWithException(entityId, propertySetName, timeseriesData));
        LOGGER.info("successfully uploaded timeseries + time =" + timeSeriesHelper.getInstantTime());

        String output = timeSeriesHelper.getInstantTime().toString();

        return output;
    }

    public String deleteAssets(String entity, String propertySetName, String from, String to) {
        TimeSeriesClient timeseriesClient = timeSeriesHelper.getTimeseriesClient(getToken(), getHostName());
        String status;
        try {
            // deleteTimeseries API call
            timeseriesClient
                    .deleteTimeseries(timeSeriesHelper.deleteTimeseriesObject(entity, propertySetName, from, to));
            status = "successfully deleted timeseries";
        } catch (MindsphereException ex) {
            status = ex.getErrorMessage();
        }
        return status;
    }

    public String getNextTimeseries(String entityId, String propertySetName, String from, String to, String pageSize) throws MindsphereException {
        TimeSeriesClient timeseriesClient = timeSeriesHelper.getTimeseriesClient(getToken(), getHostName());
        PageIterator<List<Timeseries>> iterator = new TimeseriesPageIterator(timeseriesClient, entityId, propertySetName,
                from, to, Integer.parseInt(pageSize));
        System.out.println("Page Size is " + pageSize);
        return  iterator.next().toString();
    }

    public String getPage(String entityId, String propertySetName, String from, String to, String pageSize,String page) throws MindsphereException {
        TimeSeriesClient timeseriesClient = timeSeriesHelper.getTimeseriesClient(getToken(), getHostName());
        PageIterator<List<Timeseries>> iterator = new TimeseriesPageIterator(timeseriesClient, entityId, propertySetName,
                from, to, Integer.parseInt(pageSize));
        List<Timeseries> data = iterator.getPage(Integer.parseInt(page));
        System.out.println("Page Size is " + pageSize);
        System.out.println("Page is " + page);
        return  data.toString();
    }

}
