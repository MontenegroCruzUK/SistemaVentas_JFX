package com.ventas.sistemaventas_jfx.model.proveedores;

import com.ventas.sistemaventas_jfx.model.client.Cliente;
import com.ventas.sistemaventas_jfx.model.conexionDataBase.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DameProveedor {
	Connection connection;
	PreparedStatement ps;
	ResultSet rs;
	
	public List<Proveedor> obtenerProveedoresDeDB () {
		List<Proveedor> listaProveedores = new ArrayList<> ();
		String sql = "SELECT * FROM supplier";
		try {
			connection = DataBaseConnection.getConnection ();
			ps = connection.prepareStatement (sql);
			rs = ps.executeQuery ();
			
			while (rs.next ()) {
				Proveedor proveedor = new Proveedor ();
				proveedor.setId (rs.getInt ("id"));
				proveedor.setRuc (rs.getString ("ruc"));
				proveedor.setName (rs.getString ("name"));
				proveedor.setPhone (rs.getString ("phone"));
				proveedor.setAddress (rs.getString ("address"));
				proveedor.setCompanyName (rs.getString ("company_name"));
				
				listaProveedores.add (proveedor);
			}
		} catch (SQLException e) {
			System.out.println (e.toString ());
		}
		return listaProveedores;
		
	}
	
	public void nuevoProveedorEnLaDB (Proveedor proveedor) {
		String query = "INSERT INTO supplier (ruc, name, phone, address, company_name)VALUES(?,?,?,?,?)";
		try {
			connection = DataBaseConnection.getConnection ();
			ps = connection.prepareStatement (query);
			
			ps.setString (1, proveedor.getRuc ());
			ps.setString (2, proveedor.getName ());
			ps.setString (3, proveedor.getPhone ());
			ps.setString (4, proveedor.getAddress ());
			ps.setString (5, proveedor.getCompanyName ());
			ps.execute ();
		} catch (SQLException e) {
			System.out.println (e.toString ());
		} finally {
			try {
				connection.close ();
			} catch (SQLException e) {
				System.out.println ("Problemas al cerrar la conexión: " + e.toString ());
			}
		}
	}
	
	public void modificarProveedorEnDB (Proveedor proveedor) {
		String query = "UPDATE supplier SET ruc=?, name=?, phone=?, address=?, company_name=? WHERE id=?";
		
		try {
			connection = DataBaseConnection.getConnection ();
			ps = connection.prepareStatement (query);
			
			ps.setString (1, proveedor.getRuc ());
			ps.setString (2, proveedor.getName ());
			ps.setString (3, proveedor.getPhone ());
			ps.setString (4, proveedor.getAddress ());
			ps.setString (5, proveedor.getCompanyName ());
			ps.setInt (6, proveedor.getId ());
			ps.execute ();
			
		} catch (SQLException e) {
			System.out.println (e.toString ());
		} finally {
			try {
				connection.close ();
			} catch (SQLException e) {
				System.out.println ("Problemas al cerrar la conexión: " + e.toString ());
			}
		}
	}
	
	public void borrarProveedorDeLaDB (int id) {
		String sql = "DELETE FROM  supplier  WHERE id = ?";
		try {
			connection = DataBaseConnection.getConnection ();
			ps = connection.prepareStatement (sql);
			ps.setInt (1, id);
			ps.execute ();
		} catch (SQLException e) {
			System.out.println (e.toString ());
		} finally {
			try {
				connection.close ();
			} catch (SQLException e) {
				System.out.println ("Problemas al cerrar la conexión: " + e.toString ());
			}
		}
	}
}
