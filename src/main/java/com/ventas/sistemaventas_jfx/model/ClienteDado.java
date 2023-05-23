package com.ventas.sistemaventas_jfx.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDado {
	DataBaseConnection conexion = new DataBaseConnection ();
	Connection connection;
	PreparedStatement ps;
	ResultSet rs;
	
	public List listarClientes () {
		List<Cliente> listarClientes = new ArrayList ();
		String sql = "SELECT * FROM clientes";
		
		try {
			connection = conexion.getConnection ();
			ps = connection.prepareStatement (sql);
			rs = ps.executeQuery ();
			
			while (rs.next ()) {
				Cliente cliente = new Cliente (rs.getInt ("id_cliente"), rs.getString ("dni"),
					rs.getString ("nombre"), rs.getString ("telefono"), rs.getString ("direccion"),
					rs.getString ("razon_social"));
				listarClientes.add (cliente);
			}
			System.out.println ("El metodo listar Clientes esta funcionando corectamente");
		} catch (SQLException e) {
			System.out.println ("El metodo listar Clientes NO esta funcionando corectamente");
			throw new RuntimeException (e);
		}
		return listarClientes;
	}
	
	public boolean registrarNuevoCliente (Cliente cliente) {
		String sql = "INSERT INTO clientes (dni, nombre, telefono, direccion, razon_social)VALUES (?,?,?,?,?)";
		try {
			connection = conexion.getConnection ();
			ps = connection.prepareStatement (sql);
			
			ps.setString (1, cliente.getDni ().get ());
			ps.setString (2, cliente.getNombre ().get ());
			ps.setString (3, cliente.getTelefono ().get ());
			ps.setString (4, cliente.getDireccion ().get ());
			ps.setString (5, cliente.getRazonSocial ().get ());
			ps.execute ();
			System.out.println ("Se pudo registrar al cliente");
			return true;
			
		} catch (SQLException e) {
			System.out.println ("No se pudo registrar al cliente");
			return false;
		}
	}
	
	public boolean eliminarCliente (int id) {
		System.out.println ("aqui tienes tu Id " + id);
		String sql = "DELETE FROM clientes  WHERE id_cliente = ?";
		try {
			connection = conexion.getConnection ();
			ps = connection.prepareStatement (sql);
			ps.setInt (1, id);
			ps.execute ();
			System.out.println ("Se elimino correctamente");
			return true;
		} catch (SQLException e) {
			System.out.println ("No se puede eliminar");
			return false;
		}
	}
	
	public boolean modificarCliente (Cliente cliente) {
		String sql = "UPDATE clientes SET dni=?, nombre=?, telefono=?, direccion=?, razon_social=? WHERE id_cliente=?";
		
		try {
			connection = conexion.getConnection ();
			ps = connection.prepareStatement (sql);
			ps.setString (1, String.valueOf (cliente.getDni ()));
			ps.setString (2, String.valueOf (cliente.getNombre ()));
			ps.setString (3, String.valueOf (cliente.getDireccion ()));
			ps.setString (5, cliente.getRazonSocial ().get ());
			ps.setInt (6, cliente.getId_cliente ().get ());
			ps.executeUpdate ();
			System.out.println ("Se modifico correctamente");
			return true;
		} catch (SQLException e) {
			System.out.println ("No se puede modificar\n" + e.toString ());
			
			return false;
		}
	}
}

