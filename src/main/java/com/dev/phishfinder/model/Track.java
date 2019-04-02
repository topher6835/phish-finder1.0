package com.dev.phishfinder.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Track {
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("song_id")
	private Long songId;
	
	@JsonProperty("show_id")
	private Long showId;
	
	@JsonProperty("show_date")
	private Date showDate;
	
	@JsonProperty("title")
	private String title;
	
	@JsonProperty("position")
	private int position;
	
	@JsonProperty("duration")
	private Long duration;
	
	@JsonProperty("set")
	private String set;
	
	@JsonProperty("set_name")
	private String setName;
	
	@JsonProperty("song_ids")
	private void unpackNameFromNestedObject(long[] ids) {
		songId = ids[0];
	}

	public Track() {
	}

//	public Track(Long id, Long songId, Long showId, Date showDate, String title, int position, Long duration,
//			String set, String setName) {
//		super();
//		this.id = id;
//		this.songId = songId;
//		this.showId = showId;
//		this.showDate = showDate;
//		this.title = title;
//		this.position = position;
//		this.duration = duration;
//		this.set = set;
//		this.setName = setName;
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSongId() {
		return songId;
	}

	public void setSongId(Long songId) {
		this.songId = songId;
	}

	public Long getShowId() {
		return showId;
	}

	public void setShowId(Long showId) {
		this.showId = showId;
	}

	public Date getShowDate() {
		return showDate;
	}

	public void setShowDate(Date showDate) {
		this.showDate = showDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public String getSet() {
		return set;
	}

	public void setSet(String set) {
		this.set = set;
	}

	public String getSetName() {
		return setName;
	}

	public void setSetName(String setName) {
		this.setName = setName;
	}

	@Override
	public String toString() {
		return "Track [id=" + id + ", songId=" + songId + ", showId=" + showId + ", showDate=" + showDate + ", title="
				+ title + ", position=" + position + ", duration=" + duration + ", set=" + set + ", setName=" + setName
				+ "]";
	}

}
