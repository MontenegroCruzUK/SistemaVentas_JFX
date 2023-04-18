package com.ventas.sistemaventas_jfx.controller;

import com.ventas.sistemaventas_jfx.ResourcesLoader;
import io.github.palexdev.materialfx.controls.MFXIconWrapper;
import io.github.palexdev.materialfx.controls.MFXRectangleToggleNode;
import io.github.palexdev.materialfx.utils.ScrollUtils;
import io.github.palexdev.materialfx.utils.ToggleButtonsUtil;
import io.github.palexdev.materialfx.utils.others.loader.MFXLoader;
import io.github.palexdev.materialfx.utils.others.loader.MFXLoaderBean;
import javafx.application.Platform;
import javafx.css.PseudoClass;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
		actionButtonHeader ();
		
	}
	
	private void actionButtonHeader () {
		closeIcon.addEventHandler (MouseEvent.MOUSE_CLICKED, event -> Platform.exit ());
		minimizeIcon.addEventHandler (MouseEvent.MOUSE_CLICKED,
			event -> ((Stage) rootPane.getScene ().getWindow ()).setIconified (true));
		this.alwaysOnTopIcon.addEventHandler (MouseEvent.MOUSE_CLICKED, (event) -> {
			boolean newVal = ! this.stage.isAlwaysOnTop ();
			this.alwaysOnTopIcon.pseudoClassStateChanged (PseudoClass.getPseudoClass ("always-on-top"), newVal);
			this.stage.setAlwaysOnTop (newVal);
		});
		this.hB_Header.setOnMousePressed ((event) -> {
			this.xOffset = this.stage.getX () - event.getScreenX ();
			this.yOffset = this.stage.getY () - event.getScreenY ();
		});
		this.hB_Header.setOnMouseDragged ((event) -> {
			this.stage.setX (event.getScreenX () + this.xOffset);
			this.stage.setY (event.getScreenY () + this.yOffset);
		});
		this.initializeLoader ();
		ScrollUtils.addSmoothScrolling (this.scrollPane);
	}
	
	private void initializeLoader () {
		MFXLoader loader = new MFXLoader ();
		
		loader.addView (MFXLoaderBean.of ("Nueva Venta",
			ResourcesLoader.loadURL ("view/NuevaVenta_view.fxml")).setBeanToNodeMapper (() -> {
			return this.createToggle ("mfx-square-pen", "Nueva Venta");
		}).setDefaultRoot (true).get ());
		
		loader.addView (
			MFXLoaderBean.of ("Clientes", ResourcesLoader.loadURL ("view/Clientes_view.fxml")).setBeanToNodeMapper (
				() -> createToggle ("mfx-users", "Clientes")).get ());
		
		loader.addView (MFXLoaderBean.of ("Proveedores",
			ResourcesLoader.loadURL ("view/Proveedores_view.fxml")).setBeanToNodeMapper (
			() -> createToggle ("mfx-spreadsheet", "Proveedores")).get ());
		
		loader.addView (MFXLoaderBean.of ("Productos",
			ResourcesLoader.loadURL ("view/Productos_view.fxml")).setBeanToNodeMapper (
			() -> createToggle ("mfx-square-list", "Productos")).get ());
		
		loader.addView (
			MFXLoaderBean.of ("Ventas", ResourcesLoader.loadURL ("view/Ventas_view.fxml")).setBeanToNodeMapper (
				() -> createToggle ("mfx-google-fusion-tables", "Ventas")).get ());
		
		loader.addView (MFXLoaderBean.of ("Configuracion",
			ResourcesLoader.loadURL ("view/Configuracion_view.fxml")).setBeanToNodeMapper (
			() -> createToggle ("mfx-gear", "Configuración")).get ());
		
		loader.setOnLoadedAction ((beans) -> {
			List<ToggleButton> nodes = beans.stream ().map ((bean) -> {
				ToggleButton toggle = (ToggleButton) bean.getBeanToNodeMapper ().get ();
				toggle.setOnAction ((event) -> {
					this.contentPane.getChildren ().setAll (bean.getRoot ());
				});
				if (bean.isDefaultView ()) {
					this.contentPane.getChildren ().setAll (bean.getRoot ());
					toggle.setSelected (true);
				}
				
				return toggle;
			}).toList ();
			this.navBar.getChildren ().setAll (nodes);
		});
		loader.start ();
	}
	
	private ToggleButton createToggle (String icon, String text) {
		MFXIconWrapper wrapper = new MFXIconWrapper (icon, 24.0, 32.0);
		MFXRectangleToggleNode toggleNode = new MFXRectangleToggleNode (text, wrapper);
		toggleNode.setAlignment (Pos.CENTER_LEFT);
		toggleNode.setToggleGroup (this.toggleGroup);
		
		return toggleNode;
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
