package com.ventas.sistemaventas_jfx.model.supplier;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Supplier {
	private SimpleIntegerProperty id;
	private SimpleStringProperty ruc;
	private SimpleStringProperty name;
	private SimpleStringProperty phone;
	private SimpleStringProperty address;
	private SimpleStringProperty company_name;
	
	public Supplier () {
	}
	
	public Supplier (int id, String ruc, String name, String phone, String address, String company_name) {
		
		this.id = new SimpleIntegerProperty (id);
		this.ruc = new SimpleStringProperty (ruc);
		this.name = new SimpleStringProperty (name);
		this.phone = new SimpleStringProperty (phone);
		this.address = new SimpleStringProperty (address);
		this.company_name = new SimpleStringProperty (company_name);
	}
	
	public SimpleIntegerProperty getId () {
		return id;
	}
	
	public void setId (SimpleIntegerProperty id) {this.id = id;}
	
	public SimpleStringProperty getRuc () {
		return ruc;
	}
	
	public void setRuc (SimpleStringProperty ruc) {this.ruc = ruc;}
	
	public SimpleStringProperty getName () {
		return name;
	}
	
	public void setName (SimpleStringProperty name) {this.name = name;}
	
	public SimpleStringProperty getPhone () {
		return phone;
	}
	
	public void setPhone (SimpleStringProperty phone) {this.phone = phone;}
	
	public SimpleStringProperty getAddress () {
		return address;
	}
	
	public void setAddress (SimpleStringProperty address) {this.address = address;}
	
	public SimpleStringProperty getCompanyName () {
		return company_name;
	}
	
	public void setCompanyName (SimpleStringProperty companyName) {this.company_name = companyName;}
	
	
}
