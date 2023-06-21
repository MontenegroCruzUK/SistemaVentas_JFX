package com.ventas.sistemaventas_jfx.controller;

import com.ventas.sistemaventas_jfx.model.Mensaje;
import com.ventas.sistemaventas_jfx.model.client.Cliente;
import com.ventas.sistemaventas_jfx.model.client.DameCliente;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class Cliente_Controller implements Initializable {
	Cliente clientes = new Cliente ();
	DameCliente dameCliente = new DameCliente ();
	
	
	@FXML
	private MFXTextField txt_Dni;
	
	@FXML
	private MFXTextField txt_Id;
	
	@FXML
	private MFXTextField txt_Name;
	
	@FXML
	private MFXTextField txt_Phone;
	
	@FXML
	private MFXTextField txt_Address;
	
	@FXML
	private MFXTextField txt_CompanyName;
	
	@FXML
	private MFXButton btn_Add;
	
	@FXML
	private TableView<Cliente> tbl_Clients;
	
	@FXML
	private TableColumn<Cliente, Integer> col_Id;
	
	@FXML
	private TableColumn<Cliente, String> col_Dni;
	
	@FXML
	private TableColumn<Cliente, String> col_Name;
	
	@FXML
	private TableColumn<Cliente, String> col_Phone;
	
	@FXML
	private TableColumn<Cliente, String> col_Address;
	
	@FXML
	private TableColumn<Cliente, String> col_CompanyName;
	
	
	@Override
	public void initialize (URL location, ResourceBundle resources) {
		cargarClientes ();
		
		
	}
	
	private void cargarClientes () {
		col_Id.setCellValueFactory (cellData -> cellData.getValue ().get_Id ().asObject ());
		col_Dni.setCellValueFactory (cellData -> cellData.getValue ().get_Dni ());
		col_Name.setCellValueFactory (cellData -> cellData.getValue ().get_Name ());
		col_Phone.setCellValueFactory (cellData -> cellData.getValue ().get_Phone ());
		col_Address.setCellValueFactory (cellData -> cellData.getValue ().get_Address ());
		col_CompanyName.setCellValueFactory (cellData -> cellData.getValue ().get_CompanyName ());
		
		List<Cliente> listaClientes = dameCliente.obtenerClientes ();
		tbl_Clients.setItems (FXCollections.observableArrayList (listaClientes));
		tbl_Clients.setOnMouseClicked (this::selectFieldsFromTable);
	}
	
	
	private void selectFieldsFromTable (MouseEvent event) {
		btn_Add.setDisable (true);
		clientes = tbl_Clients.getSelectionModel ().getSelectedItem ();
		if (clientes != null) {
			txt_Id.setText (String.valueOf (clientes.getId ()));
			txt_Dni.setText (clientes.getDni ());
			txt_Name.setText (clientes.getName ());
			txt_Phone.setText (clientes.getPhone ());
			txt_Address.setText (clientes.getAddress ());
			txt_CompanyName.setText (clientes.getCompanyName ());
		}
	}
	
	@FXML
	public void add () {
		String dni = txt_Dni.getText ();
		String name = txt_Name.getText ();
		String phone = txt_Phone.getText ();
		String address = txt_Address.getText ();
		String companyName = txt_CompanyName.getText ();
		
		if (dni.isEmpty () || name.isEmpty () || phone.isEmpty () || address.isEmpty () ||
			companyName.isEmpty ()) {
			Mensaje.error ("Error con el Cliente", "No se puede crear el Cliente");
			return;
		}
		if (! clientes.validarDni (dni)) {
			Mensaje.error ("Error", "DNI inválido");
			txt_Dni.setText ("");
			return;
		}
		
		if (! clientes.validarNombre (name)) {
			Mensaje.error ("Error", "Nombre inválido");
			txt_Name.setText ("");
			return;
		}
		
		if (! clientes.validarTelefono (phone)) {
			Mensaje.error ("Error", "Teléfono inválido");
			txt_Phone.setText ("");
			return;
		}
		clientes.setDni (dni);
		clientes.setName (clientes.ponerEnMayusculas (name));
		clientes.setPhone (phone);
		clientes.setAddress (clientes.ponerEnMayusculas (address));
		clientes.setCompanyName (clientes.ponerEnMayusculas (companyName));
		dameCliente.nuevoUsuarioEnLaDB (clientes);
		Mensaje.information ("Creando nuevo Cliente", "El Cliente fue creado con éxito");
		cargarClientes ();
		limpiarCampos ();
	}
	
	private void limpiarCampos () {
		txt_Id.setText ("");
		txt_Dni.setText ("");
		txt_Name.setText ("");
		txt_Phone.setText ("");
		txt_Address.setText ("");
		txt_CompanyName.setText ("");
		btn_Add.setDisable (false);
	}
	
	
	public void delete () {
		if (! txt_Id.getText ().isEmpty ()) {
			if (Mensaje.information ("Confirmar eliminación", "¿Está seguro de eliminar?")) {
				int id = Integer.parseInt (txt_Id.getText ());
				System.out.println ("Este es el Id = " + id);
				dameCliente.borrarClienteDeLaDB (id);
				limpiarCampos ();
				cargarClientes ();
			}
		}
		
	}
	
	public void upDate () {
		if (txt_Id.getText ().isEmpty ()) {
			Mensaje.warning ("Advertencia", "Seleccione una fila");
			
		} else {
			if (! txt_Dni.getText ().isEmpty () || ! txt_Name.getText ().isEmpty () ||
				! txt_Phone.getText ().isEmpty () || ! txt_Address.getText ().isEmpty () ||
				! txt_CompanyName.getText ().isEmpty ()) {
				
				clientes.setDni (txt_Dni.getText ());
				clientes.setName (clientes.ponerEnMayusculas (txt_Name.getText ()));
				clientes.setPhone (txt_Phone.getText ());
				clientes.setAddress (clientes.ponerEnMayusculas (txt_Address.getText ()));
				clientes.setCompanyName (clientes.ponerEnMayusculas (txt_CompanyName.getText ()));
				clientes.setId (Integer.parseInt (txt_Id.getText ()));
				
				dameCliente.modificarClientesEnDB (clientes);
				cargarClientes ();
				limpiarCampos ();
			} else {
				System.out.println ("Los campos están vacíos");
			}
		}
		
	}
	
	@FXML
	public void cancel () {
		limpiarCampos ();
	}
}
