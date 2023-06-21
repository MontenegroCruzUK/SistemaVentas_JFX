package com.ventas.sistemaventas_jfx.model.client;

import com.ventas.sistemaventas_jfx.model.Persona;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cliente extends Persona {
	private String dni;
	
	
	public Cliente () {
	}
	
	public SimpleIntegerProperty get_Id () {
		return new SimpleIntegerProperty (getId ());
	}
	
	public String getDni () {
		return dni;
	}
	
	public SimpleStringProperty get_Dni () {
		return new SimpleStringProperty (dni);
	}
	
	public void setDni (String dni) {
		this.dni = dni;
	}
	
	public SimpleStringProperty get_Name () {
		return new SimpleStringProperty (getName ());
	}
	
	public SimpleStringProperty get_Phone () {
		return new SimpleStringProperty (getPhone ());
	}
	
	public SimpleStringProperty get_Address () {
		return new SimpleStringProperty (getAddress ());
	}
	
	public SimpleStringProperty get_CompanyName () {
		return new SimpleStringProperty (getCompanyName ());
	}
	
	public boolean validarDni(String dni){
		String regexDni = "\\b\\d{8}[A-HJ-NP-TV-Z]\\b";
		Pattern patternDni = Pattern.compile (regexDni);
		Matcher matcherDni = patternDni.matcher (dni);
		return matcherDni.matches ();
	}
	
}
