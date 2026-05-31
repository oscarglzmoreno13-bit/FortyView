package com.mx.forty.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.primefaces.PrimeFaces;

import com.mx.forty.dto.vo.MarcaVo;
import com.mx.forty.dto.vo.ProductoVo;
import com.mx.forty.util.ConstantesView;


@Named("productosController")
@ViewScoped
public class ProductosController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<ProductoVo> listaProductos;
	private ProductoVo productoSelected;
	private List<MarcaVo> listaMarcas;
	private List<String> listaUpc;
	
	
	public List<String> getListaUpc() {
		getAllUpc();
		return listaUpc;
	}

	public void setListaUpc(List<String> listaUpc) {
		this.listaUpc = listaUpc;
	}

	public List<MarcaVo> getListaMarcas() {
		if(listaMarcas==null || (listaMarcas!=null && listaMarcas.isEmpty()) ) {
			getMarcas();
		}
		return listaMarcas;
	}

	public void setListaMarcas(List<MarcaVo> listaMarcas) {
		this.listaMarcas = listaMarcas;
	}

	@PostConstruct
	public void init() {
		listaProductos = new ArrayList<ProductoVo>();
		getProductos();
		getMarcas();
    }
	
	public void getProductos() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(ConstantesView.hostPROD+"/api/productos");
		listaProductos = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<ProductoVo>>() {});
	}
	
	private void getMarcas() {
		// TODO Auto-generated method stub
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(ConstantesView.hostPROD+"/api/marcas");
		listaMarcas = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<MarcaVo>>() {});
	}
	
	private void getAllUpc() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(ConstantesView.hostPROD+"/api/productos/getUpc");
		listaUpc = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<String>>() {});
	}
	
	public List<ProductoVo> getListaProductos() {
		if(listaProductos==null) {
			getProductos();
		}
		return listaProductos;
	}
	
	public void saveProducto () {
		 Client client = ClientBuilder.newClient();
         WebTarget target = client.target(ConstantesView.hostPROD+"/api/productos");

         try {
        	 target.request(MediaType.APPLICATION_JSON)
             .post(Entity.entity(productoSelected, MediaType.APPLICATION_JSON), ProductoVo.class);
        	 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registro guardado"));
         }catch(Exception e) {
        	 e.printStackTrace();
        	 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
         }
         getProductos();
		PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-Marcas");
	}
	
	public void deleteProducto () {
		 Client client = ClientBuilder.newClient();
        WebTarget target = client.target(ConstantesView.hostPROD+"/api/productos/delete");

        try {
       	 target.request(MediaType.APPLICATION_JSON)
            .post(Entity.entity(productoSelected, MediaType.APPLICATION_JSON), ProductoVo.class);
       	 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registro eliminado"));
        }catch(Exception e) {
       	 e.printStackTrace();
       	 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
        }
        getProductos();
		PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
       PrimeFaces.current().ajax().update("form:messages", "form:dt-Marcas");
	}
	
	
	public void setListaProductos(List<ProductoVo> listaProductos) {
		this.listaProductos = listaProductos;
	}
	public ProductoVo getProductoSelected() {
		if(productoSelected==null) {
			productoSelected = new ProductoVo();
		}
		return productoSelected;
	}
	public void setProductoSelected(ProductoVo productoSelected) {
		this.productoSelected = productoSelected;
	}
	
	
	
}
