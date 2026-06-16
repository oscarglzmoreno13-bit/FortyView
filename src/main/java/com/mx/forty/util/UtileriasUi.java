package com.mx.forty.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mx.forty.dto.vo.CampaniaVoUi;
import com.mx.forty.dto.vo.ConfiguracionVentaVoView;
import com.mx.forty.dto.vo.DireccionPedidoViewVo;
import com.mx.forty.dto.vo.FormaPagoViewVo;
import com.mx.forty.dto.vo.PersonaViewVo;
import com.mx.forty.dto.vo.ProductoVoConfigutarion;

public class UtileriasUi implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static  Map<String, Object> convertCampaniaVoToJson(CampaniaVoUi ui) {
		Map<String, Object> json = new HashMap<>();
	    json.put("idCampania", ui.getIdCampania());
	    json.put("nombre", ui.getNombre());
	    json.put("descripcion", ui.getDescripcion());
	    json.put("idEstatus", ui.getIdEstatus());
	    json.put("nombreEstatus", ui.getNombreEstatus());
	    json.put("fechaInicio", ui.getFechaInicio());
	    json.put("fechaFin", ui.getFechaFin());
	    
	    return json;
	}
	
	public static CampaniaVoUi convertJsonToCampania(Map<String, Object> map) {
		CampaniaVoUi campania = new CampaniaVoUi();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy, HH:mm");
		campania.setDescripcion((String) map.get("descripcion"));
		campania.setIdCampania(map.get("idCampania")==null?null:((BigDecimal) map.get("idCampania")).intValue());
		campania.setIdEstatus(map.get("idEstatus")==null?ConstantesView.ESTATUS_GRAL_ACTIVO:((BigDecimal) map.get("idEstatus")).intValue());
		campania.setFechaFinString((String) map.get("fechaFin"));
		campania.setFechaInicioString((String) map.get("fechaInicio"));
		try {
			campania.setFechaFin(campania.getFechaFinString()==null?new Date():sdf.parse(campania.getFechaFinString()));
			campania.setFechaInicio(campania.getFechaInicioString()==null?new Date():sdf.parse(campania.getFechaInicioString()));
		} catch(Exception e) {
			campania.setFechaFin(new Date());
			campania.setFechaInicio(new Date());
		}
		
		campania.setNombre((String) map.get("nombre"));
		return campania;
	}
	
	public static  Map<String, Object> getVoConfiguracionVtaToMap(ConfiguracionVentaVoView vo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idTipoConfiguracion", vo.getIdTipoConfiguracion());
		map.put("nombre", vo.getNombre());
		map.put("idCampania", vo.getIdCampania());
		map.put("fechaInicio", vo.getFechaInicio());
		map.put("fechaFin", vo.getFechaFin());
		map.put("precio", vo.getPrevioVenta());
		map.put("idConfiguracionVenta", vo.getIdConfiguracionVenta());
		List<Map<String, Object>> lista = new ArrayList<Map<String,Object>>();
		for (ProductoVoConfigutarion det : vo.getListaProductos()) {
			lista.add(convertVoToJsonDetalleConfig(det));
		}
		map.put("listaProductos", lista);
		return map;
	}
	
	public static Map<String, Object> convertVoToJsonDetalleConfig(ProductoVoConfigutarion vo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idDetalleConfiguracion", vo.getIdDetalleConfiguracion() );
		map.put("idConfiguracion", vo.getIdCofiguracion());
		map.put("cantidad", vo.getCantidad());
		map.put("nombreProducto",vo.getNombre() );
		map.put("idProducto",vo.getIdProducto() );
		return map;
	}
	
	public static ConfiguracionVentaVoView convertJsonToCongiguracionVo(Map<String, Object> map) {
		ConfiguracionVentaVoView vo = new ConfiguracionVentaVoView();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy, HH:mm");
		String fechaString = null;
		vo.setIdConfiguracionVenta(((BigDecimal) map.get("idConfiguracion")).intValue());
		try {
			fechaString = (String) map.get("fechaFin");
			vo.setFechaFin(fechaString==null?new Date():sdf.parse(fechaString));
			fechaString = (String) map.get("fechaInicio");
			vo.setFechaInicio(fechaString==null?new Date():sdf.parse(fechaString));
		} catch(Exception e) {
			vo.setFechaFin(new Date());
			vo.setFechaInicio(new Date());
		}
		vo.setIdCampania(((BigDecimal) map.get("idCampania")).intValue());
		vo.setNombreCampania((String) map.get("nombreCampania"));
		vo.setIdTipoConfiguracion(((BigDecimal) map.get("idTipoConfiguracion")).intValue());
		vo.setNombreTipoConfiguracion((String) map.get("nombreTipoConfiguracion"));
		vo.setNombre((String) map.get("nombre"));
		vo.setPrevioVenta(((BigDecimal) map.get("precio")).doubleValue());
		List<ProductoVoConfigutarion> lista = new ArrayList<ProductoVoConfigutarion>();
		List<Map<String, Object>> lst = (List<Map<String, Object>>) map.get("listaProductos");
		for (Map<String, Object> detalleMap : lst) {
			lista.add(convertJsonToVoProductoConfig(detalleMap));
		}
		vo.setListaProductos(lista);
		return vo;
	}
	
	public static ProductoVoConfigutarion convertJsonToVoProductoConfig(Map<String, Object> map) {
		ProductoVoConfigutarion vo = new ProductoVoConfigutarion();
		vo.setIdCofiguracion(((BigDecimal) map.get("idConfiguracion")).intValue());
		vo.setCantidad(((BigDecimal) map.get("cantidad")).intValue());
		vo.setIdDetalleConfiguracion(((BigDecimal) map.get("idDetalleConfiguracion")).intValue());
		vo.setIdProducto(((BigDecimal) map.get("idProducto")).intValue());
		vo.setNombre((String) map.get("nombreProducto"));
		return vo;
	}
	
	public static Map<String, Object> convertVoToJsonPersonaPedido(PersonaViewVo vo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idPersona", vo.getIdPersona());
		map.put("nombre", vo.getNombre());
		map.put("apePat", vo.getApellidoPaterno());
		map.put("apeMat", vo.getApellidoMaterno());
		map.put("telefono", vo.getTelefono());
		map.put("correo", vo.getCorreo());
		return map;
	}
	
	public static Map<String, Object> convertVoToJsonDireccionPedido(DireccionPedidoViewVo vo) {
		Map<String, Object>  map = new HashMap<String, Object>();
		map.put("idDireccionPedido", vo.getIdDireccionPedido());
		map.put("calle", vo.getCalle());
		map.put("emtreCalles", vo.getEntreCalles());
		map.put("idColonia", vo.getIdColonia());
		map.put("nombreColonia", vo.getNombreColonia());
		map.put("numExterior", vo.getNumeroExterior());
		map.put("numInterior", vo.getNumeroInterior());
		map.put("referencias", vo.getReferencias());
		return map;
	}
	
	public static Map<String, Object> converVoToJsonPedidoDetalle(ConfiguracionVentaVoView vo) {
		 Map<String, Object> map = new HashMap<String, Object>();
		 map.put("idConfiguracionVenta", vo.getIdConfiguracionVenta());
		 map.put("nombreConfig", vo.getNombre());
		 return map;
	}
	
	public static Map<String, Object> convertVoToJsonPedidoFormaPago(FormaPagoViewVo vo, Double monto) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idFormaPago", vo.getIdFormaPago());
		map.put("linkPago", vo.getLinkPago());
		map.put("observaciones", vo.getObservaciones());
		map.put("monto", monto);
		return map;
	}
	

}
