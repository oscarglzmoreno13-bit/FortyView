package com.mx.forty.util;

import java.io.Serializable;

public class ConstantesView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
//	public static String hostPROD = "http://3.129.16.75:8080/demo";
	public static String hostPROD = "http://localhost:8080/demo";
	public static Integer ESTATUS_GRAL_ACTIVO = 1;
	public static final int columna_status=0;
	public static final int columna_amount=1;
	public static final int columna_telefono=6;
	public static final int columna_orderNum=43;
	public static final int columna_fecha_Pay = 46;
	public static final int columna_nombre = 4;
	public static final int columna_lastName = 5;
	
	public static final String estatus_pagado_ecarPay = "paid";
	public static final String estatus_Pagado="Pagado";
	public static final String estatus_Pendiente="NO";
	public static final String forma_pago_COD = "CONTRA ENTREGA";

}
