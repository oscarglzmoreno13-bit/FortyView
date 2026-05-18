package com.mx.forty.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.mx.forty.dto.vo.CampaniaVoUi;

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
		campania.setIdEstatus(map.get("idEstatus")==null?Constantes.ESTATUS_GRAL_ACTIVO:((BigDecimal) map.get("idEstatus")).intValue());
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

}
