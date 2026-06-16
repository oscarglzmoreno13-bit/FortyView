package com.mx.forty.controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.file.UploadedFile;

import com.mx.forty.dto.vo.PedidoViewVo;
import com.mx.forty.util.ConstantesView;

import jakarta.annotation.PostConstruct;

@Named("consultaPedidos")
@RequestScoped
public class BusquedaPedidosController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<PedidoViewVo> listaPedidos;
	private PedidoViewVo pedidoSelected;
	private PedidoViewVo pedidoRsp;
	private UploadedFile file;
	private String pedidoEcartPay;
	private Integer idPedidoSelected;

	public String getPedidoEcartPay() {
		return pedidoEcartPay;
	}

	public void setPedidoEcartPay(String pedidoEcartPay) {
		this.pedidoEcartPay = pedidoEcartPay;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public List<PedidoViewVo> getListaPedidos() {
		if(listaPedidos==null) {
			init();
		}
		return listaPedidos;
	}

	public void setListaPedidos(List<PedidoViewVo> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}
	
	public void algo() {
		if(pedidoSelected == null) {
			
		}
	}

	public PedidoViewVo getPedidoSelected() {
		if(pedidoSelected==null) {
			if(pedidoRsp==null) {
				pedidoSelected = new PedidoViewVo();
			} else {
				pedidoSelected = pedidoRsp;
			}
			
		}
		return pedidoSelected;
	}

	public Integer getIdPedidoSelected() {
		return idPedidoSelected;
	}

	public void setIdPedidoSelected(Integer idPedidoSelected) {
		this.idPedidoSelected = idPedidoSelected;
	}

	public void setPedidoSelected(PedidoViewVo pedidoSelected) {
		this.pedidoSelected = pedidoSelected;
		pedidoRsp = pedidoSelected==null? new PedidoViewVo():pedidoRsp;
		pedidoRsp.setIdPedido(pedidoSelected.getIdPedido());
		idPedidoSelected =  Integer.valueOf(pedidoSelected.getIdPedido());
	}

	@PostConstruct
	public void init() {
//		if(pedidoSelected!=null && pedidoSelected.getIdPedido()!=null) {
//			
//		} else {
//			listaPedidos = new ArrayList<PedidoViewVo>();
//			pedidoSelected = new PedidoViewVo();
//			buscaPedidos();
//		}
		pedidoSelected = null;
		pedidoRsp = new PedidoViewVo();
		buscaPedidos();
		
	}
	
	public void onRowSelect(SelectEvent<PedidoViewVo> event) {
        this.pedidoSelected = event.getObject();
        // Additional backend logic can go here
    }

	private void buscaPedidos() {
		// TODO Auto-generated method stub
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(ConstantesView.hostPROD+"/api/pedidos/buscaPedidos");
		List<Map<String, Object>> lst = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Map<String, Object>>>() {});
		if(lst!=null) {
			listaPedidos = new ArrayList<PedidoViewVo>();
			for (Map<String, Object> map : lst) {
				PedidoViewVo vo = new PedidoViewVo();
				vo.setEstatus((String) map.get("estatus"));
				vo.setFecha((String) map.get("fecha"));
				vo.setIdPedido(((BigDecimal) map.get("numPedido")).intValue());
				vo.setMonto(((BigDecimal) map.get("monto")).doubleValue());
				vo.setNombre((String) map.get("nombre"));
				vo.setTelefono((String) map.get("telefono"));
				listaPedidos.add(vo);
			}
		}
		
	}
	
	public void updatePedido() {
		if(pedidoRsp!=null || idPedidoSelected!=null) {
			Map<String, Object> json = new HashMap<String, Object>();
			json.put("idPedido", idPedidoSelected);
			json.put("numOrden", pedidoEcartPay);
			Client client = ClientBuilder.newClient();
	        String url = ConstantesView.hostPROD+"/api/pedidos/updatePedido";

	        // Aquí mandas tu Map o DTO como JSON (ejemplo simplificado)
	        WebTarget target = client.target(url);
	        Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.json(json));
	        response.readEntity(new GenericType<Map<String, Object>>() {});
	        response.close();
	        client.close();
	        buscaPedidos();
		}
	}
	
	public void procesaPagos(BufferedReader reader) {
		List<PedidoViewVo> lista = new ArrayList<PedidoViewVo>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        String regex = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z$";
        int numFila = 0;
	  	String dateCreated = "";
	  	 Boolean esHoy = Boolean.FALSE;
		   Instant instant = null;
		    String[] valores = null;
		    Calendar otra = Calendar.getInstance();
		    Calendar hoy = Calendar.getInstance();
		    hoy.set(Calendar.DAY_OF_MONTH, 5);
		    hoy.set(Calendar.MONTH, Calendar.MAY);
		    List<Map<String, Object>> listaMap = new ArrayList<Map<String,Object>>();
		 try {
		        String linea;
		        while ((linea = reader.readLine()) != null) {
		        	if(numFila > 0 ) {
			  			 valores = linea.trim().split(",");
			  			 if(valores!=null && valores.length>0) {
			  				 dateCreated=null;
			  				 PedidoViewVo vo = new PedidoViewVo();
			  				 vo.setEstatus(getString(valores[ConstantesView.columna_status]));
			  				 vo.setTelefono((getString(valores[ConstantesView.columna_telefono])));;
			  				 vo.setMonto(getDouble(getString(valores[ConstantesView.columna_amount])));
			  				 vo.setNombre((getString(valores[ConstantesView.columna_nombre])));
			  				 vo.setLastName((getString(valores[ConstantesView.columna_lastName])));
			  				 for (int i = 0; i < valores.length; i++) {
								String string = valores[i];
								if(string.matches("^OR\\d+$")) {
									vo.setPedidoEcartpay(getString(string));
								}
								if(string.matches(regex)) {
									dateCreated = string;
								}
							}System.out.println();
			  				 if(dateCreated==null) {
		  						 vo.setFechaCreated(new Date());
		  					 } else {
		  						instant = Instant.parse(dateCreated);
				  				 vo.setFechaCreated(Date.from(instant));
		  					 }
			  				otra.setTime(vo.getFechaCreated());
			  				esHoy =  hoy.get(Calendar.YEAR) == otra.get(Calendar.YEAR) &&
			                         hoy.get(Calendar.MONTH) == otra.get(Calendar.MONTH) &&
			                         hoy.get(Calendar.DAY_OF_MONTH) == otra.get(Calendar.DAY_OF_MONTH);
//							if(esHoy) {
								if(vo.getEstatus().equals(ConstantesView.estatus_pagado_ecarPay)) {
									lista.add(vo);
									listaMap.add(getMapEnvio(vo));
//								}
							}
			  			 }
			  		 } else {
			  			 numFila++;
			  		 }
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		 
		 if(!lista.isEmpty()) {
			 Client client = ClientBuilder.newClient();
		        String url = ConstantesView.hostPROD+"/api/pedidos/generaOrdenEnvio";
		        
		        // Aquí mandas tu Map o DTO como JSON (ejemplo simplificado)
		        WebTarget target = client.target(url);
		        Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.json(listaMap));
		        response.readEntity(new GenericType<Map<String, Object>>() {});
		        response.close();
		        client.close();
		 }
	}
	
	 private Map<String, Object> getMapEnvio(PedidoViewVo vo) {
		// TODO Auto-generated method stub
		 Map<String, Object> map = new HashMap<String, Object>();
		 map.put("lastName",vo.getLastName() );
		 map.put("monto", vo.getMonto() );
		 map.put("nombre", vo.getNombre() );
		 map.put("telefono", vo.getTelefono() );
		 map.put("orderNum", vo.getPedidoEcartpay());
		return map;
	}

	 public void handleFileUpload(FileUploadEvent event) {
//	        UploadedFile file = event.getFile();
//	        FacesMessage message = new FacesMessage("Exito", file.getFileName() + " subido.");
//	        FacesContext.getCurrentInstance().addMessage(null, message);
//	        
//	        // Procesar el archivo (guardar en disco, base de datos, etc.)
//	        try {
//	            InputStream input = file.getInputStream();
//	            // ... logica para guardar ...
//	        } catch (IOException e) {
//	            // ... manejar error ...
//	        }
		 String field=event.getComponent().getId();
	        
	    	try {   		
	            /* Guardamos el fichero */
	    		Map<String, Object> reg = new HashMap<String, Object>();
	    		reg.put(field, event.getFile().getFileName() );
	            reg.put(field+"_content", event.getFile().getContent());
	            byte[] data = event.getFile().getContent();
	            BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(data)));
	            procesaPagos(reader);
	    	} catch (Exception ex) { 
	    		ex.printStackTrace();
	    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"", ex.getLocalizedMessage()));    		
	    		}
	    }
	
	private Double getDouble (Object valor) {
		if(valor == null) {
			return Double.valueOf(0);
		} else {
			try {
				return Double.valueOf(valor.toString());
			} catch (Exception e) {
				// TODO: handle exception
				return Double.valueOf(0);
			}
		}
	}
	
	private String getString(Object valor) {
		// TODO Auto-generated method stub
		if(valor == null) {
			return "";
		} else {
			return new String(valor.toString());
		}
	}
	
	 public void upload() {
	        if (file != null) {
	            FacesMessage message = new FacesMessage("Successful", file.getFileName() + " is uploaded.");
	            FacesContext.getCurrentInstance().addMessage(null, message);
	        }
	    }
}
