package com.ventas.sistemaventas_jfx.controller;

import com.ventas.sistemaventas_jfx.model.DataBaseConnection;
import com.ventas.sistemaventas_jfx.model.Login;
import com.ventas.sistemaventas_jfx.model.LoginDado;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import javafx.scene.layout.AnchorPane;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Login_Controller implements Initializable {
	@FXML
	private AnchorPane rootPane;
	
	@FXML
	private MFXTextField txt_Username;
	
	@FXML
	private MFXPasswordField txt_Password;
	
	@FXML
	private MFXButton btn_Login;
	
	@FXML
	private HBox hB_Header;
	
	@FXML
	private MFXFontIcon minimizeIcon;
	
	@FXML
	private MFXFontIcon closeIcon;
	Login newLogin = new Login ();
	LoginDado newLoginDado = new LoginDado ();
	
	
	@Override
	public void initialize (URL location, ResourceBundle resources) {
		actionButtonHeader ();
		
	}
	
	private void actionButtonHeader () {
		closeIcon.addEventHandler (javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> Platform.exit ());
		minimizeIcon.addEventHandler (MouseEvent.MOUSE_CLICKED,
			event -> ((Stage) rootPane.getScene ().getWindow ()).setIconified (true));
	}
	
	@FXML
	public void login (ActionEvent event) {
		String username = txt_Username.getText ().trim ();
		String password = String.valueOf (txt_Password.getText ().trim ());
		
		if (username.isEmpty () || password.isEmpty ()) {
			System.out.println ("Campos vacios");
			if (username.isEmpty ()) {
				txt_Username.requestFocus ();
			} else {
				txt_Password.requestFocus ();
			}
		} else {
			newLogin = newLoginDado.getLogin (username, password);
			if (newLogin.getUsername () != null || newLogin.getPassword () != null) {
				System.out.println ("Estas dentro del sistema Ventas");
			} else {
				System.out.println ("NOooooooooo Estas Dentro revisa bien todo");
				txt_Username.setText ("");
				txt_Password.setText ("");
			}
		}
	}
}
