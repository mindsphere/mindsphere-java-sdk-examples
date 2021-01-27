package com.siemens.mindsphere.helpers;

import java.util.ArrayList;
import java.util.List;

import com.siemens.mindsphere.sdk.eventanalytics.apiclient.EventOperationsClient;
import com.siemens.mindsphere.sdk.eventanalytics.apiclient.PatternOperationsClient;
import com.siemens.mindsphere.sdk.eventanalytics.model.CountEventsRequest;
import com.siemens.mindsphere.sdk.eventanalytics.model.Event;
import com.siemens.mindsphere.sdk.eventanalytics.model.EventInput;
import com.siemens.mindsphere.sdk.eventanalytics.model.EventSearchInputDataModel;
import com.siemens.mindsphere.sdk.eventanalytics.model.FilterEventsRequest;
import com.siemens.mindsphere.sdk.eventanalytics.model.MatchPatternsOverEventsRequest;
import com.siemens.mindsphere.sdk.eventanalytics.model.MatchingPattern;
import com.siemens.mindsphere.sdk.eventanalytics.model.PatternDefinition;
import com.siemens.mindsphere.sdk.eventanalytics.model.PatternMatchingInputDataModel;
import com.siemens.mindsphere.sdk.eventanalytics.model.RemoveDuplicateEventsRequest;
import com.siemens.mindsphere.sdk.eventanalytics.model.TopEventsInputDataModel;
import com.siemens.mindsphere.sdk.eventanalytics.model.TopEventsRequest;

public class EventAnalyticsHelper extends ControllerHelper {

	public EventOperationsClient getEventAnalyticsClient(String token, String host) {
		EventOperationsClient eventOperationsClient = EventOperationsClient.builder().restClientConfig(getConfig(host))
				.mindsphereCredentials(getCreds(token)).build();

		return eventOperationsClient;
	}

	public PatternOperationsClient getPatternOperationsClient(String token, String host) {

		PatternOperationsClient patternOperationsClient = PatternOperationsClient.builder()
				.restClientConfig(getConfig(host)).mindsphereCredentials(getCreds(token)).build();

		return patternOperationsClient;
	}

	public List<Event> getEventsList() {
		List<Event> events = new ArrayList<>();

		Event event1 = new Event();
		event1.setText("Downloading the module database causes module 11 restart");
		event1.setTextQc(0);
		event1.setTime("2017-10-01T12:00:00.001Z");
		events.add(event1);

		Event event2 = new Event();
		event2.setText("The direction for forwarding the time of day is recognized automatically by the module");
		event2.setTextQc(0);
		event2.setTime("2017-10-01T12:00:01.001Z");
		events.add(event2);

		Event event3 = new Event();
		event3.setText("Status@Flame On");
		event3.setTextQc(0);
		event3.setTime("2017-10-01T12:00:02.001Z");
		events.add(event3);

		Event event4 = new Event();
		event4.setText("Downloading the module database causes module 11 restart");
		event4.setTextQc(0);
		event4.setTime("2017-10-01T12:00:00.001Z");
		events.add(event4);

		Event event5 = new Event();
		event5.setText("The direction for forwarding the time of day is recognized automatically by the module");
		event5.setTextQc(0);
		event5.setTime("2017-10-01T12:00:01.001Z");
		events.add(event5);

		Event event6 = new Event();
		event6.setText("Status@Flame On");
		event6.setTextQc(0);
		event6.setTime("2017-10-01T12:00:02.001Z");
		events.add(event6);
		return events;
	}

	public List<Event> getEventsList1() {
		List<Event> events = new ArrayList<>();

		Event event1 = new Event();
		event1.setText("Downloading the module database causes module 11 restart");
		event1.setTextQc(0);
		event1.setTime("2017-10-01T12:00:00.001Z");
		events.add(event1);

		Event event2 = new Event();
		event2.setText("The direction for forwarding the time of day is recognized automatically by the module");
		event2.setTextQc(0);
		event2.setTime("2017-10-01T12:00:01.001Z");
		events.add(event2);

		Event event3 = new Event();
		event3.setText("Status@Flame On");
		event3.setTextQc(0);
		event3.setTime("2017-10-01T12:00:02.001Z");
		events.add(event3);

		Event event4 = new Event();
		event4.setText("The SIMATIC mode was selected for time-of-day synchronization of the module with Id: 33");
		event4.setTextQc(0);
		event4.setTime("2017-10-01T12:00:03.001Z");
		events.add(event4);

		Event event5 = new Event();
		event5.setText("INTRODUCING FUEL");
		event5.setTextQc(0);
		event5.setTime("2017-10-01T12:00:06.001Z");
		events.add(event5);

		Event event6 = new Event();
		event6.setText("Module STOP due to parameter assignment");
		event6.setTextQc(0);
		event6.setTime("2017-10-01T12:00:09.001Z");
		events.add(event6);
		return events;
	}

	public List<PatternDefinition> getEventPatterns() {
		List<PatternDefinition> patternDefinitionList = new ArrayList<>();
		PatternDefinition patternDefinition = new PatternDefinition();
		List<MatchingPattern> pattern = new ArrayList<>();

		MatchingPattern matchingPattern1 = new MatchingPattern();
		matchingPattern1.setEventText("INTRODUCING FUEL");
		matchingPattern1.setMaxRepetitions(2);
		matchingPattern1.setMinRepetitions(1);
		pattern.add(matchingPattern1);

		MatchingPattern matchingPattern2 = new MatchingPattern();
		matchingPattern2.setEventText("Status@Flame On");
		matchingPattern2.setMaxRepetitions(1);
		matchingPattern2.setMinRepetitions(0);
		patternDefinition.setPattern(pattern);
		pattern.add(matchingPattern2);

		MatchingPattern matchingPattern3 = new MatchingPattern();
		matchingPattern3.setEventText("Module STOP due to parameter assignment");
		matchingPattern3.setMaxRepetitions(1);
		matchingPattern3.setMinRepetitions(1);
		pattern.add(matchingPattern3);
		patternDefinition.setPattern(pattern);

		PatternDefinition patternDefinition1 = new PatternDefinition();
		List<MatchingPattern> pattern1 = new ArrayList<>();
		MatchingPattern matchingPattern4 = new MatchingPattern();
		matchingPattern4.setEventText("Downloading the module database causes module .. restart");
		matchingPattern4.setMaxRepetitions(1);
		matchingPattern4.setMinRepetitions(1);
		pattern1.add(matchingPattern4);

		MatchingPattern matchingPattern5 = new MatchingPattern();
		matchingPattern5.setEventText(
				"The SIMATIC mode was selected for time-of-day synchronization of the module with Id: ..");
		matchingPattern5.setMaxRepetitions(1);
		matchingPattern5.setMinRepetitions(1);
		pattern1.add(matchingPattern5);

		patternDefinition.setPattern(pattern);
		patternDefinition1.setPattern(pattern1);

		patternDefinitionList.add(patternDefinition);
		patternDefinitionList.add(patternDefinition1);
		return patternDefinitionList;
	}

    public TopEventsRequest topEvents(TopEventsInputDataModel data) {
        TopEventsRequest topEventsRequest = new TopEventsRequest();
        topEventsRequest.setData(data);
        return topEventsRequest;
    }

    public FilterEventsRequest filterEventsObjectModel(EventSearchInputDataModel data1) {
        FilterEventsRequest filterEventsRequest = new FilterEventsRequest();
        filterEventsRequest.setData(data1);
        return filterEventsRequest;
    }

    public CountEventsRequest countEventsObjectModel(EventInput data) {
        CountEventsRequest countEventsRequest = new CountEventsRequest();
        countEventsRequest.setData(data);
        return countEventsRequest;
    }

    public RemoveDuplicateEventsRequest removeDuplicatesModel(EventInput data) {
        RemoveDuplicateEventsRequest removeDuplicateEventsRequest = new RemoveDuplicateEventsRequest();
        removeDuplicateEventsRequest.setData(data);
        return removeDuplicateEventsRequest;
    }

    public MatchPatternsOverEventsRequest matchPatternsOverEventsRequest(PatternMatchingInputDataModel data) {
        MatchPatternsOverEventsRequest matchPatternsOverEventsRequest = new MatchPatternsOverEventsRequest();
        matchPatternsOverEventsRequest.setData(data);
        return matchPatternsOverEventsRequest;
    }

}
