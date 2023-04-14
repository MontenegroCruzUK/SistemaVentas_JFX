package com.ventas.sistemaventas_jfx.controller;

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
	void login(ActionEvent event) {
	
	}
}