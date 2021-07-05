package com.siemens.mindsphere.model;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MsgBrokerSubscriptionResponse implements Serializable {

	@JsonProperty("URL")
	private String URL = null;

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	


}
