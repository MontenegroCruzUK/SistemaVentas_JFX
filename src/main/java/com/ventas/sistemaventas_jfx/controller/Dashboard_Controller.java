package com.ventas.sistemaventas_jfx.controller;

import com.ventas.sistemaventas_jfx.ResourcesLoader;
import io.github.palexdev.materialfx.utils.ToggleButtonsUtil;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Dashboard_Controller implements Initializable {
	private final Stage stage;
	private double xOffset;
	private double yOffset;
	private final ToggleGroup toggleGroup;
	
	@FXML
	private AnchorPane rootPane;
	
	@FXML
	private HBox hB_Header;
	
	@FXML
	private MFXFontIcon alwaysOnTopIcon;
	
	@FXML
	private MFXFontIcon minimizeIcon;
	
	@FXML
	private MFXFontIcon closeIcon;
	
	@FXML
	private Label lb_NombreUsuario;
	
	@FXML
	private MFXScrollPane scrollPane;
	
	@FXML
	private VBox navBar;
	
	@FXML
	private StackPane contentPane;
	
	
	public Dashboard_Controller (Stage stage) {
		this.stage = stage;
		this.toggleGroup = new ToggleGroup ();
		ToggleButtonsUtil.addAlwaysOneSelectedSupport (toggleGroup);
	}
	
	@Override
	public void initialize (URL location, ResourceBundle resources) {
	
	}
	
	public void closeWindows () {
		try {
			Stage stage = new Stage ();
			FXMLLoader loader = new FXMLLoader (ResourcesLoader.loadURL ("view/Login_view.fxml"));
			loader.setControllerFactory (c -> new Dashboard_Controller (stage));
			Parent root = loader.load ();
			Login_Controller controller = loader.getController ();
			Scene scene = new Scene (root);
			stage.setScene (scene);
			stage.show ();
			Stage myStage = new Stage ();
			myStage.close ();
		} catch (IOException e) {
			System.out.println ("Error en la ventana");
		}
	}
	
	
}
