package com.siemens.mindsphere.helpers;

import java.math.BigDecimal;

import com.siemens.mindsphere.sdk.tsaggregates.apiclient.AggregatesClient;
import com.siemens.mindsphere.sdk.tsaggregates.model.GetAggregateTimeseriesRequest;


public class TimeseriesAggregateHelper extends ControllerHelper {

    public AggregatesClient getTsAggregatesClient(String token, String host) {
        AggregatesClient aggreagatesClient = AggregatesClient.builder().restClientConfig(getConfig(host))
                .mindsphereCredentials(getCreds(token)).build();
        return aggreagatesClient;
    }

    public GetAggregateTimeseriesRequest getAggregateHelper(String entityId, String propertySetName,String from, String to,BigDecimal intervalValue,String intervalUnit,String select) {
        GetAggregateTimeseriesRequest getAggregateTimeseriesRequest = new GetAggregateTimeseriesRequest();
        getAggregateTimeseriesRequest.setEntity(entityId);
        getAggregateTimeseriesRequest.setPropertyset(propertySetName);
        getAggregateTimeseriesRequest.setFrom(from);
        getAggregateTimeseriesRequest.setTo(to);
        getAggregateTimeseriesRequest.setIntervalUnit(intervalUnit);
        getAggregateTimeseriesRequest.setIntervalValue(intervalValue);
        getAggregateTimeseriesRequest.setSelect(select);
        return getAggregateTimeseriesRequest;
    }
}
