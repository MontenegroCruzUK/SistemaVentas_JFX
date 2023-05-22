package com.ventas.sistemaventas_jfx.model;

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
}

