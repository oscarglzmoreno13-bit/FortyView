package com.mx.forty.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import org.primefaces.event.SelectEvent;

import com.mx.forty.dto.vo.CampaniaVoUi;
import com.mx.forty.util.ConstantesView;
import com.mx.forty.util.UtileriasUi;

import jakarta.annotation.PostConstruct;

@Named("campaniaController")
@ViewScoped
public class CampaniaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CampaniaVoUi campaniaSelected;
	private Date fechaInicioSelected;
	private Date fechaFinSelected;
	
	public Date getFechaInicioSelected() {
		return fechaInicioSelected;
	}

	public void setFechaInicioSelected(Date fechaInicioSelected) {
		this.fechaInicioSelected = fechaInicioSelected;
	}

	public Date getFechaFinSelected() {
		return fechaFinSelected;
	}

	public void setFechaFinSelected(Date fechaFinSelected) {
		this.fechaFinSelected = fechaFinSelected;
	}

	public CampaniaVoUi getCampaniaSelected() {
		if(campaniaSelected==null) {
			campaniaSelected = new CampaniaVoUi();
		}
		return campaniaSelected;
	}
	
	 public void onDateSelect(SelectEvent<Date> event) {
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
	    }

	public void setCampaniaSelected(CampaniaVoUi campaniaSelected) {
		this.campaniaSelected = campaniaSelected;
		campaniaSelected.setFechaFin(campaniaSelected.getFechaFin()==null?new Date():campaniaSelected.getFechaFin());
		campaniaSelected.setFechaInicio(campaniaSelected.getFechaInicio()==null?new Date():campaniaSelected.getFechaInicio());
		fechaFinSelected = new Date(campaniaSelected.getFechaInicio().getTime());
		fechaInicioSelected = new Date(campaniaSelected.getFechaFin().getTime());
	}

	private List<CampaniaVoUi> listaCampanias;
	
	public void getCampanias() {
		Client client = ClientBuilder.newClient();
		listaCampanias = new ArrayList<CampaniaVoUi>();
		WebTarget target = client.target(ConstantesView.hostPROD+"/api/campanias");
		System.out.println(ConstantesView.hostPROD+"/api/campanias");
		
		List<Map<String, Object>> lst = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Map<String, Object>>>() {});
		System.out.println("Tamaño de la lista de compañas"+lst.size());
		for (Map<String, Object> map : lst) {
			listaCampanias.add(UtileriasUi.convertJsonToCampania(map));
		}
	}
	
	public void saveCampania() {
		
		if(fechaFinSelected==null || fechaInicioSelected==null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Las fechas de Inicio y Fin son obligatorias"));
		} else {
			 if(!fechaFinSelected.after(fechaInicioSelected)) {
				 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La fecha Final no puede ser menor a la fecha de Incio"));
			 } else {
				 campaniaSelected.setFechaFin(new Date(fechaFinSelected.getTime()));
				 
				 campaniaSelected.setFechaInicio(new Date(fechaInicioSelected.getTime()));
				 Client client = ClientBuilder.newClient();
		         WebTarget target = client.target(ConstantesView.hostPROD+"/api/campanias/save");

		         try { 
		        	 Map<String, Object> voJson = UtileriasUi.convertCampaniaVoToJson(getCampaniaSelected());
		        	 target.request(MediaType.APPLICATION_JSON).post(Entity.json(voJson));
		        	 getCampanias();
		         }catch(Exception e) {
		        	 e.printStackTrace();
		        	 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
		         }
		        // Enviar el objeto como JSON
		         PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
		         PrimeFaces.current().ajax().update("form:messages", "form:dt-Marcas");
			 }
		}
		 
	}
	
//	public CampaniaVo getCampaniaSelected() {
//		if(campaniaSelected==null) {
//			campaniaSelected = new CampaniaVo();
//		}
//		return campaniaSelected==null?new CampaniaVo():campaniaSelected;
//	}
//
//	public void setCampaniaSelected(CampaniaVo campaniaSelected) {
//		this.campaniaSelected = campaniaSelected;
//	}

	public List<CampaniaVoUi> getListaCampanias() {
		if(listaCampanias==null) {
			getCampanias();
			campaniaSelected = new CampaniaVoUi();
		}
		return listaCampanias;
	}

	public void setListaCampanias(List<CampaniaVoUi> listaCampanias) {
		this.listaCampanias = listaCampanias;
	}

	public void deleteCampania() {
		Client client = ClientBuilder.newClient();
	    WebTarget target = client.target(ConstantesView.hostPROD+"/api/campanias/delete");

	    Map<String, Object> voJson = UtileriasUi.convertCampaniaVoToJson(getCampaniaSelected());
	    Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.json(voJson));
	    getCampanias();
	    if (response.getStatus() == 200) {
	        System.out.println("Campania eliminada correctamente");
	    } else {
	        System.out.println("Error al eliminar: " + response.getStatus());
	    }
       // Enviar el objeto como JSON

        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        
	}
	
	@PostConstruct
	public void init() {
		if(listaCampanias==null) {
			getCampanias();
			campaniaSelected = new CampaniaVoUi();
		}
	}
	

}
