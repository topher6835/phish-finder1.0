package com.dev.phishfinder.service;

import java.util.List;

import com.dev.phishfinder.model.Track;

public interface TrackRepository {
	
	public Track getTrackById(Long trackId);

	public List<Track> getTracksByShowId(Long tracksShowId);

	public List<Track> setTracksByShow(Long showId, List<Track> trackList);

	public void reportCacheEvict();

}
