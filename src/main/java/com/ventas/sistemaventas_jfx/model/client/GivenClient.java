package com.ventas.sistemaventas_jfx.model.client;

import com.ventas.sistemaventas_jfx.model.DataBaseConnection;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a data access object for the Client class.
 */
public class GivenClient {
	DataBaseConnection myConnection = new DataBaseConnection ();
	Connection connection;
	PreparedStatement ps;
	ResultSet rs;
	
	/**
	 * Loads data from the database and returns a list of clients.
	 *
	 * @return a list of clients
	 */
	public List loadData () {
		List<Client> listOfClients = new ArrayList<> ();
		String sql = "SELECT * FROM clients";
		
		try {
			connection = myConnection.getConnection ();
			ps = connection.prepareStatement (sql);
			rs = ps.executeQuery ();
			
			while (rs.next ()) {
				Client client = new Client (rs.getInt ("id"), rs.getString ("dni"), rs.getString ("name"),
					rs.getString ("phone"), rs.getString ("address"), rs.getString ("company_name"));
				listOfClients.add (client);
			}
			
		} catch (SQLException e) {
			System.out.println (e.toString ());
		}
		return listOfClients;
	}
	
	/**
	 * Inserts a new client record into the database.
	 *
	 * @param client the client to be inserted
	 */
	public void newRecordInTheDataBase (Client client) {
		String sql = "INSERT INTO clients  (dni, name, phone, address, company_name)VALUES (?,?,?,?,?)";
		try {
			connection = myConnection.getConnection ();
			ps = connection.prepareStatement (sql);
			
			ps.setString (1, client.getDni ().get ());
			ps.setString (2, client.getName ().get ());
			ps.setString (3, client.getPhone ().get ());
			ps.setString (4, client.getAddress ().get ());
			ps.setString (5, client.getCompanyName ().get ());
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
	
	/**
	 * Deletes a client record from the database based on the given ID.
	 *
	 * @param id the ID of the client to be deleted
	 */
	public void deleteInTheDataBase (int id) {
		String sql = "DELETE FROM clients   WHERE id = ?";
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
	
	/**
	 * Modifies a client record in the database based on the given client object.
	 *
	 * @param client the client object with updated information
	 */
	public void modifyInTheDataBase (Client client) {
		String sql = "UPDATE clients SET dni=?, name=?, phone=?, address=?, company_name=? WHERE id=?";
		try {
			connection = myConnection.getConnection ();
			ps = connection.prepareStatement (sql);
			ps.setString (1, client.getDni ().get ());
			ps.setString (2, client.getName ().get ());
			ps.setString (3, client.getPhone ().get ());
			ps.setString (4, client.getAddress ().get ());
			ps.setString (5, client.getCompanyName ().get ());
			ps.setInt (6, client.getId ().get ());
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
