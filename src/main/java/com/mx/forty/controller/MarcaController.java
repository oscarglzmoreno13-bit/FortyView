package com.mx.forty.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
import javax.ws.rs.core.Response;

import org.primefaces.PrimeFaces;
import org.primefaces.util.LangUtils;

import com.mx.forty.dto.vo.MarcaVo;
import com.mx.forty.util.CustomerStatus;


@Named("marcaController")
@ViewScoped
public class MarcaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<MarcaVo> listaMarcas;
	private MarcaVo marca;
	private List<MarcaVo> listaFiltrada;
	private boolean filtroGlobal;
	
	public boolean isFiltroGlobal() {
		return filtroGlobal;
	}

	public void setFiltroGlobal(boolean filtroGlobal) {
		this.filtroGlobal = filtroGlobal;
	}

	public List<MarcaVo> getListaFiltrada() {
		return listaFiltrada;
	}

	public void setListaFiltrada(List<MarcaVo> listaFiltrada) {
		this.listaFiltrada = listaFiltrada;
	}

	@PostConstruct
    public void init() {
		listaMarcas = new ArrayList<MarcaVo>();
		filtroGlobal = false;
        getMarcas();
    }

	public List<MarcaVo> getListaMarcas() {
		if(listaMarcas==null) {
			getMarcas();
		}
		return listaMarcas;
	}
	
	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isBlank(filterText)) {
            return true;
        }

        MarcaVo marcaVo = (MarcaVo) value;
        return marcaVo.getClave().toLowerCase().contains(filterText)
                || marcaVo.getNombre().toLowerCase().contains(filterText);
    }
	
	public void toggleGlobalFilter() {
        setFiltroGlobal(!isFiltroGlobal());
    }

	public void setListaMarcas(List<MarcaVo> listaMarcas) {
		this.listaMarcas = listaMarcas;
	}

	public MarcaVo getMarca() {
		marca= marca==null?new MarcaVo():marca;
		return marca;
	}

	public void setMarca(MarcaVo marca) {
		this.marca = marca;
	}

	private void getMarcas() {
		// TODO Auto-generated method stub
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/demo/api/marcas");
		listaMarcas = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<MarcaVo>>() {});
   
	}
	
	public void saveMOrUpdate() {
		 Client client = ClientBuilder.newClient();
         WebTarget target = client.target("http://localhost:8080/demo/api/marcas");

         try {
        	 getMarca().setClave(getMarca().getClave().trim().toUpperCase());
        	 getMarca().setNombre(getMarca().getNombre().trim().toUpperCase());
        	 target.request(MediaType.APPLICATION_JSON)
             .post(Entity.entity(marca, MediaType.APPLICATION_JSON), MarcaVo.class);
        	 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registro guardado"));
         }catch(Exception e) {
        	 e.printStackTrace();
        	 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
         }
        // Enviar el objeto como JSON
         getMarcas();
         PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
         PrimeFaces.current().ajax().update("form:messages", "form:dt-Marcas");
	}
	
	public void deleteMarca() {
		Client client = ClientBuilder.newClient();
	    WebTarget target = client.target("http://localhost:8080/demo/api/marcas/delete");

	    Response response = target.request(MediaType.APPLICATION_JSON)
	                              .post(Entity.entity(marca, MediaType.APPLICATION_JSON));

	    if (response.getStatus() == 200) {
	        System.out.println("Marca eliminada correctamente");
	    } else {
	        System.out.println("Error al eliminar: " + response.getStatus());
	    }
       // Enviar el objeto como JSON

        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-Marcas");
	
	}
	
	public CustomerStatus[] getCustomerStatus() {
        return CustomerStatus.values();
    }
	

}
