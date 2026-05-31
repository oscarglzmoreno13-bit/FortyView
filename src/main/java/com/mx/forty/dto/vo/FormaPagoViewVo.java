package com.mx.forty.dto.vo;

public class FormaPagoViewVo {

	private Integer idFormaPago;
	private String nombre;
	private Integer idTipoFormaPago;
	
	private String linkPago;
	private String observaciones; 
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getLinkPago() {
		return linkPago;
	}
	public void setLinkPago(String linkPago) {
		this.linkPago = linkPago;
	}
	public Integer getIdFormaPago() {
		return idFormaPago;
	}
	public void setIdFormaPago(Integer idFormaPago) {
		this.idFormaPago = idFormaPago;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getIdTipoFormaPago() {
		return idTipoFormaPago;
	}
	public void setIdTipoFormaPago(Integer idTipoFormaPago) {
		this.idTipoFormaPago = idTipoFormaPago;
	}
}
