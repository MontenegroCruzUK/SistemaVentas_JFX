package com.ventas.sistemaventas_jfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class App extends Application {
	@Override
	public void start (Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader (App.class.getResource ("view/Login_view.fxml"));
		stage.initStyle (StageStyle.UNDECORATED);
		Scene scene = new Scene (fxmlLoader.load ());
		stage.setScene (scene);
		stage.show ();
	}
	
	public static void main (String[] args) {
		launch ();
	}
}