package com.siemens.mindsphere.services;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.siemens.mindsphere.helpers.EventManagementHelper;
import com.siemens.mindsphere.sdk.core.exception.MindsphereException;
import com.siemens.mindsphere.sdk.eventmanagement.apiclient.EventTypesClient;
import com.siemens.mindsphere.sdk.eventmanagement.apiclient.EventsClient;
import com.siemens.mindsphere.sdk.eventmanagement.model.CustomEvent;
import com.siemens.mindsphere.sdk.eventmanagement.model.EventType;
import com.siemens.mindsphere.sdk.eventmanagement.model.EventType.ScopeEnum;
import com.siemens.mindsphere.sdk.eventmanagement.model.Field;
import com.siemens.mindsphere.sdk.eventmanagement.model.Field.TypeEnum;

@Service
public class EventManagementService extends MindsphereService {

    EventManagementHelper eventsHelper = new EventManagementHelper();

    public Object createtEventType(String token) throws MindsphereException, IOException {
        Object response = null;
        EventTypesClient eventTypeClient = eventsHelper.getEventsTypeClient(getToken(), getHostName());
        String eventTypeName = "IntegTestCustomEvent" + new Random().nextInt(10000 - 10) + 10;
        EventType eventType = new EventType();
        eventType.setName(eventTypeName);
        eventType.setTtl(10);
        eventType.setScope(ScopeEnum.LOCAL);

        Field field = new Field();
        field.setName("resource");
        field.setFilterable(Boolean.TRUE);
        field.required(Boolean.TRUE);
        field.setType(TypeEnum.BOOLEAN);

        Field field2 = new Field();
        field2.setName("resource2");
        field2.setFilterable(Boolean.TRUE);
        field2.required(Boolean.TRUE);
        field2.setType(TypeEnum.INTEGER);

        Field field3 = new Field();
        field3.setName("resource3");
        field3.setFilterable(Boolean.TRUE);
        field3.required(Boolean.TRUE);
        field3.setType(TypeEnum.DOUBLE);

        Field field4 = new Field();
        field4.setName("resource4");
        field4.setFilterable(Boolean.TRUE);
        field4.required(Boolean.TRUE);
        field4.setType(TypeEnum.STRING);
        field4.setUpdatable(Boolean.TRUE);

        Field field5 = new Field();
        field5.setName("resource5");
        field5.setFilterable(Boolean.FALSE);
        field5.required(Boolean.TRUE);
        field5.setType(TypeEnum.LINK);
        field5.setUpdatable(Boolean.TRUE);

        Field field6 = new Field();
        field6.setName("resource6");
        field6.setFilterable(Boolean.TRUE);
        field6.required(Boolean.TRUE);
        field6.setType(TypeEnum.TIMESTAMP);
        field6.setUpdatable(Boolean.TRUE);

        Field field7 = new Field();
        field7.setName("resource7");
        field7.setFilterable(Boolean.TRUE);
        field7.required(Boolean.TRUE);
        field7.setType(TypeEnum.UUID);
        field7.setUpdatable(Boolean.TRUE);

        Field field8 = new Field();
        field8.setName("resource8");
        field8.setFilterable(Boolean.TRUE);
        field8.required(Boolean.TRUE);
        field8.setType(TypeEnum.ENUM);
        field8.setUpdatable(Boolean.TRUE);

        String[] values = new String[] { "hello", "hi", "welcome" };
        field8.setValues(values);

        List<Field> fieldList = new ArrayList<>();
        fieldList.add(field);
        fieldList.add(field2);
        fieldList.add(field3);
        fieldList.add(field4);
        fieldList.add(field5);
        fieldList.add(field6);
        fieldList.add(field7);
        fieldList.add(field8);

        eventType.setFields(fieldList);

        try {
            EventType createdEventType = eventTypeClient.createEventType(eventType);
            response = createdEventType;
        } catch (MindsphereException e) {
            response = "Error cause " + e.getErrorMessage();
            System.out.println(response);
        }
        return response;
    }

    public Object createCustomEvent(String eventTypeId, String entityId, String token)
            throws MindsphereException, IOException {
        // Create Event
        Object response = null;
        CustomEvent customEvent = new CustomEvent();
        customEvent.setTypeId(eventTypeId);

        customEvent.setEntityId(entityId);
        customEvent.setTimestamp(Instant.now().toString());

        Map<String, Object> fields = new HashMap<>();
        fields.put("resource", Boolean.TRUE);
        fields.put("resource2", 1);
        fields.put("resource3", 1.5);
        fields.put("resource4", 'i');
        fields.put("resource5", "https://developer.mindsphere.io");
        fields.put("resource6", "2020-01-21T00:00:00.000Z");
        fields.put("resource7", "cded7b90-d503-4778-b698-18d178f409e9");
        fields.put("resource8", "hi");

        customEvent.setFields(fields);
        EventsClient eventClient = eventsHelper.getEventsClient(getToken(), getHostName());
        CustomEvent createdCustomEvent = null;
        try {
            createdCustomEvent = eventClient.createCustomEvent(customEvent);
            response = createdCustomEvent;
        } catch (MindsphereException ex) {
            response = "Error cause : " + ex.getErrorMessage();
        }
        return response;
    }
}
