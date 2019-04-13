package com.dev.phishfinder.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Years {
	
	//@JsonProperty("status")
	//private int statusCode;

	@JsonProperty("data")
	private List<String> yearsList;
	
	public Years() {
	}

	public List<String> getYearsList() {
		return yearsList;
	}

	public void setYearsList(List<String> yearsList) {
		this.yearsList = yearsList;
	}

//	public int getStatusCode() {
//		return statusCode;
//	}
//
//	public void setStatusCode(int statusCode) {
//		this.statusCode = statusCode;
//	}

	@Override
	public String toString() {
		return "Years [yearsList=" + yearsList + "]";
	}
	
}
