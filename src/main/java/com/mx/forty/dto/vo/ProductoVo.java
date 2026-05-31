package com.mx.forty.dto.vo;

public class ProductoVo {

private Integer idProducto;
	
	private String upc;
	
	private String sku;
	
	private String nombre;
	
	private String descripcion;
	
	private Double monto;
	
	private Double precioVenta;
	
	private Integer idMArca;
	private String nombreMarca;
	
	private Integer idEstatus;
	private String nombreEstatus;
	private boolean upcExistente;
	
	////datos para configuraciones de venta
	private Integer id;
	private Integer cantidad;
	
	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getId() {
		return getIdProducto();
	}

	public void setId(Integer id) {
		this.id = id;
		this.idProducto = id;
	}

	public boolean isUpcExistente() {
		return upcExistente;
	}

	public void setUpcExistente(boolean upcExistente) {
		this.upcExistente = upcExistente;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public String getUpc() {
		return upc;
	}

	public void setUpc(String upc) {
		this.upc = upc;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
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

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public Double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(Double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public Integer getIdMArca() {
		return idMArca;
	}

	public void setIdMArca(Integer idMArca) {
		this.idMArca = idMArca;
	}

	public String getNombreMarca() {
		return nombreMarca;
	}

	public void setNombreMarca(String nombreMarca) {
		this.nombreMarca = nombreMarca;
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

	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	private String urlImagen;
	
	private String clave;
	
}
