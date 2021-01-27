package com.siemens.mindsphere.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.siemens.mindsphere.helpers.TimeseriesAggregateHelper;
import com.siemens.mindsphere.sdk.core.exception.MindsphereException;
import com.siemens.mindsphere.sdk.tsaggregates.apiclient.AggregatesClient;
import com.siemens.mindsphere.sdk.tsaggregates.model.Aggregates;

@Service
public class TimeseriesAggregateService extends MindsphereService {

    private TimeseriesAggregateHelper timeseriesAggregateHelper = new TimeseriesAggregateHelper();

    public List<Aggregates> getTimeseriesAggregate(String entityId, String propertySetName, String from, String to,
            BigDecimal intervalValue, String intervalUnit, String select) throws MindsphereException {
        AggregatesClient aggreagatesClient = timeseriesAggregateHelper.getTsAggregatesClient(getToken(), getHostName());
        List<Aggregates> aggregateResponse = aggreagatesClient.getAggregateTimeseries(timeseriesAggregateHelper
                .getAggregateHelper(entityId, propertySetName, from, to, intervalValue, intervalUnit, select));
        return aggregateResponse;
    }

}
