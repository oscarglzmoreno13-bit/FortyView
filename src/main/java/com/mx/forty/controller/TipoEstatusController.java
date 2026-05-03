package com.mx.forty.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;

@Named("tipoEstatusController")
@SessionScoped
public class TipoEstatusController implements Serializable {

	private String mensaje = "Hola Óscar desde JSF!";
    private List<String> lista = new ArrayList<>();

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<String> getLista() {
        return lista;
    }

    public void agregarElemento(String valor) {
        lista.add(valor);
    }
	
}
