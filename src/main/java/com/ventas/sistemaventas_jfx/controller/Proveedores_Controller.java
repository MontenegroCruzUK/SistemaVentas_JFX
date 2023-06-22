package com.ventas.sistemaventas_jfx.controller;

import com.ventas.sistemaventas_jfx.model.Mensaje;
import com.ventas.sistemaventas_jfx.model.proveedores.DameProveedor;
import com.ventas.sistemaventas_jfx.model.proveedores.Proveedor;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
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

public class Proveedores_Controller implements Initializable {
	
	Proveedor proveedor = new Proveedor ();
	DameProveedor dameProveedor = new DameProveedor ();
	Mensaje message = new Mensaje ();
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
		cargarProveedores ();
		
	}
	
	private void cargarProveedores () {
		col_Id.setCellValueFactory (cellData -> cellData.getValue ().get_Id ().asObject ());
		col_Ruc.setCellValueFactory (cellData -> cellData.getValue ().get_Ruc ());
		col_Name.setCellValueFactory (cellData -> cellData.getValue ().get_Name ());
		col_Phone.setCellValueFactory (cellData -> cellData.getValue ().get_Phone ());
		col_Address.setCellValueFactory (cellData -> cellData.getValue ().get_Address ());
		col_CompanyName.setCellValueFactory (cellData -> cellData.getValue ().get_CompanyName ());
		
		List<Proveedor> listSuppliers = dameProveedor.obtenerProveedoresDeDB ();
		tbl_Supplier.setItems (FXCollections.observableArrayList (listSuppliers));
		tbl_Supplier.setOnMouseClicked (this::seleccionarFila);
	}
	
	private void seleccionarFila (MouseEvent event) {
		btn_Save.setDisable (true);
		proveedor = tbl_Supplier.getSelectionModel ().getSelectedItem ();
		
		if (proveedor != null) {
			txt_Id.setText (String.valueOf (proveedor.getId ()));
			txt_Ruc.setText (proveedor.getRuc ());
			txt_Name.setText (proveedor.getName ());
			txt_Phone.setText (proveedor.getPhone ());
			txt_Address.setText (proveedor.getAddress ());
			txt_CompanyName.setText (proveedor.getCompanyName ());
		}
	}
	
	@FXML
	public void add (ActionEvent actionEvent) {
		String ruc = txt_Ruc.getText ();
		String name = txt_Name.getText ();
		String phone = txt_Phone.getText ();
		String address = txt_Address.getText ();
		String companyName = txt_CompanyName.getText ();
		if (comprobarCampos ()) {
			Mensaje.error ("Error con el Proveedor", "No se puede crear el Proveedor");
			return;
		}
		if (! proveedor.validarRuc (ruc)) {
			Mensaje.error ("Error con el Proveedor", "Ruc inválido");
			txt_Ruc.setText ("");
			return;
		}
		if (! proveedor.validarNombre (name)) {
			Mensaje.error ("Error", "Nombre inválido");
			txt_Name.setText ("");
			return;
		}
		if (! proveedor.validarTelefono (phone)) {
			Mensaje.error ("Error", "Teléfono inválido");
			txt_Phone.setText ("");
			return;
		}
		proveedor.setRuc (ruc);
		proveedor.setName (proveedor.ponerEnMayusculas (name));
		proveedor.setPhone (phone);
		proveedor.setAddress (proveedor.ponerEnMayusculas (address));
		proveedor.setCompanyName (proveedor.ponerEnMayusculas (companyName));
		
		dameProveedor.nuevoProveedorEnLaDB (proveedor);
		
		Mensaje.information ("Creando nuevo Proveedor", "El Proveedor fue creado con éxito");
		cargarProveedores ();
		limpiarCampos ();
		
	}
	
	
	@FXML
	public void upDate (ActionEvent actionEvent) {
		if (txt_Id.getText ().isEmpty ()) {
			Mensaje.warning ("Advertencia", "Seleccione una fila");
		} else {
			if (! comprobarCampos ()) {
				String ruc = txt_Ruc.getText ();
				String phone = txt_Phone.getText ();
				if (proveedor.validarRuc (ruc)) {
					proveedor.setRuc (ruc);
				}
				proveedor.setName (proveedor.ponerEnMayusculas (txt_Name.getText ()));
				if (proveedor.validarTelefono (phone)) {
					proveedor.setPhone (phone);
				}
			}
			proveedor.setAddress (proveedor.ponerEnMayusculas (txt_Address.getText ()));
			proveedor.setCompanyName (proveedor.ponerEnMayusculas (txt_CompanyName.getText ()));
		}
		dameProveedor.modificarProveedorEnDB (proveedor);
		cargarProveedores ();
		limpiarCampos ();
	}
	
	@FXML
	public void delete (ActionEvent actionEvent) {
		if (! txt_Id.getText ().isEmpty ()) {
			if (Mensaje.information ("Confirmar eliminación", "¿Está seguro de eliminar?")) {
				int id = Integer.parseInt (txt_Id.getText ());
				System.out.println ("Este es el Id = " + id);
				dameProveedor.borrarProveedorDeLaDB (id);
				limpiarCampos ();
				cargarProveedores ();
			}
		}
	}
	
	@FXML
	public void cancel (ActionEvent actionEvent) {
		limpiarCampos ();
		
	}
	
	
	
	private void limpiarCampos () {
		txt_Id.setText ("");
		txt_Ruc.setText ("");
		txt_Name.setText ("");
		txt_Phone.setText ("");
		txt_Address.setText ("");
		txt_CompanyName.setText ("");
		btn_Save.setDisable (false);
	}
	
	private boolean comprobarCampos () {
		return txt_Ruc.getText ().isEmpty () || txt_Name.getText ().isEmpty () ||
			txt_Phone.getText ().isEmpty () || txt_Address.getText ().isEmpty () ||
			txt_CompanyName.getText ().isEmpty ();
	}
}
