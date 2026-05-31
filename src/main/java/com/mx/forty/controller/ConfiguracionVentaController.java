package com.mx.forty.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.mx.forty.dto.vo.CampaniaVoUi;
import com.mx.forty.dto.vo.ConfiguracionVentaVoView;
import com.mx.forty.dto.vo.MarcaVo;
import com.mx.forty.dto.vo.ProductoVoConfigutarion;
import com.mx.forty.dto.vo.TipoConfiguracionVo;
import com.mx.forty.util.ConstantesView;
import com.mx.forty.util.UtileriasUi;

@Named("configuracionesController")
@ViewScoped
public class ConfiguracionVentaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ConfiguracionVentaVoView> listaConfiguraciones;
	private ConfiguracionVentaVoView configuracionSelected;
	private List<TipoConfiguracionVo> listaTipos;
	private Date fechaInicioSelected;
	private Date fechaFinSelected;
	private TipoConfiguracionVo tipoConfiguracionSelected;
	private List<MarcaVo> listaMarcas; 
	private MarcaVo marcaSelected;
	private List<ProductoVoConfigutarion> listaProductos;
	private List<ProductoVoConfigutarion> listaSelectedProductsFinal;
	private List<CampaniaVoUi> listaCampanias;
	private CampaniaVoUi campaniaSelected;
	
	
	public CampaniaVoUi getCampaniaSelected() {
		if(campaniaSelected == null) {
			campaniaSelected = new CampaniaVoUi();
			campaniaSelected.setIdCampania(Integer.valueOf(0));
		}
		return campaniaSelected;
	}

	public void setCampaniaSelected(CampaniaVoUi campaniaSelected) {
		this.campaniaSelected = campaniaSelected;
	}

	public List<CampaniaVoUi> getListaCampanias() {
		if(listaCampanias == null) {
			listaCampanias = new ArrayList<CampaniaVoUi>();
		}
		return listaCampanias;
	}

	public void setListaCampanias(List<CampaniaVoUi> listaCampanias) {
		this.listaCampanias = listaCampanias;
	}

	public List<ProductoVoConfigutarion> getListaSelectedProductsFinal() {
		return listaSelectedProductsFinal;
	}

	public void setListaSelectedProductsFinal(List<ProductoVoConfigutarion> listaSelectedProductsFinal) {
		this.listaSelectedProductsFinal = listaSelectedProductsFinal;
	}

	public List<ProductoVoConfigutarion> getListaSelectedTemp() {
		if(listaSelectedTemp==null) {
			listaSelectedTemp = new ArrayList<ProductoVoConfigutarion>();
		}
		return listaSelectedTemp;
	}

	public void setListaSelectedTemp(List<ProductoVoConfigutarion> listaSelectedTemp) {
		this.listaSelectedTemp = listaSelectedTemp;
	}

	private List<ProductoVoConfigutarion> listaSelectedTemp;
	


	public List<ProductoVoConfigutarion> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<ProductoVoConfigutarion> listaProductos) {
		this.listaProductos = listaProductos;
	}

	

	public void setMarcaSelected(MarcaVo marcaSelected) {
		this.marcaSelected = marcaSelected;
	}

	public List<MarcaVo> getListaMarcas() {
		return listaMarcas;
	}

	public void setListaMarcas(List<MarcaVo> listaMarcas) {
		this.listaMarcas = listaMarcas;
	}

	public TipoConfiguracionVo getTipoConfiguracionSelected() {
		if(tipoConfiguracionSelected == null) {
			tipoConfiguracionSelected = new TipoConfiguracionVo();
		}
		if(listaTipos==null || (listaTipos!=null && listaTipos.isEmpty())) {
			findTiposConfig();
		}
		return tipoConfiguracionSelected;
	}
	
	public MarcaVo getMarcaSelected() {
		if(marcaSelected == null) {
			marcaSelected = new MarcaVo();
			marcaSelected.setIdMarca(0);
		}
		if(listaMarcas==null || (listaMarcas!=null && listaMarcas.isEmpty())) {
			getMarcas();
		}
 		return marcaSelected;
	}

	public void setTipoConfiguracionSelected(TipoConfiguracionVo tipoConfiguracionSelected) {
		this.tipoConfiguracionSelected = tipoConfiguracionSelected;
	}

	public List<TipoConfiguracionVo> getListaTipos() {
		return listaTipos;
	}

	public void setListaTipos(List<TipoConfiguracionVo> listaTipos) {
		this.listaTipos = listaTipos;
	}

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

	public List<ConfiguracionVentaVoView> getListaConfiguraciones() {
		return listaConfiguraciones;
	}

	public void setListaConfiguraciones(List<ConfiguracionVentaVoView> listaConfiguraciones) {
		this.listaConfiguraciones = listaConfiguraciones;
	}

	public ConfiguracionVentaVoView getConfiguracionSelected() {
		return configuracionSelected;
	}

	public void setConfiguracionSelected(ConfiguracionVentaVoView configuracionSelected) {
		if (configuracionSelected == null) {
	        this.configuracionSelected = new ConfiguracionVentaVoView();
	        this.configuracionSelected.setFechaInicio(new Date());
	        this.configuracionSelected.setFechaFin(new Date());
	    } else {
	    	if(this.configuracionSelected==null) {
	    		this.configuracionSelected = configuracionSelected;
	    	}	
	    		this.configuracionSelected.setIdConfiguracionVenta(configuracionSelected.getIdConfiguracionVenta());
	    		this.configuracionSelected.setNombre(configuracionSelected.getNombre());
	    		this.configuracionSelected.setPrevioVenta(configuracionSelected.getPrevioVenta());
		        this.configuracionSelected.setFechaInicio(
		            configuracionSelected.getFechaInicio() == null ? new Date() : configuracionSelected.getFechaInicio()
		        );
		        this.configuracionSelected.setFechaFin(
		            configuracionSelected.getFechaFin() == null ? new Date() : configuracionSelected.getFechaFin()
		        );
		        this.configuracionSelected.setIdTipoConfiguracion(configuracionSelected.getIdTipoConfiguracion());
		        if(campaniaSelected==null) {
		        	campaniaSelected = new CampaniaVoUi();
		        }
		        campaniaSelected.setIdCampania(configuracionSelected.getIdCampania());
		        if(this.configuracionSelected.getListaProductos()==null) {
		        	this.configuracionSelected.setListaProductos(new ArrayList<ProductoVoConfigutarion>());
		        }
		        this.configuracionSelected.setListaProductos(configuracionSelected.getListaProductos());
		        if(this.configuracionSelected.getListaProductos()!=null) {
					for (ProductoVoConfigutarion productoVoConfigutarion : listaSelectedTemp) {
						for (ProductoVoConfigutarion prod : configuracionSelected.getListaProductos()) {
							if(productoVoConfigutarion.getIdProducto().equals(prod.getIdProducto())) {
								productoVoConfigutarion.setProductIsSelected(Boolean.TRUE);
								productoVoConfigutarion.setCantidad(prod.getCantidad());
							}
						}
					}
				}
	    }
	    setFechaInicioSelected(this.configuracionSelected.getFechaInicio());
	    setFechaFinSelected(this.configuracionSelected.getFechaFin());
	}

	@PostConstruct
	public void init() {
		if(listaConfiguraciones == null) {
			listaConfiguraciones = new ArrayList<ConfiguracionVentaVoView>();
		}
		if(listaTipos == null) {
			listaTipos = new ArrayList<TipoConfiguracionVo>();
		}
		if(configuracionSelected==null) {
			configuracionSelected = new ConfiguracionVentaVoView();
		}
		if(listaMarcas == null) {
			listaMarcas = new ArrayList<MarcaVo>();
		}
		buscaConfiguraciones();
	}
	
	public void onDateSelect(SelectEvent<Date> event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }
	
	 public void onItemSelect(SelectEvent event) {
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected marca", (String) event.getObject());
	        FacesContext.getCurrentInstance().addMessage(null, message);
	    }
	
	public void newConfiguration() {
		setConfiguracionSelected(null);
		findTiposConfig();
		tipoConfiguracionSelected = new TipoConfiguracionVo();
//		getMarcas();
		getCampanias();
		campaniaSelected = new CampaniaVoUi();
		campaniaSelected.setIdCampania(0);
//		marcaSelected = new MarcaVo();
//		marcaSelected.setIdMarca(0);
		listaSelectedTemp = new ArrayList<ProductoVoConfigutarion>();
		getProductsActives();
	}
	
//	public void clearNewProducts() {
////		getMarcas();
//		getProductsByMarcaSelected();
//		listaSelectedTemp = new ArrayList<ProductoVoConfigutarion>();
//	}
	
	public void getProductsByMarcaSelected() {
		if(marcaSelected!=null && (marcaSelected.getIdMarca()!=null  && !marcaSelected.getIdMarca().equals(0))) {
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target(ConstantesView.hostPROD+"/api/productos/findByIdMarca");
			listaProductos = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<ProductoVoConfigutarion>>() {});
			
		}
	}
	
	public void getProductsActives() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(ConstantesView.hostPROD+"/api/productos");
		listaSelectedTemp = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<ProductoVoConfigutarion>>() {});
		PrimeFaces.current().ajax().update("form:messages", ":dialogs:checkboxDT");
	}
	
	public void onRowSelect(SelectEvent<ProductoVoConfigutarion> event) {
        FacesMessage msg = new FacesMessage("Product Selected", String.valueOf(event.getObject().getIdProducto()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
        productoSeleccionado(true, event.getObject());
    }
	
	public void productoSeleccionado(Boolean isSelected, ProductoVoConfigutarion vo) {
		if(isSelected) {
			vo.setCantidad(0);
			vo.setProductIsSelected(true);
		} else {
			vo.setCantidad(0);
			vo.setProductIsSelected(false);
		}
	}
	
    public void onRowUnselect(UnselectEvent<ProductoVoConfigutarion> event) {
        FacesMessage msg = new FacesMessage("Product Unselected", String.valueOf(event.getObject().getIdProducto()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
        productoSeleccionado(false, event.getObject());
    }
	
	public void findTiposConfig() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(ConstantesView.hostPROD+"/api/tipoConfiguracion");
		listaTipos = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<TipoConfiguracionVo>>() {});
	}
	
	public void getMarcas() {
		// TODO Auto-generated method stub
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(ConstantesView.hostPROD+"/api/marcas");
		listaMarcas = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<MarcaVo>>() {});
		
	}
	
	public void cha() {
		findTiposConfig();
		tipoConfiguracionSelected = new TipoConfiguracionVo();
		getCampanias();
		listaSelectedTemp = new ArrayList<ProductoVoConfigutarion>();
		getProductsActives();
		if(configuracionSelected!=null) {
			campaniaSelected = new CampaniaVoUi();
			campaniaSelected.setIdCampania(configuracionSelected.getIdCampania());
			campaniaSelected.setNombre(configuracionSelected.getNombreCampania());
			if(configuracionSelected.getListaProductos()!=null) {
				for (ProductoVoConfigutarion productoVoConfigutarion : listaSelectedTemp) {
					for (ProductoVoConfigutarion prod : configuracionSelected.getListaProductos()) {
						if(productoVoConfigutarion.getIdProducto().equals(prod.getIdProducto())) {
							productoVoConfigutarion.setProductIsSelected(Boolean.TRUE);
							productoVoConfigutarion.setCantidad(prod.getCantidad());
						}
					}
				}
			}
			
		}
	}
	
	public void getCampanias() {
		Client client = ClientBuilder.newClient();
		listaCampanias = new ArrayList<CampaniaVoUi>();
		WebTarget target = client.target(ConstantesView.hostPROD+"/api/campanias");
		List<Map<String, Object>> lst = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Map<String, Object>>>() {});
		for (Map<String, Object> map : lst) {
			listaCampanias.add(UtileriasUi.convertJsonToCampania(map));
		}
	}
	
	public void buscaConfiguraciones() {
		Client client = ClientBuilder.newClient();
		listaConfiguraciones  = new ArrayList<ConfiguracionVentaVoView>();
		WebTarget target = client.target(ConstantesView.hostPROD+"/api/configuraciones/findAll");
		List<Map<String, Object>> lst = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Map<String, Object>>>() {});
		for (Map<String, Object> map : lst) {
			listaConfiguraciones.add(UtileriasUi.convertJsonToCongiguracionVo(map));
		}
	}
	
	public void saveConfiguracion() {
		if(validaaDatos()) {
			configuracionSelected.setFechaFin(fechaFinSelected);
			configuracionSelected.setFechaInicio(fechaInicioSelected);
//			con
			configuracionSelected.setIdCampania(campaniaSelected.getIdCampania());
			configuracionSelected.setListaProductos(listaProductos);
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target(ConstantesView.hostPROD+"/api/configuraciones/save");
			try { 
			    Map<String, Object> voJson = UtileriasUi.getVoConfiguracionVtaToMap(configuracionSelected);
			    Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.json(voJson));
			    init();
			} catch(Exception e) {
			    e.printStackTrace();
			    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
			}
	        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
		}
	}

	public boolean validaaDatos() {
		if(configuracionSelected.getIdTipoConfiguracion() == null || 
				((configuracionSelected.getIdTipoConfiguracion()!=null &&
						configuracionSelected.getIdTipoConfiguracion().equals(0)))) {
			return false;
		}
		if(campaniaSelected == null || 
				(campaniaSelected!=null && (campaniaSelected.getIdCampania()!=null &&
						campaniaSelected.getIdCampania().equals(0)))) {
			return false;
		}
		if(configuracionSelected.getNombre()==null || 
				(configuracionSelected.getNombre()!=null && configuracionSelected.getNombre().equals(""))) {
			return false;
		}
		if(fechaInicioSelected==null || fechaFinSelected==null) {
			return false;
		}
		if(configuracionSelected.getPrevioVenta()==null || 
				(configuracionSelected.getPrevioVenta()!=null && 
				 configuracionSelected.getPrevioVenta().equals(Integer.valueOf(0)) )) {	
			return false;
		}
		if(listaSelectedTemp.isEmpty()) {
			return false;
		} else {
			listaProductos = new ArrayList<ProductoVoConfigutarion>();
			for (ProductoVoConfigutarion productoVoConfigutarion : listaSelectedTemp) {
				if(productoVoConfigutarion.isProductIsSelected()) {
					if(productoVoConfigutarion.getCantidad() > 0) {
						listaProductos.add(productoVoConfigutarion);
					}
				}
			}
			if(listaProductos.isEmpty()) {
				return false;
			}
		}
		Calendar hoy = Calendar.getInstance();
		hoy.set(Calendar.HOUR_OF_DAY, 0);
		hoy.set(Calendar.MINUTE, 0);
		Date now = hoy.getTime();
		if(!fechaFinSelected.after(fechaInicioSelected)) {
			return false;
		}
		if(!fechaInicioSelected.after(now) || !fechaFinSelected.after(now)) {
			return false;
		}
		return true;
	}
	
	public void deleteConfiguracion() {
		if(configuracionSelected!=null && configuracionSelected.getIdConfiguracionVenta()!=null) {
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target(ConstantesView.hostPROD+"/api/configuraciones/delete");
			try { 
			    Map<String, Object> voJson = new HashMap<String, Object>();
			    voJson.put("idConfiguracion", configuracionSelected.getIdConfiguracionVenta());
			    Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.json(voJson));
			    init();
			} catch(Exception e) {
			    e.printStackTrace();
			    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
			}
	        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
		}
	}
	
	
	
	
}
