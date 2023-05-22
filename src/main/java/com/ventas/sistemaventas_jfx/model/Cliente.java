package com.ventas.sistemaventas_jfx.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Cliente {
	private SimpleIntegerProperty id_cliente;
	private SimpleStringProperty dni;
	private SimpleStringProperty nombre;
	private SimpleStringProperty telefono;
	private SimpleStringProperty direccion;
	private SimpleStringProperty razonSocial;
	
	public Cliente () {}
	
	public Cliente (int id_cliente, String dni, String nombre, String telefono, String direccion,
	                String razonSocial) {
		this.id_cliente = new SimpleIntegerProperty (id_cliente);
		this.dni = new SimpleStringProperty (dni);
		this.nombre = new SimpleStringProperty (nombre);
		this.telefono = new SimpleStringProperty (telefono);
		this.direccion = new SimpleStringProperty (direccion);
		this.razonSocial = new SimpleStringProperty (razonSocial);
	}
	
	public Cliente (String dni, String nombre, String telefono, String direccion, String razonSocial) {
		this.dni = new SimpleStringProperty (dni);
		this.nombre = new SimpleStringProperty (nombre);
		this.telefono = new SimpleStringProperty (telefono);
		this.direccion = new SimpleStringProperty (direccion);
		this.razonSocial = new SimpleStringProperty (razonSocial);
	}
	
	public SimpleIntegerProperty getId_cliente () {
		return id_cliente;
	}
	
	public void setId_cliente (SimpleIntegerProperty id_cliente) {
		this.id_cliente = id_cliente;
	}
	
	public SimpleStringProperty getDni () {
		return dni;
	}
	
	public void setDni (SimpleStringProperty dni) {
		this.dni = dni;
	}
	
	public SimpleStringProperty getNombre () {
		return nombre;
	}
	
	public void setNombre (SimpleStringProperty nombre) {
		this.nombre = nombre;
	}
	
	public SimpleStringProperty getTelefono () {
		return telefono;
	}
	
	public void setTelefono (SimpleStringProperty telefono) {
		this.telefono = telefono;
	}
	
	public SimpleStringProperty getDireccion () {
		return direccion;
	}
	
	public void setDireccion (SimpleStringProperty direccion) {
		this.direccion = direccion;
	}
	
	public SimpleStringProperty getRazonSocial () {
		return razonSocial;
	}
	
	public void setRazonSocial (SimpleStringProperty razonSocial) {
		this.razonSocial = razonSocial;
	}
	
}
