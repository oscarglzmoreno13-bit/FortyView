package com.mx.forty.controller;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.mx.forty.dto.vo.CampaniaVoUi;
import com.mx.forty.dto.vo.ColoniaUiVo;
import com.mx.forty.dto.vo.ConfiguracionVentaVoView;
import com.mx.forty.dto.vo.DireccionPedidoViewVo;
import com.mx.forty.dto.vo.FormaPagoViewVo;
import com.mx.forty.dto.vo.PersonaViewVo;
import com.mx.forty.dto.vo.ProductoVoConfigutarion;
import com.mx.forty.dto.vo.TipoFormaPagoViewVo;
import com.mx.forty.util.ConstantesView;
import com.mx.forty.util.UtileriasUi;

import jakarta.annotation.PostConstruct;

@Named("capturaPedidosController")
@ViewScoped
public class CapturaPedidosController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PersonaViewVo personaPedido;
	private List<String> listaCp;
	private List<ColoniaUiVo> listaColonias;
	private String cpSeleccionado;
	private ColoniaUiVo coloniaSeleccioada;
	private DireccionPedidoViewVo direccionPedido;
	private List<CampaniaVoUi> listaCampanias;
	private CampaniaVoUi campaniaSelected;
	private List<ConfiguracionVentaVoView> listaConfiguraciones;
	private ConfiguracionVentaVoView configuraionSelected;
	private List<ConfiguracionVentaVoView> listaConfiguracionesSeleccionadas;
	private List<ProductoVoConfigutarion> listaDetalle;
	private Double montoTotal;
	private List<TipoFormaPagoViewVo> listaTiposFormaPago;
	private TipoFormaPagoViewVo tipoFormaPagoSelected;
	private List<FormaPagoViewVo> listaFormasPago;
	private List<FormaPagoViewVo> listaFormasPagoMostrar;
	private FormaPagoViewVo formaPagoSelected;
	
	public List<TipoFormaPagoViewVo> getListaTiposFormaPago() {
		return listaTiposFormaPago;
	}

	public void setListaTiposFormaPago(List<TipoFormaPagoViewVo> listaTiposFormaPago) {
		this.listaTiposFormaPago = listaTiposFormaPago;
	}

	public TipoFormaPagoViewVo getTipoFormaPagoSelected() {
		if(tipoFormaPagoSelected==null) {
			tipoFormaPagoSelected = new TipoFormaPagoViewVo();
		}
		return tipoFormaPagoSelected;
	}

	public void setTipoFormaPagoSelected(TipoFormaPagoViewVo tipoFormaPagoSelected) {
		this.tipoFormaPagoSelected = tipoFormaPagoSelected;
	}

	public List<FormaPagoViewVo> getListaFormasPago() {
		return listaFormasPago;
	}

	public void setListaFormasPago(List<FormaPagoViewVo> listaFormasPago) {
		this.listaFormasPago = listaFormasPago;
	}

	public List<FormaPagoViewVo> getListaFormasPagoMostrar() {
		return listaFormasPagoMostrar;
	}

	public void setListaFormasPagoMostrar(List<FormaPagoViewVo> listaFormasPagoMostrar) {
		this.listaFormasPagoMostrar = listaFormasPagoMostrar;
	}

	public FormaPagoViewVo getFormaPagoSelected() {
		if(formaPagoSelected==null) {
			formaPagoSelected = new FormaPagoViewVo();
		}
		return formaPagoSelected;
	}

	public void setFormaPagoSelected(FormaPagoViewVo formaPagoSelected) {
		this.formaPagoSelected = formaPagoSelected;
	}

	public Double getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(Double montoTotal) {
		this.montoTotal = montoTotal;
	}

	public List<ProductoVoConfigutarion> getListaDetalle() {
		return listaDetalle;
	}

	public void setListaDetalle(List<ProductoVoConfigutarion> listaDetalle) {
		this.listaDetalle = listaDetalle;
	}

	public List<ConfiguracionVentaVoView> getListaConfiguraciones() {
		return listaConfiguraciones;
	}

	public void setListaConfiguraciones(List<ConfiguracionVentaVoView> listaConfiguraciones) {
		this.listaConfiguraciones = listaConfiguraciones;
	}

	public ConfiguracionVentaVoView getConfiguraionSelected() {
		if(configuraionSelected==null) {
			configuraionSelected = new ConfiguracionVentaVoView();
		}
		return configuraionSelected;
	}

	public void setConfiguraionSelected(ConfiguracionVentaVoView configuraionSelected) {
		this.configuraionSelected = configuraionSelected;
	}

	public List<ConfiguracionVentaVoView> getListaConfiguracionesSeleccionadas() {
		return listaConfiguracionesSeleccionadas;
	}

	public void setListaConfiguracionesSeleccionadas(List<ConfiguracionVentaVoView> listaConfiguracionesSeleccionadas) {
		this.listaConfiguracionesSeleccionadas = listaConfiguracionesSeleccionadas;
	}

	public List<CampaniaVoUi> getListaCampanias() {
		return listaCampanias;
	}

	public void setListaCampanias(List<CampaniaVoUi> listaCampanias) {
		this.listaCampanias = listaCampanias;
	}

	public CampaniaVoUi getCampaniaSelected() {
		return campaniaSelected;
	}

	public void setCampaniaSelected(CampaniaVoUi campaniaSelected) {
		this.campaniaSelected = campaniaSelected;
	}

	public List<String> getListaCp() {
		return listaCp;
	}

	public void setListaCp(List<String> listaCp) {
		this.listaCp = listaCp;
	}

	public List<ColoniaUiVo> getListaColonias() {
		return listaColonias;
	}

	public void setListaColonias(List<ColoniaUiVo> listaColonias) {
		this.listaColonias = listaColonias;
	}

	public String getCpSeleccionado() {
		return cpSeleccionado;
	}

	public void setCpSeleccionado(String cpSeleccionado) {
		this.cpSeleccionado = cpSeleccionado;
	}

	public ColoniaUiVo getColoniaSeleccioada() {
		return coloniaSeleccioada;
	}

	public void setColoniaSeleccioada(ColoniaUiVo coloniaSeleccioada) {
		this.coloniaSeleccioada = coloniaSeleccioada;
	}

	public PersonaViewVo getPersonaPedido() {
		return personaPedido;
	}

	public void setPersonaPedido(PersonaViewVo personaPedido) {
		this.personaPedido = personaPedido;
	}

	@PostConstruct
	public void init() {
		if(personaPedido == null ) {personaPedido = new PersonaViewVo();}
		cpSeleccionado = new String();
		buscaCodigosPostales();
		coloniaSeleccioada = new ColoniaUiVo();
		buscaCampanias();
		campaniaSelected = new CampaniaVoUi();
		listaConfiguraciones = new ArrayList<ConfiguracionVentaVoView>();
		montoTotal = Double.valueOf(0);
		buscaTiposFormaPago();
		tipoFormaPagoSelected = new TipoFormaPagoViewVo();
	}
	
	public void validaSeleccionColonia() {
		if(coloniaSeleccioada!=null) {
			if(direccionPedido == null) {
				direccionPedido = new DireccionPedidoViewVo();
			}
			if(coloniaSeleccioada.getIdColonia()!=null) {
				for (ColoniaUiVo coloniaUiVo : listaColonias) {
					if(coloniaSeleccioada.getIdColonia().equals(coloniaUiVo.getIdColonia())) {
						coloniaSeleccioada = coloniaUiVo;
					}
				}
				direccionPedido.setIdColonia(coloniaSeleccioada.getIdColonia());
			}
		}
	}
	
	public DireccionPedidoViewVo getDireccionPedido() {
		if(direccionPedido==null) {
			direccionPedido = new DireccionPedidoViewVo();
		}
		return direccionPedido;
	}
	
	public void viewDetailConfiguration(ConfiguracionVentaVoView configuracion) {
		configuraionSelected = null;
		if(configuracion.isSelected()) {
			if(configuracion!=null &&
					configuracion.getListaProductos()!=null && 
					!configuracion.getListaProductos().isEmpty()) {
					listaDetalle = new ArrayList<ProductoVoConfigutarion>();
					listaDetalle = configuracion.getListaProductos();
					configuraionSelected = configuracion;
				}
			if(listaConfiguracionesSeleccionadas==null) {
				listaConfiguracionesSeleccionadas = new ArrayList<ConfiguracionVentaVoView>();
			}
			listaConfiguracionesSeleccionadas.add(configuracion);
		} else {
			configuraionSelected = null;
			listaDetalle = new ArrayList<ProductoVoConfigutarion>();
			listaConfiguracionesSeleccionadas.remove(configuracion);
			List<ConfiguracionVentaVoView> lista = new ArrayList<ConfiguracionVentaVoView>();
			for (ConfiguracionVentaVoView configuracionVentaVoView : listaConfiguracionesSeleccionadas) {
				if(!configuracionVentaVoView.getIdConfiguracionVenta().equals(configuracion.getIdConfiguracionVenta())) {
					lista.add(configuracionVentaVoView);
				}
			}
			listaConfiguracionesSeleccionadas = new ArrayList<ConfiguracionVentaVoView>();
			listaConfiguracionesSeleccionadas.addAll(lista);
		}
		actualizaMontoPedido();
	}
	
	private void actualizaMontoPedido() {
		// TODO Auto-generated method stub
		if(listaConfiguracionesSeleccionadas!=null) {
			montoTotal = Double.valueOf(0);
			for (ConfiguracionVentaVoView configuracionVentaVoView : listaConfiguracionesSeleccionadas) {
				montoTotal = configuracionVentaVoView.getPrevioVenta() + montoTotal;
			}
		}
	}

	public void setDireccionPedido(DireccionPedidoViewVo direccionPedido) {
		this.direccionPedido = direccionPedido;
	}

	private void buscaCodigosPostales() {
		// TODO Auto-generated method stub
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(ConstantesView.hostPROD+"/api/pedidos/buscaCodigosPostales");
		listaCp = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<String>>() {});
	}
	
	public void buscaColoniasByCp() {
		if(cpSeleccionado!=null && !cpSeleccionado.equals("")) {
			Client client = ClientBuilder.newClient();
			 WebTarget target = client.target(ConstantesView.hostPROD+"/api/pedidos/buscaColoniasByCp")
			                          .queryParam("codigoPostal", cpSeleccionado);
			listaColonias = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<ColoniaUiVo>>() {});
			coloniaSeleccioada = new ColoniaUiVo();
			
		}
	}
	
	 private void buscaCampanias() {
			// TODO Auto-generated method stub
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy, HH:mm");
		     LocalDate fecha = null;
	        LocalDate hoy = LocalDate.now();
	        
			 Client client = ClientBuilder.newClient();
				listaCampanias = new ArrayList<CampaniaVoUi>();
				WebTarget target = client.target(ConstantesView.hostPROD+"/api/campanias");
				List<Map<String, Object>> lst = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Map<String, Object>>>() {});
				for (Map<String, Object> map : lst) {
					CampaniaVoUi campania = UtileriasUi.convertJsonToCampania(map);
					fecha = LocalDate.parse(campania.getFechaFinString(), formatter);
					if(!fecha.isBefore(hoy)) {
						listaCampanias.add(campania);
					}
				}
				campaniaSelected = new CampaniaVoUi();
		}
	 
	 public void buscaConfiguracionesByCampania() {
			Client client = ClientBuilder.newClient();
			listaConfiguraciones  = new ArrayList<ConfiguracionVentaVoView>();
			 WebTarget target = client.target(ConstantesView.hostPROD+"/api/configuraciones/findActivesByCampania")
	                 .queryParam("idCampania", campaniaSelected.getIdCampania()); // ejemplo idEstado=5

			List<Map<String, Object>> lst = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Map<String, Object>>>() {});
			for (Map<String, Object> map : lst) {
				listaConfiguraciones.add(UtileriasUi.convertJsonToCongiguracionVo(map));
			}
		}

	 public void buscaTiposFormaPago() {
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target(ConstantesView.hostPROD+"/api/pedidos/findTipoFormaPago");
			listaTiposFormaPago = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<TipoFormaPagoViewVo>>() {});
		}
		
		public void buscaFormaPago() {
			if(listaFormasPago == null ) {
				Client client = ClientBuilder.newClient();
				WebTarget target = client.target(ConstantesView.hostPROD+"/api/pedidos/findFormasPago");
				listaFormasPago = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<FormaPagoViewVo>>() {});
			}
			if(tipoFormaPagoSelected!=null && tipoFormaPagoSelected.getIdTipoFormaPago()!=null) {
				listaFormasPagoMostrar = new ArrayList<FormaPagoViewVo>();
				for (FormaPagoViewVo forma : listaFormasPago) {
					if(forma.getIdTipoFormaPago().equals(tipoFormaPagoSelected.getIdTipoFormaPago())) {
						listaFormasPagoMostrar.add(forma);
					}
				}
			}
			formaPagoSelected = new FormaPagoViewVo();
			formaPagoSelected.setIdTipoFormaPago(tipoFormaPagoSelected.getIdTipoFormaPago());
		}
		
		public void selectFormaPago() {
			System.out.println(formaPagoSelected.getIdFormaPago());
		}
		
	 
	public void save() {
		if(validaDatos()) {
			Map<String, Object> json = new HashMap<String, Object>();
			json.put("", UtileriasUi.convertVoToJsonPersonaPedido(personaPedido));
			json.put("", UtileriasUi.convertVoToJsonDireccionPedido(direccionPedido));
			List<Map<String, Object>> listaCongi = new ArrayList<Map<String,Object>>();
			for (ConfiguracionVentaVoView configuracionVentaVoView : listaConfiguracionesSeleccionadas) {
				listaCongi.add(UtileriasUi.converVoToJsonPedidoDetalle(configuracionVentaVoView));
			}
			json.put("", listaCongi);
			json.put("", UtileriasUi.convertVoToJsonPedidoFormaPago(formaPagoSelected, montoTotal));
			System.out.println();
		}
	}

	private boolean validaDatos() {
		if(personaPedido==null) {
			return false;
		} else {
			if(personaPedido.getNombre()==null && 
					(personaPedido.getNombre()!=null && personaPedido.getNombre().equals(""))) {
				return false;
			}
			if(personaPedido.getTelefono()==null && 
					(personaPedido.getTelefono()!=null && personaPedido.getTelefono().equals(""))) {
				return false;
			}
		}
		if(direccionPedido==null) {
			return false;
		} else {
			if(direccionPedido.getIdColonia()==null || 
					(direccionPedido.getIdColonia()!=null && direccionPedido.getIdColonia().equals(0))) {
				return false;
			}
			if(direccionPedido.getCalle()==null || 
					(direccionPedido.getCalle()!=null && direccionPedido.getCalle().equals(""))) {
				return false;
			}
			if(direccionPedido.getNumeroExterior()==null || 
					(direccionPedido.getNumeroExterior()!=null && direccionPedido.getNumeroExterior().equals(""))) {
				return false;
			}
		}
		if(listaConfiguracionesSeleccionadas==null ||
				(listaConfiguracionesSeleccionadas!=null && listaConfiguracionesSeleccionadas.isEmpty())) {
			return false;
		}
		if(formaPagoSelected==null) {
			return false;
		} else {
			if(formaPagoSelected.getIdFormaPago()==null || 
					(formaPagoSelected.getIdFormaPago()!=null && formaPagoSelected.getIdFormaPago().equals(0))) {
				return false;
			}
				
		}
		return true;
	}

}
