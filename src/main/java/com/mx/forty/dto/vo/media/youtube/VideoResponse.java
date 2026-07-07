package com.mx.forty.dto.vo.media.youtube;

import java.util.List;

public class VideoResponse {

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	private List<Item> items;
}
