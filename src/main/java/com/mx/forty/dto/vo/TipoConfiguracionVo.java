package com.mx.forty.dto.vo;

import java.io.Serializable;

public class TipoConfiguracionVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer idTipoConfiguracion;
	private String clave;
	public Integer getIdTipoConfiguracion() {
		return idTipoConfiguracion;
	}
	public void setIdTipoConfiguracion(Integer idTipoConfiguracion) {
		this.idTipoConfiguracion = idTipoConfiguracion;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	private String nombre;

}
