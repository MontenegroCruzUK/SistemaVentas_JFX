package com.ventas.sistemaventas_jfx.controller;

import com.ventas.sistemaventas_jfx.model.Cliente;
import com.ventas.sistemaventas_jfx.model.ClienteDado;
import com.ventas.sistemaventas_jfx.model.Mensajes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.util.List;

import java.net.URL;
import java.util.ResourceBundle;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
		tbl_Clientes.getItems ().addAll (listaClientes);
		
	}
	
	@FXML
	public void agregarNuevoCliente (ActionEvent event) {
		System.out.println ("Estamos trabajando en ello");
	}
	
	
}
