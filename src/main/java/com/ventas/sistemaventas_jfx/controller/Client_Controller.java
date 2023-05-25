package com.ventas.sistemaventas_jfx.controller;

import com.ventas.sistemaventas_jfx.model.Client;
import com.ventas.sistemaventas_jfx.model.GivenClient;
import com.ventas.sistemaventas_jfx.model.Message;
import com.ventas.sistemaventas_jfx.model.ValidateInformation;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;

import java.util.List;

import java.net.URL;
import java.util.ResourceBundle;

import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class Client_Controller implements Initializable {
	Client client = new Client ();
	GivenClient givenClient = new GivenClient ();
	Message message = new Message ();
	ValidateInformation validateData = new ValidateInformation ();
	
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
	private TableView<Client> tbl_Clients;
	
	@FXML
	private TableColumn<Client, Integer> col_Id;
	
	@FXML
	private TableColumn<Client, String> col_Dni;
	
	@FXML
	private TableColumn<Client, String> col_Name;
	
	@FXML
	private TableColumn<Client, String> col_Phone;
	
	@FXML
	private TableColumn<Client, String> col_Address;
	
	@FXML
	private TableColumn<Client, String> col_CompanyName;
	
	
	@Override
	public void initialize (URL location, ResourceBundle resources) {
		loadData ();
	}
	
	private void loadData () {
		col_Id.setCellValueFactory (cellData -> cellData.getValue ().getId ().asObject ());
		col_Dni.setCellValueFactory (cellData -> cellData.getValue ().getDni ());
		col_Name.setCellValueFactory (cellData -> cellData.getValue ().getName ());
		col_Phone.setCellValueFactory (cellData -> cellData.getValue ().getPhone ());
		col_Address.setCellValueFactory (cellData -> cellData.getValue ().getAddress ());
		col_CompanyName.setCellValueFactory (cellData -> cellData.getValue ().getCompanyName ());
		List<Client> listaClients = givenClient.loadData ();
		tbl_Clients.setItems (FXCollections.observableArrayList (listaClients));
		tbl_Clients.setOnMouseClicked (this::selectFieldsFromTable);
		
	}
	
	private void selectFieldsFromTable (MouseEvent event) {
		client = tbl_Clients.getSelectionModel ().getSelectedItem ();
		if (client != null) {
			txt_Id.setText (String.valueOf (client.getId ().get ()));
			txt_Dni.setText (client.getDni ().get ());
			txt_Name.setText (client.getName ().get ());
			txt_Phone.setText (client.getPhone ().get ());
			txt_Address.setText (client.getAddress ().get ());
			txt_CompanyName.setText (client.getCompanyName ().get ());
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
			message.error ("Error con el Cliente", "No se puede crear el Cliente");
			return;
		}
		if (! validateData.validateDni (dni)) {
			message.error ("Error", "DNI inválido");
			txt_Dni.setText ("");
			return;
		}
		
		if (! validateData.validateName (name)) {
			message.error ("Error", "Nombre inválido");
			txt_Name.setText ("");
			return;
		}
		
		if (! validateData.validatePhone (phone)) {
			message.error ("Error", "Teléfono inválido");
			txt_Phone.setText ("");
			return;
		}
		client.setDni (new SimpleStringProperty (dni));
		client.setName (new SimpleStringProperty (validateData.capitalizeWords (name)));
		client.setPhone (new SimpleStringProperty (phone));
		client.setAddress (new SimpleStringProperty (validateData.capitalizeWords (address)));
		client.setCompanyName (new SimpleStringProperty (validateData.capitalizeWords (companyName)));
		
		givenClient.newRecordInTheDataBase (client);
		
		message.informacion ("Creando nuevo Cliente", "El Cliente fue creado con éxito");
		loadData ();
		resetFields ();
		
		
	}
	
	private void resetFields () {
		txt_Id.setText ("");
		txt_Dni.setText ("");
		txt_Name.setText ("");
		txt_Phone.setText ("");
		txt_Address.setText ("");
		txt_CompanyName.setText ("");
	}
	
	
	public void delete () {
		
		if (! txt_Id.getText ().isEmpty ()) {
			if (message.informacion ("Confirmar eliminación", "¿Está seguro de eliminar?")) {
				int id = Integer.parseInt (txt_Id.getText ());
				System.out.println ("Este es el Id = " + id);
				givenClient.deleteInTheDataBase (id);
				resetFields ();
				loadData ();
			}
		}
	}
	
	public void upDate () {
		if (txt_Id.getText ().isEmpty ()) {
			Alert alert = new Alert (Alert.AlertType.WARNING);
			alert.setTitle ("Advertencia");
			alert.setHeaderText (null);
			alert.setContentText ("Seleccione una fila");
			alert.showAndWait ();
		} else {
			if (! txt_Dni.getText ().isEmpty () || ! txt_Name.getText ().isEmpty () ||
				! txt_Phone.getText ().isEmpty () || ! txt_Address.getText ().isEmpty () ||
				! txt_CompanyName.getText ().isEmpty ()) {
				
				client.setDni (new SimpleStringProperty (txt_Dni.getText ()));
				client.setName (new SimpleStringProperty (txt_Name.getText ()));
				client.setPhone (new SimpleStringProperty (txt_Phone.getText ()));
				client.setAddress (new SimpleStringProperty (txt_Address.getText ()));
				client.setCompanyName (new SimpleStringProperty (txt_CompanyName.getText ()));
				client.setId (new SimpleIntegerProperty (Integer.parseInt (txt_Id.getText ())));
				givenClient.modifyInTheDataBase (client);
				loadData ();
				resetFields ();
			} else {
				System.out.println ("Los campos están vacíos");
			}
		}
	}
	
	@FXML
	public void cancel () {
		resetFields ();
		
	}
}
