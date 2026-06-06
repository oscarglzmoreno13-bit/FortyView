package com.mx.forty.dto.vo;

import java.util.Date;

public class PedidoViewVo {

	private Integer idPedido;
	private String nombre;
	private String fecha;
	private Double monto;
	private String telefono;
	private String estatus;
	private String pedidoEcartpay;
	private Date fechaCreated;
	private String lastName;
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getFechaCreated() {
		return fechaCreated;
	}
	public void setFechaCreated(Date fechaCreated) {
		this.fechaCreated = fechaCreated;
	}
	public String getPedidoEcartpay() {
		return pedidoEcartpay;
	}
	public void setPedidoEcartpay(String pedidoEcartpay) {
		this.pedidoEcartpay = pedidoEcartpay;
	}
	public Integer getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
}
