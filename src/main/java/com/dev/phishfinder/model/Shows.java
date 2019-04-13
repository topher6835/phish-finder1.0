package com.dev.phishfinder.model;

import java.util.List;

import com.dev.phishfinder.model.Show;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Shows {
	
	private String yearId;
	
	@JsonProperty("data")
	private List<Show> showsList;
	
	public Shows() {
	}
	
	public Shows(String yearId) {
		this.yearId = yearId;
	}

	public String getYearId() {
		return yearId;
	}

	public void setYearId(String yearId) {
		this.yearId = yearId;
	}

	@JsonProperty("data")
	public List<Show> getShowsList() {
		return showsList;
	}

	public void setShowsList(List<Show> showsList) {
		this.showsList = showsList;
	}

	@Override
	public String toString() {
		return "Shows [yearId=" + yearId + ", showsList=" + showsList + "]";
	}

}
