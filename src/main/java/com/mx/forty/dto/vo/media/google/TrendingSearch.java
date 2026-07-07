package com.mx.forty.dto.vo.media.google;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TrendingSearch {

	private String query;
    private Long search_volume;
    private Integer increase_percentage;
    private List<Category> categories;
    private List<String> TrendBreakdown;
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public Long getSearch_volume() {
		return search_volume;
	}
	public void setSearch_volume(Long search_volume) {
		this.search_volume = search_volume;
	}
	public Integer getIncrease_percentage() {
		return increase_percentage;
	}
	public void setIncrease_percentage(Integer increase_percentage) {
		this.increase_percentage = increase_percentage;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	public List<String> getTrendBreakdown() {
		return TrendBreakdown;
	}
	public void setTrendBreakdown(List<String> trendBreakdown) {
		TrendBreakdown = trendBreakdown;
	}
}
