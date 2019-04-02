package com.dev.phishfinder.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Show {

	@JsonProperty("id")
	private Long id;
	
	private String yearId;
	
	@JsonProperty("date")
	private String date;
	
	@JsonProperty("duration")
	private Long duration;
	
	@JsonProperty("tour_id")
	private int tour_id;
	
	@JsonProperty("venue_name")
	private String venueName;
	
	@JsonProperty("location")
	private String location;
	
	@JsonProperty("venue_id")
	private int venueId;
	
	@JsonProperty("updated_at")
	private Date updated_at;
	
	@JsonProperty("tracks")
	private List<Track> tracks;
	
	@JsonProperty("venue")
	private void unpackNameFromNestedObject(Map<String, Object> venue) {
		venueName = (String) venue.get("name");
		location = (String) venue.get("location");
		venueId = (int) venue.get("id");
	}
	
	public Show() {
	}
	
	public Show(Show newShow) {
		this.id = newShow.id;
		this.yearId = newShow.yearId;
		this.date = newShow.date;
		this.duration = newShow.duration;
		this.tour_id = newShow.tour_id;
		this.venueName = newShow.venueName;
		this.location = newShow.location;
		this.venueId = newShow.venueId;
		this.updated_at = newShow.updated_at;
		this.tracks = newShow.tracks;
	}
	
	public Show(String yearId) {
		this.yearId= yearId;
	}
	
	public Show(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getYearId() {
		return yearId;
	}

	public void setYearId(String yearId) {
		this.yearId = yearId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public String getVenueName() {
		return venueName;
	}

	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getVenueId() {
		return venueId;
	}

	public void setVenueId(int venueId) {
		this.venueId = venueId;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public int getTour_id() {
		return tour_id;
	}

	public void setTour_id(int tour_id) {
		this.tour_id = tour_id;
	}

	@JsonProperty("tracks")
	public List<Track> getTracks() {
		return tracks;
	}

	@JsonProperty("tracks")
	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}

	@Override
	public String toString() {
		return "Show [id=" + id + ", yearId=" + yearId + ", date=" + date + ", duration=" + duration + ", tour_id="
				+ tour_id + ", venueName=" + venueName + ", location=" + location + ", venueId=" + venueId
				+ ", updated_at=" + updated_at + ", tracks=" + tracks + "]";
	}
	
}
