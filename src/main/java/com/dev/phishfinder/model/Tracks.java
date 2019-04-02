package com.dev.phishfinder.model;

import java.util.List;

import com.dev.phishfinder.model.Track;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Tracks {

	private Long tracksShowId;
	
	@JsonProperty("tracks")
	private List<Track> tracks;
	
	public Tracks() {
	}
	
	public Tracks(Long tracksShowId) {
		super();
		this.tracksShowId = tracksShowId;
	}
	
	public Tracks(Long tracksShowId, List<Track> tracks) {
		super();
		this.tracksShowId = tracksShowId;
		this.tracks = tracks;
	}

	public Long getTracksShowId() {
		return tracksShowId;
	}

	public void setTracksShowId(Long tracksShowId) {
		this.tracksShowId = tracksShowId;
	}

	public List<Track> getTracks() {
		return tracks;
	}

	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}

	@Override
	public String toString() {
		return "Tracks [tracksShowId=" + tracksShowId + ", tracks=" + tracks + "]";
	}

}
