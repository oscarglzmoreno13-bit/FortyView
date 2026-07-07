package com.mx.forty.media.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.mx.forty.dto.vo.media.google.GoogleTrendsResponse;
import com.mx.forty.dto.vo.media.google.TrendingSearch;
import com.mx.forty.dto.vo.media.youtube.Item;
import com.mx.forty.dto.vo.media.youtube.VideoResponse;
import com.mx.forty.util.ConstantesView;

import jakarta.annotation.PostConstruct;

@Named("mediaTrending")
@RequestScoped
public class MediaTrendingController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Item> listaVideos;
	private List<TrendingSearch> listaTrendingSearch;
	
	public List<TrendingSearch> getListaTrendingSearch() {
		return listaTrendingSearch;
	}

	public void setListaTrendingSearch(List<TrendingSearch> listaTrendingSearch) {
		this.listaTrendingSearch = listaTrendingSearch;
	}

	@PostConstruct
	public void init() {
		
	}
	
	public List<Item> getListaVideos() {
		return listaVideos;
	}

	public void setListaVideos(List<Item> listaVideos) {
		this.listaVideos = listaVideos;
	}

	public void getYouTubeTrending() {
		listaVideos = new ArrayList<Item>();
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(ConstantesView.hostPROD+"/api/youtube/trending");
		try {
			VideoResponse response = target.request(MediaType.APPLICATION_JSON).get(new GenericType<VideoResponse>() {});
			listaVideos = response.getItems();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getGoogleTrends() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(ConstantesView.hostPROD+"/api/SerpApi/trending");
		try {
			listaTrendingSearch = new ArrayList<TrendingSearch>();
			GoogleTrendsResponse response = target.request(MediaType.APPLICATION_JSON).get(new GenericType<GoogleTrendsResponse>() {});
			listaTrendingSearch = response.getTrending_searches();
			System.out.println();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

}
