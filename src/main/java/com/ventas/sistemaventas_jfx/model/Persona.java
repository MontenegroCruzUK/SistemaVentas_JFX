package com.ventas.sistemaventas_jfx.model;

public class Persona {
	private int id;
	
	private String name;
	private String phone;
	private String address;
	private String companyName;
	
	public Persona () {
	}
	
	public Persona (int id, String name, String phone, String address, String companyName) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.companyName = companyName;
	}
	
	public int getId () {
		return id;
	}
	
	public void setId (int id) {
		this.id = id;
	}
	
	public String getName () {
		return name;
	}
	
	public void setName (String name) {
		this.name = name;
	}
	
	public String getPhone () {
		return phone;
	}
	
	public void setPhone (String phone) {
		this.phone = phone;
	}
	
	public String getAddress () {
		return address;
	}
	
	public void setAddress (String address) {
		this.address = address;
	}
	
	public String getCompanyName () {
		return companyName;
	}
	
	public void setCompanyName (String companyName) {
		this.companyName = companyName;
	}
}
