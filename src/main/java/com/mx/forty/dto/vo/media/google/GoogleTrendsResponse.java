package com.mx.forty.dto.vo.media.google;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleTrendsResponse {

	 private List<TrendingSearch> trending_searches;

	 public List<TrendingSearch> getTrending_searches() {
		 return trending_searches;
	 }

	 public void setTrending_searches(List<TrendingSearch> trending_searches) {
		 this.trending_searches = trending_searches;
	 }
}
