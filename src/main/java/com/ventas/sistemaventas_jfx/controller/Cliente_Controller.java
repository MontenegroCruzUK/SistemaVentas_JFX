package com.ventas.sistemaventas_jfx.controller;

import com.ventas.sistemaventas_jfx.model.Cliente;
import com.ventas.sistemaventas_jfx.model.ClienteDado;
import com.ventas.sistemaventas_jfx.model.Mensajes;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.util.List;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class Cliente_Controller implements Initializable {
	Cliente cliente = new Cliente ();
	ClienteDado clienteDado = new ClienteDado ();
	Mensajes mensaje = new Mensajes ();
	
	@FXML
	private MFXTextField txt_Dni;
	
	@FXML
	private MFXTextField txt_Id;
	
	@FXML
	private MFXTextField txt_Nombre;
	
	@FXML
	private MFXTextField txt_Telefono;
	
	@FXML
	private MFXTextField txt_Direccion;
	
	@FXML
	private MFXTextField txt_RazonSocial;
	
	@FXML
	private MFXButton btn_Guardar;
	
	@FXML
	private MFXButton btn_Actualizar;
	
	@FXML
	private MFXButton btn_Eliminar;
	
	@FXML
	private TableView<Cliente> tbl_Clientes;
	
	@FXML
	private TableColumn<Cliente, Integer> tc_Id;
	
	@FXML
	private TableColumn<Cliente, String> tc_Dni;
	
	@FXML
	private TableColumn<Cliente, String> tc_Nombre;
	
	@FXML
	private TableColumn<Cliente, String> tc_Telefono;
	
	@FXML
	private TableColumn<Cliente, String> tc_Direccion;
	
	@FXML
	private TableColumn<Cliente, String> tc_RazonSocial;
	
	
	@Override
	public void initialize (URL location, ResourceBundle resources) {
		cargarDatosClientes ();
	}
	
	private void cargarDatosClientes () {
		tc_Id.setCellValueFactory (cellData -> cellData.getValue ().getId_cliente ().asObject ());
		tc_Dni.setCellValueFactory (cellData -> cellData.getValue ().getDni ());
		tc_Nombre.setCellValueFactory (cellData -> cellData.getValue ().getNombre ());
		tc_Telefono.setCellValueFactory (cellData -> cellData.getValue ().getTelefono ());
		tc_Direccion.setCellValueFactory (cellData -> cellData.getValue ().getDireccion ());
		tc_RazonSocial.setCellValueFactory (cellData -> cellData.getValue ().getRazonSocial ());
		ClienteDado clienteDado = new ClienteDado ();
		List listaClientes = clienteDado.listarClientes ();
		tbl_Clientes.getItems ().setAll (listaClientes);
		tbl_Clientes.setOnMouseClicked (this::seleccionarCamposTablaClientes);
		
	}
	
	private void seleccionarCamposTablaClientes (MouseEvent event) {
		cliente = tbl_Clientes.getSelectionModel ().getSelectedItem ();
		if (cliente != null) {
			txt_Id.setText (String.valueOf (cliente.getId_cliente ().get ()));
			txt_Dni.setText (cliente.getDni ().get ());
			txt_Nombre.setText (cliente.getNombre ().get ());
			txt_Telefono.setText (cliente.getTelefono ().get ());
			txt_Direccion.setText (cliente.getDireccion ().get ());
			txt_RazonSocial.setText (cliente.getRazonSocial ().get ());
		}
	}
	
	@FXML
	public void agregarNuevoCliente (ActionEvent event) {
		String dni = txt_Dni.getText ();
		String nombre = txt_Nombre.getText ();
		String telefono = txt_Telefono.getText ();
		String direccion = txt_Direccion.getText ();
		String razonSocial = txt_RazonSocial.getText ();
		
		if (! dni.isEmpty () || ! nombre.isEmpty () || ! telefono.isEmpty () || ! direccion.isEmpty () ||
			! razonSocial.isEmpty ()) {
			cliente.setDni (new SimpleStringProperty (dni));
			cliente.setNombre (new SimpleStringProperty (nombre));
			cliente.setTelefono (new SimpleStringProperty (telefono));
			cliente.setDireccion (new SimpleStringProperty (direccion));
			cliente.setRazonSocial (new SimpleStringProperty (razonSocial));
			clienteDado.registrarNuevoCliente (cliente);
			System.out.println ("Se registró correctamente");
			cargarDatosClientes ();
			limpiarCampos ();
		} else {
			System.out.println ("Fallo en el registro");
		}
	}
	
	private void limpiarCampos () {
		txt_Dni.setText ("");
		txt_Nombre.setText ("");
		txt_Telefono.setText ("");
		txt_Direccion.setText ("");
		txt_RazonSocial.setText ("");
	}
	
	
	public void eliminarCliente (ActionEvent actionEvent) {
		
		if (! txt_Id.getText ().isEmpty ()) {
			Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
			alert.setTitle ("Confirmar eliminación");
			alert.setHeaderText (null);
			alert.setContentText ("¿Está seguro de eliminar?");
			
			Optional<ButtonType> result = alert.showAndWait ();
			if (result.isPresent () && result.get () == ButtonType.OK) {
				int id = Integer.parseInt (txt_Id.getText ());
				System.out.println ("Este es el Id = " + id);
				clienteDado.eliminarCliente (id);
				limpiarCampos ();
				cargarDatosClientes ();
			}
		}
	}
	
	public void actualizarCliente (ActionEvent actionEvent) {
		if (txt_Id.getText ().isEmpty ()) {
			Alert alert = new Alert (Alert.AlertType.WARNING);
			alert.setTitle ("Advertencia");
			alert.setHeaderText (null);
			alert.setContentText ("Seleccione una fila");
			alert.showAndWait ();
		} else {
			if (! txt_Dni.getText ().isEmpty () || ! txt_Nombre.getText ().isEmpty () ||
				! txt_Telefono.getText ().isEmpty () || ! txt_Direccion.getText ().isEmpty () ||
				! txt_RazonSocial.getText ().isEmpty ()) {
				
				cliente.setDni (new SimpleStringProperty (cliente.getDni ().get ()));
				cliente.setDni (new SimpleStringProperty (cliente.getNombre ().get ()));
				cliente.setDni (new SimpleStringProperty (cliente.getTelefono ().get ()));
				cliente.setDni (new SimpleStringProperty (cliente.getDireccion ().get ()));
				cliente.setDni (new SimpleStringProperty (cliente.getRazonSocial ().get ()));
				cliente.setId_cliente (new SimpleIntegerProperty (cliente.getId_cliente ().get ()));
				clienteDado.modificarCliente (cliente);
				cargarDatosClientes ();
			} else {
				System.out.println ("Los campos están vacíos");
			}
		}
		
	}
}
