package com.ventas.sistemaventas_jfx.model.client;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Represents a client in the system.
 */

public class Client {
	private SimpleIntegerProperty id;
	private SimpleStringProperty dni;
	private SimpleStringProperty name;
	private SimpleStringProperty phone;
	private SimpleStringProperty address;
	private SimpleStringProperty companyName;
	
	/**
	 * Constructs an empty Client object.
	 */
	public Client () {}
	
	/**
	 * Constructs a Client object with the specified properties.
	 *
	 * @param id          the client ID
	 * @param dni         the client's DNI
	 * @param name        the client's name
	 * @param phone       the client's phone number
	 * @param address     the client's address
	 * @param companyName the client's company name
	 */
	
	public Client (int id, String dni, String name, String phone, String address, String companyName) {
		this.id = new SimpleIntegerProperty (id);
		this.dni = new SimpleStringProperty (dni);
		this.name = new SimpleStringProperty (name);
		this.phone = new SimpleStringProperty (phone);
		this.address = new SimpleStringProperty (address);
		this.companyName = new SimpleStringProperty (companyName);
	}
	
	/**
	 * Retrieves the ID of the client.
	 *
	 * @return the client ID
	 */
	public SimpleIntegerProperty getId () {
		return id;
	}
	/**
	 * Sets the ID of the client.
	 *
	 * @param id the client ID
	 */
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
