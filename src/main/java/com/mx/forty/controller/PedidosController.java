package com.mx.forty.controller;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.primefaces.event.FlowEvent;

import com.mx.forty.dto.vo.CampaniaVoUi;
import com.mx.forty.dto.vo.ColoniaUiVo;
import com.mx.forty.dto.vo.ConfiguracionVentaVoView;
import com.mx.forty.dto.vo.DireccionPedidoViewVo;
import com.mx.forty.dto.vo.EstadoUiVo;
import com.mx.forty.dto.vo.FormaPagoViewVo;
import com.mx.forty.dto.vo.MunicipioUiVo;
import com.mx.forty.dto.vo.PersonaViewVo;
import com.mx.forty.dto.vo.ProductoVoConfigutarion;
import com.mx.forty.dto.vo.TipoFormaPagoViewVo;
import com.mx.forty.util.ConstantesView;
import com.mx.forty.util.UtileriasUi;

@Named("pedidosController")
@ViewScoped
public class PedidosController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 private boolean skip;
	 private List<EstadoUiVo> listaEstados;
	 private List<MunicipioUiVo> listaMunicipios;
	 private List<ColoniaUiVo> listaColonias;
	 private EstadoUiVo estadoSelected;
	 private MunicipioUiVo mcpioSelected;
	 private ColoniaUiVo coloniaSelected;
	 private List<ColoniaUiVo> listaCp;
	 private String cpSeleccionado;
	 private List<CampaniaVoUi> listaCampanias;
	 private CampaniaVoUi campaniaSelected;
	 private List<ConfiguracionVentaVoView> listaConfiguraciones;
	 private ConfiguracionVentaVoView configuracionSelected;
	 private List<ProductoVoConfigutarion> listaDetalle;
	 private List<TipoFormaPagoViewVo> listaTipoFormaPago;
	 private TipoFormaPagoViewVo tipoFormaPagoSelected;
	 private List<FormaPagoViewVo> listaFormasPagoGral;
	 private List<FormaPagoViewVo> listaMostrarFormasPago;
	 private FormaPagoViewVo formaPagoSelected;
	 private PersonaViewVo personaPedido;
	 private List<PersonaViewVo> listaPersonas;
	 private PersonaViewVo personaSelected;
	 private String nombre;
	 private DireccionPedidoViewVo direccionPedido;
	 private List<PersonaViewVo> listaEjecutivos;
	 private PersonaViewVo ejecutivoSelected;
	 private Date hoy = new Date();
	 
	 public List<PersonaViewVo> getListaEjecutivos() {
		return listaEjecutivos;
	}

	 public void setListaEjecutivos(List<PersonaViewVo> listaEjecutivos) {
		 this.listaEjecutivos = listaEjecutivos;
	 }

	 public PersonaViewVo getEjecutivoSelected() {
		 return ejecutivoSelected;
	 }

	 public void setEjecutivoSelected(PersonaViewVo ejecutivoSelected) {
		 this.ejecutivoSelected = ejecutivoSelected;
	 }

	 public DireccionPedidoViewVo getDireccionPedido() {
		return direccionPedido;
	}

	 public void setDireccionPedido(DireccionPedidoViewVo direccionPedido) {
		 this.direccionPedido = direccionPedido;
	 }

	 public String getNombre() {
		return nombre;
	}

	 public void setNombre(String nombre) {
		 this.nombre = nombre;
	 }

	 public List<PersonaViewVo> getListaPersonas() {
		return listaPersonas;
	}

	 public void setListaPersonas(List<PersonaViewVo> listaPersonas) {
		 this.listaPersonas = listaPersonas;
	 }

	 public PersonaViewVo getPersonaSelected() {
		 return personaSelected;
	 }

	 public void setPersonaSelected(PersonaViewVo personaSelected) {
		 this.personaSelected = personaSelected;
	 }

	 public PersonaViewVo getPersonaPedido() {
		return personaPedido;
	}

	 public void setPersonaPedido(PersonaViewVo personaPedido) {
		 this.personaPedido = personaPedido;
	 }

	 public List<TipoFormaPagoViewVo> getListaTipoFormaPago() {
		return listaTipoFormaPago;
	}

	 public void setListaTipoFormaPago(List<TipoFormaPagoViewVo> listaTipoFormaPago) {
		 this.listaTipoFormaPago = listaTipoFormaPago;
	 }

	 public TipoFormaPagoViewVo getTipoFormaPagoSelected() {
		 return tipoFormaPagoSelected;
	 }

	 public void setTipoFormaPagoSelected(TipoFormaPagoViewVo tipoFormaPagoSelected) {
		 this.tipoFormaPagoSelected = tipoFormaPagoSelected;
	 }

	 public List<FormaPagoViewVo> getListaFormasPagoGral() {
		 return listaFormasPagoGral;
	 }

	 public void setListaFormasPagoGral(List<FormaPagoViewVo> listaFormasPagoGral) {
		 this.listaFormasPagoGral = listaFormasPagoGral;
	 }

	 public List<FormaPagoViewVo> getListaMostrarFormasPago() {
		 return listaMostrarFormasPago;
	 }

	 public void setListaMostrarFormasPago(List<FormaPagoViewVo> listaMostrarFormasPago) {
		 this.listaMostrarFormasPago = listaMostrarFormasPago;
	 }

	 public FormaPagoViewVo getFormaPagoSelected() {
		 return formaPagoSelected;
	 }

	 public void setFormaPagoSelected(FormaPagoViewVo formaPagoSelected) {
		 this.formaPagoSelected = formaPagoSelected;
	 }

	 private List<ConfiguracionVentaVoView> listaConfiguracionesAgregadas;
	 public List<ConfiguracionVentaVoView> getListaConfiguracionesAgregadas() {
		return listaConfiguracionesAgregadas;
	}

	 public void setListaConfiguracionesAgregadas(List<ConfiguracionVentaVoView> listaConfiguracionesAgregadas) {
		 this.listaConfiguracionesAgregadas = listaConfiguracionesAgregadas;
	 }

	 public Double getTotalPedido() {
		 return totalPedido;
	 }

	 public void setTotalPedido(Double totalPedido) {
		 this.totalPedido = totalPedido;
	 }

	 private Double totalPedido;
	
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

	 public ConfiguracionVentaVoView getConfiguracionSelected() {
		 return configuracionSelected;
	 }

	 public void setConfiguracionSelected(ConfiguracionVentaVoView configuracionSelected) {
		 this.configuracionSelected = configuracionSelected;
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

	 public List<ColoniaUiVo> getListaCp() {
		return listaCp;
	}

	 public void setListaCp(List<ColoniaUiVo> listaCp) {
		 this.listaCp = listaCp;
	 }

	 public String getCpSeleccionado() {
		 return cpSeleccionado;
	 }

	 public void setCpSeleccionado(String cpSeleccionado) {
		 this.cpSeleccionado = cpSeleccionado;
	 }

	 public List<EstadoUiVo> getListaEstados() {
		return listaEstados;
	}

	 public void setListaEstados(List<EstadoUiVo> listaEstados) {
		 this.listaEstados = listaEstados;
	 }

	 public List<MunicipioUiVo> getListaMunicipios() {
		 return listaMunicipios;
	 }

	 public void setListaMunicipios(List<MunicipioUiVo> listaMunicipios) {
		 this.listaMunicipios = listaMunicipios;
	 }

	 public List<ColoniaUiVo> getListaColonias() {
		 return listaColonias;
	 }

	 public void setListaColonias(List<ColoniaUiVo> listaColonias) {
		 this.listaColonias = listaColonias;
	 }

	 public EstadoUiVo getEstadoSelected() {
		 return estadoSelected;
	 }

	 public void setEstadoSelected(EstadoUiVo estadoSelected) {
		 this.estadoSelected = estadoSelected;
	 }

	 public MunicipioUiVo getMcpioSelected() {
		 return mcpioSelected;
	 }

	 public void setMcpioSelected(MunicipioUiVo mcpioSelected) {
		 this.mcpioSelected = mcpioSelected;
	 }

	 public ColoniaUiVo getColoniaSelected() {
		 return coloniaSelected;
	 }

	 public void setColoniaSelected(ColoniaUiVo coloniaSelected) {
		 this.coloniaSelected = coloniaSelected;
	 }

	 public boolean isSkip() {
	        return skip;
	    }

	    public void setSkip(boolean skip) {
	        this.skip = skip;
	    }
	    
	    
	@PostConstruct
    public void init() {
		inicializaDatos();
		buscaEstados();
		buscaCampanias();
		buscaTiposFormaPago();
		buscaEjecutivos();
		personaPedido = new PersonaViewVo();
		direccionPedido = new DireccionPedidoViewVo();
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
	 
	public Date getHoy() {
		return hoy;
	}

	 public void setHoy(Date hoy) {
		 this.hoy = hoy;
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
	
	public void viewDetailConfiguration(ConfiguracionVentaVoView configuracion) {
		configuracionSelected = null;
		if(configuracion.isSelected()) {
			if(configuracion!=null &&
					configuracion.getListaProductos()!=null && 
					!configuracion.getListaProductos().isEmpty()) {
					listaDetalle = new ArrayList<ProductoVoConfigutarion>();
					listaDetalle = configuracion.getListaProductos();
					configuracionSelected = configuracion;
				}
			if(listaConfiguracionesAgregadas==null) {
				listaConfiguracionesAgregadas = new ArrayList<ConfiguracionVentaVoView>();
			}
			listaConfiguracionesAgregadas.add(configuracion);
		} else {
			configuracionSelected = null;
			listaDetalle = new ArrayList<ProductoVoConfigutarion>();
			listaConfiguracionesAgregadas.remove(configuracion);
			List<ConfiguracionVentaVoView> lista = new ArrayList<ConfiguracionVentaVoView>();
			for (ConfiguracionVentaVoView configuracionVentaVoView : listaConfiguracionesAgregadas) {
				if(!configuracionVentaVoView.getIdConfiguracionVenta().equals(configuracion.getIdConfiguracionVenta())) {
					lista.add(configuracionVentaVoView);
				}
			}
			listaConfiguracionesAgregadas = new ArrayList<ConfiguracionVentaVoView>();
			listaConfiguracionesAgregadas.addAll(lista);
		}
		actualizaMontoPedido();
	}
	 
	 

	 private void actualizaMontoPedido() {
		// TODO Auto-generated method stub
		if(listaConfiguracionesAgregadas!=null) {
			totalPedido = new Double(0);
			for (ConfiguracionVentaVoView configuracionVentaVoView : listaConfiguracionesAgregadas) {
				totalPedido = configuracionVentaVoView.getPrevioVenta() + totalPedido;
			}
		}
	}

	 public void buscaEstados() {
		// TODO Auto-generated method stub
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(ConstantesView.hostPROD+"/api/pedidos/findEdos");
		listaEstados = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<EstadoUiVo>>() {});
	}
	 
	 public void buscaEjecutivos() {
			// TODO Auto-generated method stub
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target(ConstantesView.hostPROD+"/api/pedidos/buscaEjecutivos");
			listaEjecutivos = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<PersonaViewVo>>() {});
		}
	 
	public void buscaTiposFormaPago() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(ConstantesView.hostPROD+"/api/pedidos/findTipoFormaPago");
		listaTipoFormaPago = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<TipoFormaPagoViewVo>>() {});
		tipoFormaPagoSelected = new TipoFormaPagoViewVo();
		formaPagoSelected = new FormaPagoViewVo();
	}
	
	public void buscaFormaPago() {
		if(listaFormasPagoGral == null ) {
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target(ConstantesView.hostPROD+"/api/pedidos/findFormasPago");
			listaFormasPagoGral = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<FormaPagoViewVo>>() {});
		}
		if(tipoFormaPagoSelected!=null && tipoFormaPagoSelected.getIdTipoFormaPago()!=null) {
			listaMostrarFormasPago = new ArrayList<FormaPagoViewVo>();
			for (FormaPagoViewVo forma : listaFormasPagoGral) {
				if(forma.getIdTipoFormaPago().equals(tipoFormaPagoSelected.getIdTipoFormaPago())) {
					listaMostrarFormasPago.add(forma);
				}
			}
		}
		formaPagoSelected = new FormaPagoViewVo();
	}
	

	 private void inicializaDatos() {
		 listaEstados = new ArrayList<EstadoUiVo>();
		 listaMunicipios = new ArrayList<MunicipioUiVo>();
		 listaColonias = new ArrayList<ColoniaUiVo>();
		 estadoSelected = new EstadoUiVo();
		 mcpioSelected = new MunicipioUiVo();
		 coloniaSelected = new ColoniaUiVo();
		 personaPedido = new PersonaViewVo();
		 ejecutivoSelected = new PersonaViewVo();
		 ZonedDateTime ahoraCDMX = ZonedDateTime.now(ZoneId.of("America/Mexico_City"));
		 hoy = Date.from(ahoraCDMX.toInstant());
	}
	
	 public void buscaPersonas() {
		 if(personaPedido!=null) {
			 Client client = ClientBuilder.newClient();
			 WebTarget target = client.target(ConstantesView.hostPROD+"/api/pedidos/findPersonaLikeNombre")
			                          .queryParam("nombre", personaPedido.getNombre()==null
			                          							?""
			                          							:personaPedido.getNombre()); // ejemplo idEstado=5

			 listaPersonas = target.request(MediaType.APPLICATION_JSON)
			                         .get(new GenericType<List<PersonaViewVo>>() {});
			 personaPedido = new PersonaViewVo();
		 }
		  
	 }
	 
	 public void buscaMunicipios() {
		 if(estadoSelected!=null && estadoSelected.getIdEstado()!=null) {
			 Client client = ClientBuilder.newClient();
			 WebTarget target = client.target(ConstantesView.hostPROD+"/api/pedidos/findMcpiosByIdEdo")
			                          .queryParam("idEstado", estadoSelected.getIdEstado()); // ejemplo idEstado=5

			 listaMunicipios = target.request(MediaType.APPLICATION_JSON)
			                         .get(new GenericType<List<MunicipioUiVo>>() {});
			 mcpioSelected = new MunicipioUiVo();
			 listaCp = new ArrayList<ColoniaUiVo>();
			 listaColonias = new ArrayList<ColoniaUiVo>();
			 cpSeleccionado = new String();
			 coloniaSelected = new ColoniaUiVo();
		 }
	 }
	 
	 public void buscaColoniasCp() {
		 if(mcpioSelected != null && mcpioSelected.getIdMunicipio()!=null) {
			 Client client = ClientBuilder.newClient();
			 WebTarget target = client.target(ConstantesView.hostPROD+"/api/pedidos/findColByIdMcpio")
			                          .queryParam("idMcpio", mcpioSelected.getIdMunicipio()); // ejemplo idEstado=5

			 listaCp = target.request(MediaType.APPLICATION_JSON)
			                         .get(new GenericType<List<ColoniaUiVo>>() {});
			 cpSeleccionado = new String("");
			 listaColonias = new ArrayList<ColoniaUiVo>();
			 coloniaSelected = new ColoniaUiVo();
		 }
	 }
	 
	 public void buscaColoniasByCp() {
		 if(cpSeleccionado!=null && !cpSeleccionado.equals("")) {
			 listaColonias = new ArrayList<ColoniaUiVo>();
			 for (ColoniaUiVo coloniaUiVo : listaCp) {
				if(coloniaUiVo.getCp().trim().equals(cpSeleccionado.trim())) {
					listaColonias.add(coloniaUiVo);
				}
			}
			 coloniaSelected = new ColoniaUiVo();
		 }
	 }

	 public void savePedido() {
		 if(validaDatos()) {
			 Map<String, Object> mapa = new HashMap<String, Object>();
			 mapa.put("datosPersona", UtileriasUi.convertVoToJsonPersonaPedido(personaSelected));
			 mapa.put("direccionPedido", UtileriasUi.convertVoToJsonDireccionPedido(direccionPedido));
		 }
	 }
	 
	 private boolean validaDatos() {
		// TODO Auto-generated method stub
		 if(personaPedido==null) {
			 return false;
		 } else {
			 if(personaPedido.getNombre()==null || 
					 (personaPedido.getNombre()!=null && personaPedido.getNombre().equals(""))) {
				 return false;
			 }
			 if(personaPedido.getTelefono()==null || 
					 (personaPedido.getTelefono()!=null && personaPedido.getTelefono().equals(""))) {
				 return false;
			 }
		 }
		 if(direccionPedido==null) {
			 return false;
		 } else {
			 if(coloniaSelected==null ||
					 (coloniaSelected!=null && coloniaSelected.getIdColonia()==null) ||
					 (coloniaSelected!=null && coloniaSelected.getIdColonia()!=null && 
							 coloniaSelected.getIdColonia().equals("0"))) {
				 return false;
			 }
			 if(direccionPedido.getCalle()==null || 
					 (direccionPedido.getCalle()!=null && direccionPedido.getCalle().equals(""))) {
				 return false;
			 }
		 }
		 if(totalPedido==null) {
			 return false;
		 } else {
			 if(totalPedido!=null && totalPedido<=0) {
				 return false;
			 }
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

	 public String onFlowProcess(FlowEvent event) {
	        if (skip) {
	            skip = false; //reset in case user goes back
	            return "confirm";
	        }
	        else {
	            return event.getNewStep();
	        }
	    }
}
