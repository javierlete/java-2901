package com.ipartek.formacion.bases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcEjemplo {
	public static void main(String[] args) throws SQLException {
		String url = "jdbc:sqlite:bdd/ejemplo.db";
		String user = "";
		String pass = "";
		
		Connection con = DriverManager.getConnection(url, user, pass);
		PreparedStatement pst = con.prepareStatement("SELECT * FROM personas");
		ResultSet rs = pst.executeQuery();
		
		while(rs.next()) {
			System.out.printf("%2d %-10s\n", rs.getLong("id"), rs.getString("nombre"));
		}
		
		System.out.println();
		
		Long id = 2L;
		
		pst = con.prepareStatement("SELECT * FROM personas WHERE id=?");
		
		pst.setLong(1, id);
		
		rs = pst.executeQuery();
		
		while(rs.next()) {
			System.out.printf("%2d %-10s\n", rs.getLong("id"), rs.getString("nombre"));
		}
		
		System.out.println();

		pst = con.prepareStatement("INSERT INTO personas (nombre) VALUES (?)");
		
		pst.setString(1, "NUEVO");
		
		pst.executeUpdate();

		pst = con.prepareStatement("SELECT * FROM personas");
		
		rs = pst.executeQuery();
		
		while(rs.next()) {
			System.out.printf("%2d %-10s\n", rs.getLong("id"), rs.getString("nombre"));
		}

	}
}
