package com.ventas.sistemaventas_jfx.controller;

import com.ventas.sistemaventas_jfx.model.productos.DameProducto;
import com.ventas.sistemaventas_jfx.model.productos.Producto;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class Producto_Controller implements Initializable {
	DameProducto givenProduct = new DameProducto ();
	
	@FXML
	private MFXTextField txt_Codec;
	@FXML
	private MFXTextField txt_Id;
	@FXML
	private MFXTextField txt_Description;
	@FXML
	private MFXTextField txt_Stock;
	@FXML
	private MFXComboBox cBx_Suppliers;
	@FXML
	private MFXTextField txt_Price;
	@FXML
	private MFXButton btn_Save;
	@FXML
	private MFXButton btn_UpData;
	@FXML
	private MFXButton btn_Delete;
	@FXML
	private MFXButton btn_Report;
	@FXML
	private TableView<Producto> tbl_Product;
	@FXML
	private TableColumn<Producto, Integer> col_Id;
	@FXML
	private TableColumn<Producto, String> col_Code;
	@FXML
	private TableColumn<Producto, String> col_Description;
	@FXML
	private TableColumn<Producto, String> col_Supplier;
	@FXML
	private TableColumn<Producto, Integer> col_Stock;
	@FXML
	private TableColumn<Producto, Double> col_Price;
	
	
	@Override
	public void initialize (URL location, ResourceBundle resources) {
	
		
	}
	
	private void loadData () {
		
	}
}
