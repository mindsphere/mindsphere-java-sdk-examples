package com.siemens.mindsphere.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.siemens.mindsphere.helpers.EventAnalyticsHelper;
import com.siemens.mindsphere.sdk.core.exception.MindsphereException;
import com.siemens.mindsphere.sdk.eventanalytics.apiclient.EventOperationsClient;
import com.siemens.mindsphere.sdk.eventanalytics.apiclient.PatternOperationsClient;
import com.siemens.mindsphere.sdk.eventanalytics.model.CountEventsRequest;
import com.siemens.mindsphere.sdk.eventanalytics.model.DuplicateEventArrayOutput;
import com.siemens.mindsphere.sdk.eventanalytics.model.Event;
import com.siemens.mindsphere.sdk.eventanalytics.model.EventArrayOutput;
import com.siemens.mindsphere.sdk.eventanalytics.model.EventCountOutput;
import com.siemens.mindsphere.sdk.eventanalytics.model.EventCountOutputItem;
import com.siemens.mindsphere.sdk.eventanalytics.model.EventInput;
import com.siemens.mindsphere.sdk.eventanalytics.model.EventInputEventsMetadata;
import com.siemens.mindsphere.sdk.eventanalytics.model.EventSearchInputDataModel;
import com.siemens.mindsphere.sdk.eventanalytics.model.EventsInputModel;
import com.siemens.mindsphere.sdk.eventanalytics.model.EventsInputModelEventsMetadata;
import com.siemens.mindsphere.sdk.eventanalytics.model.FilterEventsRequest;
import com.siemens.mindsphere.sdk.eventanalytics.model.MatchPatternsOverEventsRequest;
import com.siemens.mindsphere.sdk.eventanalytics.model.PatternDefinition;
import com.siemens.mindsphere.sdk.eventanalytics.model.PatternMatchingInputDataModel;
import com.siemens.mindsphere.sdk.eventanalytics.model.PatternMatchingOutput;
import com.siemens.mindsphere.sdk.eventanalytics.model.RemoveDuplicateEventsRequest;
import com.siemens.mindsphere.sdk.eventanalytics.model.TopEventOutput;
import com.siemens.mindsphere.sdk.eventanalytics.model.TopEventOutputInner;
import com.siemens.mindsphere.sdk.eventanalytics.model.TopEventsInputDataModel;
import com.siemens.mindsphere.sdk.eventanalytics.model.TopEventsRequest;

@Service
public class EventAnalyticsService extends MindsphereService {

    EventAnalyticsHelper eventAnalyticsHelper = new EventAnalyticsHelper();

    public String findEvents() throws MindsphereException {
        EventOperationsClient eventOperationsClient = eventAnalyticsHelper.getEventAnalyticsClient(getToken(),
                getHostName());
        TopEventsInputDataModel data = getTopEventData();
        EventSearchInputDataModel data1 = getEventFilterData();

        String output = null;
        TopEventOutput topEventsResponse = null;
        EventArrayOutput filterEventsResponse = null;

        // topEvents API call
        topEventsResponse = eventOperationsClient.topEvents(eventAnalyticsHelper.topEvents(data));
        TopEventOutputInner topEventResponse = topEventsResponse.get(0);

        // filter events API call
        filterEventsResponse = eventOperationsClient.filterEvents(eventAnalyticsHelper.filterEventsObjectModel(data1));
        List<Event> filterEventList = filterEventsResponse.getOutput();

        output = getSelectedOutput(topEventResponse, filterEventList);
        return output;
    }

    public String countEvent() throws MindsphereException {
        String output = null;
        EventOperationsClient eventOperationsClient = eventAnalyticsHelper.getEventAnalyticsClient(getToken(),
                getHostName());
        EventInput data = getCountData();

        EventCountOutput countEventsResponse = null;
        // countEvents API call
        countEventsResponse = eventOperationsClient.countEvents(eventAnalyticsHelper.countEventsObjectModel(data));
        List<EventCountOutputItem> eventCountOutputItem = countEventsResponse.getOutput();

        output = getCountOutput(eventCountOutputItem);
        return output;

    }

    public String removeDuplicateEvent() throws MindsphereException {
        String output = null;
        EventOperationsClient eventOperationsClient = eventAnalyticsHelper.getEventAnalyticsClient(getToken(),
                getHostName());
        EventInput data = getRemoveDuplicateData();

        DuplicateEventArrayOutput removeDuplicateResponse = null;
        // removeDuplicateEvents API call
        removeDuplicateResponse = eventOperationsClient.removeDuplicateEvents(eventAnalyticsHelper.removeDuplicatesModel(data));
        output = getRemoveDuplicateOutput(removeDuplicateResponse);
        return output;
    }

    public PatternMatchingOutput matchEventPattern() throws MindsphereException {
        PatternMatchingOutput output = null;
        PatternOperationsClient patternOperationsClient = eventAnalyticsHelper.getPatternOperationsClient(getToken(),
                getHostName());
        PatternMatchingInputDataModel data = getPatternData();
        PatternMatchingOutput matchPatternResponse = null;
        // matchPatternsOverEvents API call
        matchPatternResponse = patternOperationsClient.matchPatternsOverEvents(eventAnalyticsHelper.matchPatternsOverEventsRequest(data));
        output = getmatchPatternOutput(matchPatternResponse);
        return output;
    }

    private PatternMatchingOutput getmatchPatternOutput(PatternMatchingOutput matchPatternResponse) {
        return matchPatternResponse;
    }

    private TopEventsInputDataModel getTopEventData() {
        TopEventsInputDataModel data = new TopEventsInputDataModel();
        List<Event> events = eventAnalyticsHelper.getEventsList();
        data.setEvents(events);
        EventsInputModelEventsMetadata eventsMetadata = new EventsInputModelEventsMetadata();
        eventsMetadata.setEventTextPropertyName("text");
        data.setEventsMetadata(eventsMetadata);
        Integer numberOfTopPositionsRequired = 12;
        data.setNumberOfTopPositionsRequired(numberOfTopPositionsRequired);
        return data;
    }

    private EventSearchInputDataModel getEventFilterData() {
        EventSearchInputDataModel data = new EventSearchInputDataModel();

        List<Event> events = eventAnalyticsHelper.getEventsList();
        data.setEvents(events);
        EventsInputModelEventsMetadata eventsMetadata = new EventsInputModelEventsMetadata();
        eventsMetadata.setEventTextPropertyName("text");
        data.setEventsMetadata(eventsMetadata);
        List<String> filterList = new ArrayList<>();
        filterList.add("Introduction fuel");
        filterList.add("Downloading the module database causes module 11 restart");
        data.setFilterList(filterList);
        return data;
    }

    private EventInput getCountData() {
        EventInput data = new EventInput();
        List<Event> events = eventAnalyticsHelper.getEventsList();
        data.setEvents(events);
        EventInputEventsMetadata eventsMetadata = new EventInputEventsMetadata();
        String eventTextPropertyName = "text";
        eventsMetadata.setEventTextPropertyName(eventTextPropertyName);
        Integer splitInterval = 5000;
        eventsMetadata.setSplitInterval(splitInterval);
        data.setEventsMetadata(eventsMetadata);
        return data;
    }

    private EventInput getRemoveDuplicateData() {
        EventInput data = new EventInput();
        List<Event> events = eventAnalyticsHelper.getEventsList();
        data.setEvents(events);
        EventInputEventsMetadata eventsMetadata = new EventInputEventsMetadata();
        eventsMetadata.setEventTextPropertyName("text");
        eventsMetadata.setSplitInterval(5000);
        data.setEventsMetadata(eventsMetadata);
        return data;
    }

    private PatternMatchingInputDataModel getPatternData() {
        PatternMatchingInputDataModel data = new PatternMatchingInputDataModel();
        EventsInputModel eventsInput = new EventsInputModel();
        List<Event> events = eventAnalyticsHelper.getEventsList1();
        eventsInput.setEvents(events);
        EventsInputModelEventsMetadata eventsMetadata = new EventsInputModelEventsMetadata();
        eventsMetadata.setEventTextPropertyName("text");
        eventsInput.setEventsMetadata(eventsMetadata);
        data.setEventsInput(eventsInput);
        Integer maxPatternInterval = 200000;
        data.setMaxPatternInterval(maxPatternInterval);

        List<String> nonEvents = new ArrayList<>();
        nonEvents.add("Error 2.. occurred");
        nonEvents.add("STOPPING ENGINE");
        data.setNonEvents(nonEvents);
        List<PatternDefinition> patternsList = eventAnalyticsHelper.getEventPatterns();
        data.setPatternsList(patternsList);
        data.setMaxPatternInterval(200000);
        return data;
    }

    private String getSelectedOutput(TopEventOutputInner topEventResponse, List<Event> filterEventList) {
        String filterEvent = "";
        for (Event event : filterEventList) {
            filterEvent = filterEvent + "time : " + event.getTime();
            filterEvent = filterEvent + " text : " + event.getText();
            filterEvent = filterEvent + " textQc : " + event.getTextQc();
        }

        String output = "TOP EVENTS :: " + "Appearances : " + topEventResponse.getAppearances() + " " + "Text : "
                + topEventResponse.getText() + " \nFILTERED EVENTS :: " + filterEvent;
        return output;
    }

    private String getCountOutput(List<EventCountOutputItem> eventCountOutputItem) {

        String output = "";
        for (EventCountOutputItem item : eventCountOutputItem) {
            output = output + "StartTime : " + item.getStartTime();
            output = output + " EndTime : " + item.getEndTime();
            output = output + " EventCount : " + item.getEventCount();
        }
        return output;
    }

    private String getRemoveDuplicateOutput(DuplicateEventArrayOutput removeDuplicateResponse) {
        List<Event> eventList = removeDuplicateResponse.getOutput();
        String output = "";
        for (Event event : eventList) {
            output = output + "Time : " + event.getTime();
            output = output + " text : " + event.getText();
            output = output + " textQc : " + event.getTextQc();
        }
        return output;
    }

}
