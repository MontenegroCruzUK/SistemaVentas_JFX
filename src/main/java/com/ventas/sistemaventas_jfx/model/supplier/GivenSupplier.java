package com.ventas.sistemaventas_jfx.model.supplier;

import com.ventas.sistemaventas_jfx.model.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GivenSupplier {
	DataBaseConnection myConnection = new DataBaseConnection ();
	Connection connection;
	PreparedStatement ps;
	ResultSet rs;
	
	public List loadData () {
		List<Supplier> listOfSuppliers = new ArrayList<> ();
		String sql = "SELECT * FROM supplier";
		try {
			connection = myConnection.getConnection ();
			ps = connection.prepareStatement (sql);
			rs = ps.executeQuery ();
			
			while (rs.next ()) {
				Supplier supplier = new Supplier (rs.getInt ("id"), rs.getString ("ruc"), rs.getString ("name"),
					rs.getString ("phone"), rs.getString ("address"), rs.getString ("company_name"));
				listOfSuppliers.add (supplier);
			}
		} catch (SQLException e) {
			System.out.println (e.toString ());
		}
		return listOfSuppliers;
	}
	
	public void createNewSupplier (Supplier supplier) {
		String query = "INSERT INTO supplier (ruc, name, phone, address, company_name)VALUES(?,?,?,?,?)";
		try {
			connection = myConnection.getConnection ();
			ps = connection.prepareStatement (query);
			
			ps.setString (1, supplier.getRuc ().get ());
			ps.setString (2, supplier.getName ().get ());
			ps.setString (3, supplier.getPhone ().get ());
			ps.setString (4, supplier.getAddress ().get ());
			ps.setString (5, supplier.getCompanyName ().get ());
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
	
	public void resetSupplier (Supplier supplier) {
		String query = "UPDATE supplier SET ruc=?, name=?, phone=?, address=?, company_name=? WHERE id=?";
		
		try {
			connection = myConnection.getConnection ();
			ps = connection.prepareStatement (query);
			
			ps.setString (1, supplier.getRuc ().get ());
			ps.setString (2, supplier.getName ().get ());
			ps.setString (3, supplier.getPhone ().get ());
			ps.setString (4, supplier.getAddress ().get ());
			ps.setString (5, supplier.getCompanyName ().get ());
			ps.setInt (6, supplier.getId ().get ());
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
	
	public void deleteInTheDataBase (int id) {
		String sql = "DELETE FROM  supplier  WHERE id = ?";
		try {
			connection = myConnection.getConnection ();
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
