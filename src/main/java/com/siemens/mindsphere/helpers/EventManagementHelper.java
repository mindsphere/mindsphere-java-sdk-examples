package com.siemens.mindsphere.helpers;

import com.siemens.mindsphere.sdk.eventmanagement.apiclient.EventTypesClient;
import com.siemens.mindsphere.sdk.eventmanagement.apiclient.EventsClient;

public class EventManagementHelper  extends ControllerHelper{

    public EventTypesClient getEventsTypeClient(String token, String host) {
        EventTypesClient eventsClient = EventTypesClient.builder().restClientConfig(getConfig(host))
                .mindsphereCredentials(getCreds(token)).build();

        return eventsClient;
    }
    
    public EventsClient getEventsClient(String token, String host) {
        EventsClient eventsClient = EventsClient.builder().restClientConfig(getConfig(host))
                .mindsphereCredentials(getCreds(token)).build();
        return eventsClient;
    }
}
