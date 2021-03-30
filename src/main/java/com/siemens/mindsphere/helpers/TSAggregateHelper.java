package com.siemens.mindsphere.helpers;

import com.siemens.mindsphere.sdk.tsaggregates.apiclient.AggregatesClientV4;

public class TSAggregateHelper extends ControllerHelper {

	public AggregatesClientV4 getAggregatesClientV4(String token, String host) {
		AggregatesClientV4 timeseriesClient = AggregatesClientV4.builder()
				.restClientConfig(getConfig(host)).mindsphereCredentials(getCreds(token)).build();

		return timeseriesClient;
	}

}
