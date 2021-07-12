package com.siemens.mindsphere.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.siemens.mindsphere.helpers.TSAggregateHelper;
import com.siemens.mindsphere.sdk.core.exception.MindsphereException;
import com.siemens.mindsphere.sdk.tsaggregates.apiclient.AggregatesClientV4;
import com.siemens.mindsphere.sdk.tsaggregates.model.AggregatesResponse;
import com.siemens.mindsphere.sdk.tsaggregates.model.AggregatesV4;
import com.siemens.mindsphere.sdk.tsaggregates.model.RetrieveAggregatesRequest;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TSAggregateService extends MindsphereService {

	TSAggregateHelper tsAggregateHelper = new TSAggregateHelper();

	public String retrieveTimeseriesTest(String entityId, String propertySetName) throws MindsphereException {

		AggregatesClientV4 aggregatesClientV4 = tsAggregateHelper.getAggregatesClientV4(getToken(), getHostName());

		RetrieveAggregatesRequest request = new RetrieveAggregatesRequest();
		request.setAssetId(entityId);
		request.setAspectName(propertySetName);
		AggregatesResponse aggregate = aggregatesClientV4.retrieveAggregates(request);
		List<AggregatesV4> aggregateList = aggregate.getAggregates();
		if (aggregateList != null) {
			log.info("Getting response Successfully for retrieveTimeseries :" + aggregateList);
			return aggregateList.toString();
		} else {
			log.info("Getting null response for retrieveTimeseries");
			return null;
		}

	}

	public String getAggregateTimeseriesWithselect(String entityId, String propertySetName, String from, String to)
			throws MindsphereException {
		AggregatesClientV4 aggregatesClientV4 = tsAggregateHelper.getAggregatesClientV4(getToken(), getHostName());

		RetrieveAggregatesRequest request = new RetrieveAggregatesRequest();
		request.setAssetId(entityId);
		request.setAspectName(propertySetName);
		request.setFrom(from);
		request.setTo(to);
		AggregatesResponse aggregate = aggregatesClientV4.retrieveAggregates(request);
		List<AggregatesV4> aggregateList = aggregate.getAggregates();
		if (aggregateList != null) {
			log.info("Getting response Successfully for getAggregateTimeseriesWithselect :" + aggregateList);
			return aggregateList.toString();
		} else {
			log.info("Getting null response for retrieveTimeseries");
			return null;
		}
	}

}
