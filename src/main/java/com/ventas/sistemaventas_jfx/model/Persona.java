package com.ventas.sistemaventas_jfx.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	public SimpleIntegerProperty get_Id () {
		return new SimpleIntegerProperty (id);
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
	
	
	public boolean validarNombre (String name) {
		String regexName = "[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s]+";
		Pattern patternName = Pattern.compile (regexName);
		Matcher matcherName = patternName.matcher (name);
		return matcherName.matches ();
	}
	
	public String ponerEnMayusculas (String name) {
		StringBuilder result = new StringBuilder ();
		boolean capitalize = true;
		
		for (char c : name.toCharArray ()) {
			if (Character.isWhitespace (c)) {
				capitalize = true;
			} else if (capitalize) {
				c = Character.toTitleCase (c);
				capitalize = false;
			}
			result.append (c);
		}
		return result.toString ();
	}
	
	public boolean validarTelefono (String phone) {
		// Validar que el teléfono solo contenga números y esté en el rango adecuado
		String phoneRegex = "^\\d{9}$"; // Expresión regular para 9 dígitos
		
		if (! phone.matches (phoneRegex)) {
			Mensaje.error ("Teléfono inválido", "El teléfono debe tener 9 dígitos numéricos");
			return false;
		}
		return true;
	}
}
