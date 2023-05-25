package com.ventas.sistemaventas_jfx.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Client {
	private SimpleIntegerProperty id;
	private SimpleStringProperty dni;
	private SimpleStringProperty name;
	private SimpleStringProperty phone;
	private SimpleStringProperty address;
	private SimpleStringProperty companyName;
	
	public Client () {}
	
	public Client (int id, String dni, String name, String phone, String address,
	               String companyName) {
		this.id = new SimpleIntegerProperty (id);
		this.dni = new SimpleStringProperty (dni);
		this.name = new SimpleStringProperty (name);
		this.phone = new SimpleStringProperty (phone);
		this.address = new SimpleStringProperty (address);
		this.companyName = new SimpleStringProperty (companyName);
	}
	
	public Client (String dni, String name, String phone, String address, String companyName) {
		this.dni = new SimpleStringProperty (dni);
		this.name = new SimpleStringProperty (name);
		this.phone = new SimpleStringProperty (phone);
		this.address = new SimpleStringProperty (address);
		this.companyName = new SimpleStringProperty (companyName);
	}
	
	public SimpleIntegerProperty getId () {
		return id;
	}
	
	public void setId (SimpleIntegerProperty id) {
		this.id = id;
	}
	
	public SimpleStringProperty getDni () {
		return dni;
	}
	
	public void setDni (SimpleStringProperty dni) {
		this.dni = dni;
	}
	
	public SimpleStringProperty getName () {
		return name;
	}
	
	public void setName (SimpleStringProperty name) {
		this.name = name;
	}
	
	public SimpleStringProperty getPhone () {
		return phone;
	}
	
	public void setPhone (SimpleStringProperty phone) {
		this.phone = phone;
	}
	
	public SimpleStringProperty getAddress () {
		return address;
	}
	
	public void setAddress (SimpleStringProperty address) {
		this.address = address;
	}
	
	public SimpleStringProperty getCompanyName () {
		return companyName;
	}
	
	public void setCompanyName (SimpleStringProperty companyName) {
		this.companyName = companyName;
	}
	
}
