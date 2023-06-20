package com.ventas.sistemaventas_jfx.controller;

import com.ventas.sistemaventas_jfx.model.client.Cliente;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class Cliente_Controller implements Initializable {
	
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
	
	}
	
	
	private void selectFieldsFromTable (MouseEvent event) {
		System.out.println ("selectFields");
	}
	
	@FXML
	public void add () {
		System.out.println ("selectFields");
	}
	
	private void resetFields () {
		System.out.println ("selectFields");
	}
	
	
	public void delete () {
		System.out.println ("selectFields");
	}
	
	public void upDate () {
		System.out.println ("selectFields");
	}
	
	@FXML
	public void cancel () {
		resetFields ();
	}
}
