package com.ventas.sistemaventas_jfx.model.client;

import com.ventas.sistemaventas_jfx.model.conexionDataBase.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DameCliente {
	
	Connection connection;
	PreparedStatement ps;
	ResultSet rs;
	
	public List<Cliente> obtenerClientes () {
		List<Cliente> listOfClients = new ArrayList<> ();
		String sql = "SELECT * FROM clients";
		
		try {
			connection = DataBaseConnection.getConnection ();
			ps = connection.prepareStatement (sql);
			rs = ps.executeQuery ();
			
			while (rs.next ()) {
				Cliente cliente = new Cliente ();
				cliente.setId (rs.getInt ("id"));
				cliente.setDni (rs.getString ("dni"));
				cliente.setName (rs.getString ("name"));
				cliente.setPhone (rs.getString ("phone"));
				cliente.setAddress (rs.getString ("address"));
				cliente.setCompanyName (rs.getString ("company_name"));
				
				listOfClients.add (cliente);
			}
		} catch (SQLException e) {
			System.out.println (e.toString ());
		}
		return listOfClients;
	}
	
	public void nuevoUsuarioEnLaDB (Cliente cliente) {
		String sql = "INSERT INTO clients  (dni, name, phone, address, company_name)VALUES (?,?,?,?,?)";
		try {
			connection = DataBaseConnection.getConnection ();
			ps = connection.prepareStatement (sql);
			
			ps.setString (1, cliente.getDni ());
			ps.setString (2, cliente.getName ());
			ps.setString (3, cliente.getPhone ());
			ps.setString (4, cliente.getAddress ());
			ps.setString (5, cliente.getCompanyName ());
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
	
	public void borrarClienteDeLaDB (int id) {
		String sql = "DELETE FROM clients   WHERE id = ?";
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
	
	public void modificarClientesEnDB (Cliente clientes) {
		String sql = "UPDATE clients SET dni=?, name=?, phone=?, address=?, company_name=? WHERE id=?";
		try {
			connection = DataBaseConnection.getConnection ();
			ps = connection.prepareStatement (sql);
			ps.setString (1, clientes.getDni ());
			ps.setString (2, clientes.getName ());
			ps.setString (3, clientes.getPhone ());
			ps.setString (4, clientes.getAddress ());
			ps.setString (5, clientes.getCompanyName ());
			ps.setInt (6, clientes.getId ());
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
