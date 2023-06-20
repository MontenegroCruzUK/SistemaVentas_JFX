package com.ventas.sistemaventas_jfx.model.client;

import com.ventas.sistemaventas_jfx.model.Persona;
import javafx.beans.binding.BooleanExpression;

public class Cliente extends Persona {
	private String dni;
	
	public Cliente () {}
	
	public Cliente (int id, String name, String phone, String address, String companyName, String dni) {
		super (id, name, phone, address, companyName);
		this.dni = dni;
	}
	
	public String getDni () {
		return dni;
	}
	
	public void setDni (String dni) {
		this.dni = dni;
	}
	
	public BooleanExpression idProperty () {
		return null;
	}
}
