package com.mx.forty.dto.vo.media.youtube;

import java.util.Map;

public class Snippet {

	private String title;
    public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getChannelTitle() {
		return channelTitle;
	}
	public void setChannelTitle(String channelTitle) {
		this.channelTitle = channelTitle;
	}
	public Map<String, Thumbnail> getThumbnails() {
		return thumbnails;
	}
	public void setThumbnails(Map<String, Thumbnail> thumbnails) {
		this.thumbnails = thumbnails;
	}
	private String description;
    private String channelTitle;
    private Map<String, Thumbnail> thumbnails;
}
