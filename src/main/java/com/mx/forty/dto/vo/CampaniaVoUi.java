package com.mx.forty.dto.vo;

import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CampaniaVoUi {

	private Integer idCampania;
	
	private String nombre;
	
	private String descripcion;
	 
	private String nombreEstatus;
	private Integer idEstatus;
	private String fechaInicioString;
	private String fechaFinString;
	 
	public String getFechaInicioString() {
		return fechaInicioString;
	}

	public void setFechaInicioString(String fechaInicioString) {
		this.fechaInicioString = fechaInicioString;
	}

	public String getFechaFinString() {
		return fechaFinString;
	}

	public void setFechaFinString(String fechaFinString) {
		this.fechaFinString = fechaFinString;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	 public String getNombreEstatus() {
		return nombreEstatus;
	}

	public void setNombreEstatus(String nombreEstatus) {
		this.nombreEstatus = nombreEstatus;
	}

	public Integer getIdEstatus() {
		return idEstatus;
	}

	public void setIdEstatus(Integer idEstatus) {
		this.idEstatus = idEstatus;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yy HH:mm")
    private Date fechaInicio;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yy HH:mm")
    private Date fechaFin;
	 
	 public Integer getIdCampania() {
		return idCampania;
	}

	 public void setIdCampania(Integer idCampania) {
		 this.idCampania = idCampania;
	 }

	 public String getNombre() {
		 return nombre;
	 }

	 public void setNombre(String nombre) {
		 this.nombre = nombre;
	 }

	 public String getDescripcion() {
		 return descripcion;
	 }

	 public void setDescripcion(String descripcion) {
		 this.descripcion = descripcion;
	 }

	 public Date getFechaInicio() {
		 return fechaInicio;
	 }

	 public void setFechaInicio(Timestamp fechaInicio) {
		 this.fechaInicio = new Date(fechaInicio.getTime());
	 }

	 public Date getFechaFin() {
		 return fechaFin;
	 }

	 public void setFechaFin(Timestamp fechaFin) {
		 this.fechaFin = new Date(fechaFin.getTime());
	 }

}
