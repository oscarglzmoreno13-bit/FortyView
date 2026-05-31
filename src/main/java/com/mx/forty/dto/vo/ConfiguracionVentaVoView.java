package com.mx.forty.dto.vo;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ConfiguracionVentaVoView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idConfiguracionVenta;
	private String nombre;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yy HH:mm")
	private Date fechaInicio;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yy HH:mm")
	private Date fechaFin;
	private Double previoVenta;
	private Integer idEstatus;
	private String nombreEstatus;
	private Integer idTipoConfiguracion;
	private Integer idCampania;
	private String nombreCampania;
	private String nombreTipoConfiguracion;
	private boolean selected;
	
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public String getNombreTipoConfiguracion() {
		return nombreTipoConfiguracion;
	}
	public void setNombreTipoConfiguracion(String nombreTipoConfiguracion) {
		this.nombreTipoConfiguracion = nombreTipoConfiguracion;
	}
	public String getNombreCampania() {
		return nombreCampania;
	}
	public void setNombreCampania(String nombreCampania) {
		this.nombreCampania = nombreCampania;
	}
	private List<ProductoVoConfigutarion> listaProductos;
	
	public Integer getIdCampania() {
		return idCampania;
	}
	public void setIdCampania(Integer idCampania) {
		this.idCampania = idCampania;
	}
	public List<ProductoVoConfigutarion> getListaProductos() {
		return listaProductos;
	}
	public void setListaProductos(List<ProductoVoConfigutarion> listaProductos) {
		this.listaProductos = listaProductos;
	}
	public Integer getIdTipoConfiguracion() {
		return idTipoConfiguracion;
	}
	public void setIdTipoConfiguracion(Integer idTipoConfiguracion) {
		this.idTipoConfiguracion = idTipoConfiguracion;
	}
	public Integer getIdConfiguracionVenta() {
		return idConfiguracionVenta;
	}
	public void setIdConfiguracionVenta(Integer idConfiguracionVenta) {
		this.idConfiguracionVenta = idConfiguracionVenta;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public Double getPrevioVenta() {
		return previoVenta;
	}
	public void setPrevioVenta(Double previoVenta) {
		this.previoVenta = previoVenta;
	}
	public Integer getIdEstatus() {
		return idEstatus;
	}
	public void setIdEstatus(Integer idEstatus) {
		this.idEstatus = idEstatus;
	}
	public String getNombreEstatus() {
		return nombreEstatus;
	}
	public void setNombreEstatus(String nombreEstatus) {
		this.nombreEstatus = nombreEstatus;
	}
}
