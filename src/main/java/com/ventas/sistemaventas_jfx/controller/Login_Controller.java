package com.ventas.sistemaventas_jfx.controller;

import com.ventas.sistemaventas_jfx.ResourcesLoader;
import com.ventas.sistemaventas_jfx.model.Login;
import com.ventas.sistemaventas_jfx.model.LoginDado;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.font.MFXFontIcon;

import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
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
	@FXML
	private Label lb_Mensaje;
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
		PauseTransition toast = new PauseTransition (Duration.seconds (5));
		
		if (username.isEmpty () || password.isEmpty ()) {
			lb_Mensaje.setText ("Campos vacios");
			txt_Username.requestFocus ();
			toast.setOnFinished (evento -> lb_Mensaje.setText (""));
			toast.play ();
			if (username.isEmpty ()) {
				lb_Mensaje.setText ("Campo usuario esta vacío");
				txt_Username.requestFocus ();
				toast.setOnFinished (evento -> lb_Mensaje.setText (""));
				toast.play ();
			} else {
				txt_Password.requestFocus ();
				lb_Mensaje.setText ("Campo Contraseña esta vacío");
				toast.setOnFinished (evento -> lb_Mensaje.setText (""));
				toast.play ();
			}
		} else {
			newLogin = newLoginDado.getLogin (username, password);
			if (newLogin.getUsername () != null || newLogin.getPassword () != null) {
				try {
					Stage stage = new Stage ();
					FXMLLoader loader = new FXMLLoader (ResourcesLoader.loadURL ("view/Dashboard_view.fxml"));
					loader.setControllerFactory (c -> new Dashboard_Controller (stage));
					Parent root = loader.load ();
					Dashboard_Controller controller = loader.getController ();
					Scene scene = new Scene (root);
					scene.setFill (Color.TRANSPARENT);
					stage.initStyle (StageStyle.TRANSPARENT);
					stage.setScene (scene);
					stage.show ();
					
					stage.setOnCloseRequest (e -> controller.closeWindows ());
					Stage myStage = (Stage) this.btn_Login.getScene ().getWindow ();
					myStage.close ();
				} catch (IOException e) {
					System.out.println ("Error en la ventana");
				}
				
			} else {
				txt_Username.setText ("");
				txt_Password.setText ("");
				lb_Mensaje.setText ("Usuario o Contraseña incorrecto");
				txt_Username.requestFocus ();
				toast.setOnFinished (evento -> lb_Mensaje.setText (""));
				toast.play ();
			}
		}
	}
	
	
}
