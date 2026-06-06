package com.mx.forty.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import com.mx.forty.dto.vo.PedidoViewVo;
import com.mx.forty.util.ConstantesView;

import jakarta.annotation.PostConstruct;

@Named("consultaPedidos")
@ViewScoped
public class BusquedaPedidosController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<PedidoViewVo> listaPedidos;
	private PedidoViewVo pedidoSelected;
	private UploadedFile file;

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

	public PedidoViewVo getPedidoSelected() {
		return pedidoSelected;
	}

	public void setPedidoSelected(PedidoViewVo pedidoSelected) {
		this.pedidoSelected = pedidoSelected;
	}

	@PostConstruct
	public void init() {
		listaPedidos = new ArrayList<PedidoViewVo>();
		pedidoSelected = new PedidoViewVo();
		buscaPedidos();
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
	
	public void procesaPagos() {
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
		 try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
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
			  				 for (int i = 0; i < valores.length; i++) {
								String string = valores[i];
								if(string.matches("^OR\\d+$")) {
									vo.setPedidoEcartpay(getString(string));
								}
								if(string.matches(regex)) {
									dateCreated = string;
								}
							}
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
							if(esHoy) {
								lista.add(vo);
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
			 System.out.println();
		 }
	}
	
	 public void handleFileUpload(FileUploadEvent event) {
	        UploadedFile file = event.getFile();
	        FacesMessage message = new FacesMessage("Exito", file.getFileName() + " subido.");
	        FacesContext.getCurrentInstance().addMessage(null, message);
	        
	        // Procesar el archivo (guardar en disco, base de datos, etc.)
	        try {
	            InputStream input = file.getInputStream();
	            // ... logica para guardar ...
	        } catch (IOException e) {
	            // ... manejar error ...
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
}
