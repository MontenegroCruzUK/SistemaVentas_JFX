package com.ventas.sistemaventas_jfx.model.client;

import com.ventas.sistemaventas_jfx.model.conexionDataBase.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DameCliente {
	DataBaseConnection myConnection = new DataBaseConnection ();
	Connection connection;
	PreparedStatement ps;
	ResultSet rs;
	
	public List cargarDatos () {
		List<Cliente> listOfClients = new ArrayList<> ();
		String sql = "SELECT * FROM clients";
		
		try {
			connection = myConnection.getConnection ();
			ps = connection.prepareStatement (sql);
			rs = ps.executeQuery ();
			
			while (rs.next ()) {
				Cliente cliente = new Cliente ();
				cliente.setId (rs.getInt ("id"));
				cliente.setDni (rs.getString ("dni"));
				cliente.setName (rs.getString ("name"));
				cliente.setPhone (rs.getString ("phone"));
				cliente.setAddress (rs.getString ("address"));
				cliente.setCompanyName (rs.getString ("companyName"));
				listOfClients.add (cliente);
			}
			
		} catch (SQLException e) {
			System.out.println (e.toString ());
		}
		return listOfClients;
	}
}
