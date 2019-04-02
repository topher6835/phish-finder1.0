package com.dev.phishfinder.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ShowData {
	
	@JsonProperty("data")
	private Show show;

	public ShowData() {
	}

	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	@Override
	public String toString() {
		return "ShowData [show=" + show + "]";
	}

}
