package com.ventas.sistemaventas_jfx.model;

public class Login {
	private int id_vendedor;
	private String correo;
	private String password;
	
	public Login () {}
	
	public Login (int id_vendedor, String correo, String password) {
		this.id_vendedor = id_vendedor;
		this.correo = correo;
		this.password = password;
	}
	
	public int getId_vendedor () {
		return id_vendedor;
	}
	
	public void setId_vendedor (int id_vendedor) {
		this.id_vendedor = id_vendedor;
	}
	
	public String getCorreo () {
		return correo;
	}
	
	public void setCorreo (String correo) {
		this.correo = correo;
	}
	
	public String getPassword () {
		return password;
	}
	
	public void setPassword (String password) {
		this.password = password;
	}
}
