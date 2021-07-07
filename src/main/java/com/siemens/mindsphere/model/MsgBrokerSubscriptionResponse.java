package com.siemens.mindsphere.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MsgBrokerSubscriptionResponse implements Serializable {

	private String URL = null;

	@JsonProperty(value = "URL")
	public String getUrlvalue() {
		return URL;
	}

	public void setUrlvalue(String urlvalue) {
		URL = urlvalue;
	}

	@Override
	public String toString() {
		return "MsgBrokerSubscriptionResponse [Urlvalue=" + URL + "]";
	}

	


}
