package com.ventas.sistemaventas_jfx.model.proveedores;


import com.ventas.sistemaventas_jfx.model.Mensaje;
import com.ventas.sistemaventas_jfx.model.Persona;
import javafx.beans.property.SimpleStringProperty;

import javax.print.attribute.standard.MediaName;

public class Proveedor extends Persona {
	private String ruc;
	
	public Proveedor () {
	}
	
	public String getRuc () {
		return ruc;
	}
	
	public void setRuc (String ruc) {
		this.ruc = ruc;
	}
	
	public SimpleStringProperty get_Ruc () {
		return new SimpleStringProperty (ruc);
	}
	public boolean validarRuc (String ruc) {
		// Validar que el teléfono solo contenga números y esté en el rango adecuado
		String rucRegex = "^\\d{13}$"; // Expresión regular para 9 dígitos
		
		if (! ruc.matches (rucRegex)) {
			Mensaje.error ("RUC inválido", "El RUC debe tener 13 dígitos numéricos");
			return false;
		}
		return true;
	}
	
}
