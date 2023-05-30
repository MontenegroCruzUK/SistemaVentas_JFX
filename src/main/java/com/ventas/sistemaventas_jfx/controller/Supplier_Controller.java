package com.ventas.sistemaventas_jfx.controller;

import com.ventas.sistemaventas_jfx.model.Message;
import com.ventas.sistemaventas_jfx.model.ValidateInformation;
import com.ventas.sistemaventas_jfx.model.supplier.GivenSupplier;
import com.ventas.sistemaventas_jfx.model.supplier.Supplier;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Supplier_Controller implements Initializable {
	
	Supplier supplier = new Supplier ();
	GivenSupplier givenSupplier = new GivenSupplier ();
	Message message = new Message ();
	ValidateInformation validateData = new ValidateInformation ();
	@FXML
	public MFXButton btn_Save;
	@FXML
	public MFXButton btn_UpDate;
	@FXML
	public MFXButton btn_Delete;
	@FXML
	public MFXButton btn_Cancel;
	@FXML
	private MFXTextField txt_Id;
	@FXML
	private MFXTextField txt_Ruc;
	@FXML
	private MFXTextField txt_Name;
	@FXML
	private MFXTextField txt_Phone;
	@FXML
	private MFXTextField txt_Address;
	@FXML
	private MFXTextField txt_CompanyName;
	@FXML
	private TableView<Supplier> tbl_Supplier;
	@FXML
	private TableColumn<Supplier, Integer> col_Id;
	@FXML
	private TableColumn<Supplier, String> col_Ruc;
	@FXML
	private TableColumn<Supplier, String> col_Name;
	@FXML
	private TableColumn<Supplier, String> col_Phone;
	@FXML
	private TableColumn<Supplier, String> col_Address;
	@FXML
	private TableColumn<Supplier, String> col_CompanyName;
	
	@Override
	public void initialize (URL location, ResourceBundle resources) {
		loadData ();
	}
	
	@FXML
	public void add (ActionEvent actionEvent) {
		String ruc = txt_Ruc.getText ();
		String name = txt_Name.getText ();
		String phone = txt_Phone.getText ();
		String address = txt_Address.getText ();
		String companyName = txt_CompanyName.getText ();
		if (checkFields ()) {
			message.error ("Error con el Proveedor", "No se puede crear el Proveedor");
			return;
		}
		if (! validateData.validateRuc (ruc)) {
			message.error ("Error con el Proveedor", "Ruc inválido");
			txt_Ruc.setText ("");
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
		
		supplier.setRuc (new SimpleStringProperty (ruc));
		supplier.setName (new SimpleStringProperty (validateData.capitalizeWords (name)));
		supplier.setPhone (new SimpleStringProperty (phone));
		supplier.setAddress (new SimpleStringProperty (validateData.capitalizeWords (address)));
		supplier.setCompanyName (new SimpleStringProperty (validateData.capitalizeWords (companyName)));
		givenSupplier.createNewSupplier (supplier);
		message.information ("Creando nuevo Cliente", "El Cliente fue creado con éxito");
		loadData ();
		resetFields ();
		
	}
	
	
	@FXML
	public void upDate (ActionEvent actionEvent) {
		if (txt_Id.getText ().isEmpty ()) {
			message.warning ("Advertencia", "Seleccione una fila");
		} else {
			if (! checkFields ()) {
				supplier.setRuc (new SimpleStringProperty (validateData.validaRuc (txt_Ruc.getText ())));
				supplier.setName (new SimpleStringProperty (validateData.capitalizeWords (txt_Name.getText ())));
				supplier.setPhone (new SimpleStringProperty (validateData.validaPhone (txt_Phone.getText ())));
				supplier.setAddress (
					new SimpleStringProperty (validateData.capitalizeWords (txt_Address.getText ())));
				supplier.setCompanyName (
					new SimpleStringProperty (validateData.capitalizeWords (txt_CompanyName.getText ())));
			}
			givenSupplier.resetSupplier (supplier);
			
			loadData ();
			resetFields ();
		}
	}
	
	private boolean checkFields () {
		return txt_Ruc.getText ().isEmpty () || txt_Name.getText ().isEmpty () ||
			txt_Phone.getText ().isEmpty () || txt_Address.getText ().isEmpty () ||
			txt_CompanyName.getText ().isEmpty ();
	}
	
	@FXML
	public void delete (ActionEvent actionEvent) {
		if (! txt_Id.getText ().isEmpty ()) {
			if (message.information ("Confirmar eliminación", "¿Está seguro de eliminar?")) {
				int id = Integer.parseInt (txt_Id.getText ());
				System.out.println ("Este es el Id = " + id);
				givenSupplier.deleteInTheDataBase (id);
				resetFields ();
				loadData ();
			}
		}
		
	}
	
	@FXML
	public void cancel (ActionEvent actionEvent) {
		resetFields ();
		
	}
	
	private void loadData () {
		col_Id.setCellValueFactory (cellData -> cellData.getValue ().getId ().asObject ());
		col_Ruc.setCellValueFactory (cellData -> cellData.getValue ().getRuc ());
		col_Name.setCellValueFactory (cellData -> cellData.getValue ().getName ());
		col_Phone.setCellValueFactory (cellData -> cellData.getValue ().getPhone ());
		col_Address.setCellValueFactory (cellData -> cellData.getValue ().getAddress ());
		col_CompanyName.setCellValueFactory (cellData -> cellData.getValue ().getCompanyName ());
		List<Supplier> listSuppliers = givenSupplier.loadData ();
		tbl_Supplier.setItems (FXCollections.observableArrayList (listSuppliers));
		tbl_Supplier.setOnMouseClicked (this::selectFieldsFromTable);
	}
	
	private void selectFieldsFromTable (MouseEvent event) {
		supplier = tbl_Supplier.getSelectionModel ().getSelectedItem ();
		if (supplier != null) {
			txt_Id.setText (String.valueOf (supplier.getId ().get ()));
			txt_Ruc.setText (String.valueOf (supplier.getRuc ().get ()));
			txt_Name.setText (supplier.getName ().get ());
			txt_Phone.setText (supplier.getPhone ().get ());
			txt_Address.setText (supplier.getAddress ().get ());
			txt_CompanyName.setText (supplier.getCompanyName ().get ());
		}
		btn_Save.setDisable (true);
	}
	
	private void resetFields () {
		txt_Id.setText ("");
		txt_Ruc.setText ("");
		txt_Name.setText ("");
		txt_Phone.setText ("");
		txt_Address.setText ("");
		txt_CompanyName.setText ("");
		btn_Save.setDisable (false);
	}
}
