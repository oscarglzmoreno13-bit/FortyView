package com.mx.forty.dto.vo;

public class EstatusVo {

	private Integer idEstatus;
	private String clave;
	private String nombre;
	private String nombreTipoEstatus;
	
	public String getNombreTipoEstatus() {
		return nombreTipoEstatus;
	}
	public void setNombreTipoEstatus(String nombreTipoEstatus) {
		this.nombreTipoEstatus = nombreTipoEstatus;
	}
	public Integer getIdEstatus() {
		return idEstatus;
	}
	public void setIdEstatus(Integer idEstatus) {
		this.idEstatus = idEstatus;
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
}
