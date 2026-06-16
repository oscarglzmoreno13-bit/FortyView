package com.mx.forty.dto.vo;

public class DireccionPedidoViewVo {

	private Integer idDireccionPedido;
	private String calle;
	private String entreCalles;
	private String referencias;
	private Integer idColonia;
	private String nombreColonia;
	public String getNombreColonia() {
		return nombreColonia;
	}
	public void setNombreColonia(String nombreColonia) {
		this.nombreColonia = nombreColonia;
	}
	private String numeroExterior;
	private String numeroInterior;
	public String getNumeroInterior() {
		return numeroInterior;
	}
	public void setNumeroInterior(String numeroInterior) {
		this.numeroInterior = numeroInterior;
	}
	public Integer getIdDireccionPedido() {
		return idDireccionPedido;
	}
	public void setIdDireccionPedido(Integer idDireccionPedido) {
		this.idDireccionPedido = idDireccionPedido;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getEntreCalles() {
		return entreCalles;
	}
	public void setEntreCalles(String entreCalles) {
		this.entreCalles = entreCalles;
	}
	public String getReferencias() {
		return referencias;
	}
	public void setReferencias(String referencias) {
		this.referencias = referencias;
	}
	public Integer getIdColonia() {
		return idColonia;
	}
	public void setIdColonia(Integer idColonia) {
		this.idColonia = idColonia;
	}
	public String getNumeroExterior() {
		return numeroExterior;
	}
	public void setNumeroExterior(String numeroExterior) {
		this.numeroExterior = numeroExterior;
	}
}
