package com.ventas.sistemaventas_jfx;

import com.ventas.sistemaventas_jfx.controller.Login_Controller;
import com.ventas.sistemaventas_jfx.model.ResourcesLoader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class App extends Application {
	@Override
	public void start (Stage stage) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader (App.class.getResource ("view/Login_view.fxml"));
			stage.initStyle (StageStyle.UNDECORATED);
			Scene scene = new Scene (fxmlLoader.load ());
			stage.setScene (scene);
			stage.show ();
		} catch (IOException e) {
			System.out.println ("Error en la ventana");
		}
	}
	
	public static void main (String[] args) {
		launch ();
	}
}