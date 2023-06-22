package com.ventas.sistemaventas_jfx.model.login;

import com.ventas.sistemaventas_jfx.model.conexionDataBase.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DameLogin {
	private Connection connection;
	private PreparedStatement ps;
	private ResultSet rs;
	private final String QUERRY = "SELECT * FROM vendedor WHERE correo = ? AND password = ?";
	
	Login newLogin = new Login ();
	
	public Login getLogin (String username, String password) {
		try {
			connection = DataBaseConnection.getConnection ();
			ps = connection.prepareStatement (QUERRY);
			ps.setString (1, username);
			ps.setString (2, password);
			rs = ps.executeQuery ();
			
			if (rs.next ()) {
				newLogin.setCorreo (rs.getString ("correo"));
				newLogin.setPassword (rs.getString ("password"));
			}
			System.out.println ("Todo salio bien");
		} catch (SQLException e) {
			System.out.println ("Problemas con la base de datos\n" + e.getMessage ());
		}
		return newLogin;
		
	}
	
}
