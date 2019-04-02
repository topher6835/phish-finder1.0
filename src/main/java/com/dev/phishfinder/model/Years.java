package com.dev.phishfinder.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

//@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Years {
	
	//@Id
	@JsonAlias("data")
	private int id;
	
	//@ElementCollection()
	//@JsonAlias("data")
	private List<Year> yearsList;
	
	public Years() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@JsonProperty("data")
	public List<Year> getYears() {
		return yearsList;
	}

	public void setYears(List<Year> yearsList) {
		this.yearsList = yearsList;
	}

	@Override
	public String toString() {
		return "Years [id=" + id + ", years=" + yearsList + "]";
	}
	
}
