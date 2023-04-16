package com.ventas.sistemaventas_jfx.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDado {
	private Connection connection;
	private PreparedStatement ps;
	private ResultSet rs;
	private final String QUERRY = "SELECT * FROM users WHERE username = ? AND password = ?";
	DataBaseConnection myConnection = new DataBaseConnection ();
	Login newLogin = new Login ();
	
	public Login getLogin (String username, String password) {
		try {
			connection = myConnection.getConnection ();
			ps = connection.prepareStatement (QUERRY);
			ps.setString (1, username);
			ps.setString (2, password);
			rs = ps.executeQuery ();
			
			if (rs.next ()) {
				newLogin.setUsername (rs.getString ("username"));
				newLogin.setPassword (rs.getString ("password"));
			}
			System.out.println ("Todo salio bien");
		} catch (SQLException e) {
			System.out.println ("Broblema con la base de datos\n" + e.getMessage ());
		}
		return newLogin;
		
	}
	
}
