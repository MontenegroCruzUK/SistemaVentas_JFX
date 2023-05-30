package com.ventas.sistemaventas_jfx.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateInformation {
	Message message = new Message ();
	
	
	public boolean validateDni (String dni) {
		String regexDni = "\\b\\d{8}[A-HJ-NP-TV-Z]\\b";
		Pattern patternDni = Pattern.compile (regexDni);
		Matcher matcherDni = patternDni.matcher (dni);
		return matcherDni.matches ();
	}
	
	public boolean validateName (String name) {
		String regexName = "[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s]+";
		Pattern patternName = Pattern.compile (regexName);
		Matcher matcherName = patternName.matcher (name);
		return matcherName.matches ();
	}
	
	public String capitalizeWords (String name) {
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
	
	public boolean validatePhone (String phone) {
		// Validar que el teléfono solo contenga números y esté en el rango adecuado
		String phoneRegex = "^\\d{9}$"; // Expresión regular para 9 dígitos
		
		if (! phone.matches (phoneRegex)) {
			message.error ("Teléfono inválido", "El teléfono debe tener 9 dígitos numéricos");
			return false;
		}
		return true;
	}
	
	public boolean validateRuc (String ruc) {
		// Validar que el teléfono solo contenga números y esté en el rango adecuado
		String phoneRegex = "^\\d{13}$"; // Expresión regular para 9 dígitos
		
		if (! ruc.matches (phoneRegex)) {
			message.error ("RUC inválido", "El RUC debe tener 13 dígitos numéricos");
			return false;
		}
		return true;
	}
	
	public String validaRuc (String ruc) {
		// Validar que el RUC solo contenga números y tenga una longitud adecuada
		String rucRegex = "^\\d{13}$"; // Expresión regular para 13 dígitos
		
		if (! ruc.matches (rucRegex)) {
			message.error ("RUC inválido", "El RUC debe tener 13 dígitos numéricos");
			return "";
		}
		
		return ruc;
	}
	
	public String validaPhone (String phone) {
		// Validar que el teléfono solo contenga números y tenga una longitud adecuada
		String phoneRegex = "^\\d{9}$"; // Expresión regular para 9 dígitos
		
		if (! phone.matches (phoneRegex)) {
			message.error ("Teléfono inválido", "El teléfono debe tener 9 dígitos numéricos");
			return "";
		}
		
		return phone;
	}
}
