package com.dev.phishfinder.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Year {
	
	private String id;	// id should be year? String

	//private String year;
	
	@JsonAlias("data")
	private List<String> years;
	
	//@ElementCollection()
	//@JsonAlias("data")
	//@OneToMany(cascade=CascadeType.ALL)
	//@JoinColumn(name="yearId")
	//private List<Show> shows;
	//private Date updatedAt;
	
	public Year() {
	}
	
	public Year(List<String> years) {
		super();
		this.years = years;
		//this.id = year;
	}
                     
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

//	public String getYear() {
//		return year;
//	}
//	public void setYear(String year) {
//		this.year = year;
//	}
	
//	public List<Show> getShows() {
//		return shows;
//	}
//
//	public void setShows(List<Show> shows) {
//		this.shows = shows;
//	}
	
	@JsonProperty("data")
	public List<String> getYears() {
		return years;
	}

	public void setYears(List<String> years) {
		this.years = years;
	}

	@Override
	public String toString() {
		return "Year [id=" + id + ", years=" + years + "]";
	}
	
}
