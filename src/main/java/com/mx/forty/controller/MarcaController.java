package com.mx.forty.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.mx.forty.dto.vo.MarcaVo;

import jakarta.annotation.PostConstruct;


@Named("marcaController")
@ViewScoped
public class MarcaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<MarcaVo> listaMarcas;
	private MarcaVo marcaVo;
	
	@PostConstruct
    public void init() {
		listaMarcas = new ArrayList<MarcaVo>();
        getMarcas();
    }

	public List<MarcaVo> getListaMarcas() {
		if(listaMarcas==null) {
			getMarcas();
		}
		return listaMarcas;
	}

	public void setListaMarcas(List<MarcaVo> listaMarcas) {
		this.listaMarcas = listaMarcas;
	}

	public MarcaVo getMarcaVo() {
		return marcaVo;
	}

	public void setMarcaVo(MarcaVo marcaVo) {
		this.marcaVo = marcaVo;
	}

	private void getMarcas() {
		// TODO Auto-generated method stub
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/demo/api/marcas");
		listaMarcas = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<MarcaVo>>() {});
   
	}
	

}
