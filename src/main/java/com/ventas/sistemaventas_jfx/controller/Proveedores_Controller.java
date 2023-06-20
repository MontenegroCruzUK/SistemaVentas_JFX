package com.ventas.sistemaventas_jfx.controller;

import com.ventas.sistemaventas_jfx.model.Mensaje;
import com.ventas.sistemaventas_jfx.model.ValidateInformation;
import com.ventas.sistemaventas_jfx.model.proveedores.DameProveedor;
import com.ventas.sistemaventas_jfx.model.proveedores.Proveedor;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Proveedores_Controller implements Initializable {
	
	Proveedor supplier = new Proveedor ();
	DameProveedor givenSupplier = new DameProveedor ();
	Mensaje message = new Mensaje ();
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
	private TableView<Proveedor> tbl_Supplier;
	@FXML
	private TableColumn<Proveedor, Integer> col_Id;
	@FXML
	private TableColumn<Proveedor, String> col_Ruc;
	@FXML
	private TableColumn<Proveedor, String> col_Name;
	@FXML
	private TableColumn<Proveedor, String> col_Phone;
	@FXML
	private TableColumn<Proveedor, String> col_Address;
	@FXML
	private TableColumn<Proveedor, String> col_CompanyName;
	
	@Override
	public void initialize (URL location, ResourceBundle resources) {
	}
	
	@FXML
	public void add (ActionEvent actionEvent) {
	
	}
	
	
	@FXML
	public void upDate (ActionEvent actionEvent) {
	
	}
	
	@FXML
	public void delete (ActionEvent actionEvent) {
	
	}
	
	@FXML
	public void cancel (ActionEvent actionEvent) {
		resetFields ();
		
	}
	
	private void loadData () {
	
	}
	
	private void selectFieldsFromTable (MouseEvent event) {
	
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
