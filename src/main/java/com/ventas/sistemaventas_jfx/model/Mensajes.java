package com.ventas.sistemaventas_jfx.model;

import javafx.scene.control.Alert;

public class Mensajes {
	
	public void confirmacion (String titulo, String mensaje) {
		Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
		alert.setTitle (titulo);
		alert.setHeaderText (null);
		alert.setContentText (mensaje);
		alert.showAndWait ();
	}
	
	public void error (String titulo, String mensaje) {
		Alert alert = new Alert (Alert.AlertType.ERROR);
		alert.setTitle (titulo);
		alert.setHeaderText (null);
		alert.setContentText (mensaje);
		alert.showAndWait ();
	}
	
	public void informacion (String titulo, String mensaje) {
		Alert alert = new Alert (Alert.AlertType.INFORMATION);
		alert.setTitle (titulo);
		alert.setHeaderText (null);
		alert.setContentText (mensaje);
		alert.showAndWait ();
	}
}
