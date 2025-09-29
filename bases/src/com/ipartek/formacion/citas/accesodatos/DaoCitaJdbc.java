package com.ipartek.formacion.citas.accesodatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;

import com.ipartek.formacion.citas.entidades.Cita;

public class DaoCitaJdbc implements DaoCita {

	private static final String URL = "jdbc:sqlite:bdd/citas.db";

	@Override
	public Iterable<Cita> obtenerTodos() {
		try (Connection con = DriverManager.getConnection(URL);
				PreparedStatement pst = con.prepareStatement("SELECT * FROM citas");
				ResultSet rs = pst.executeQuery()) {
			ArrayList<Cita> citas = new ArrayList<>();

			while (rs.next()) {
				citas.add(new Cita(rs.getLong("id"), rs.getString("texto"), rs.getTimestamp("inicio").toLocalDateTime(),
						rs.getTimestamp("fin").toLocalDateTime()));
			}

			return citas;
		} catch (SQLException e) {
			throw new AccesoDatosException("Error al obtener todos los registros", e);
		}
	}

	@Override
	public Optional<Cita> obtenerPorId(Long id) {
		try (Connection con = DriverManager.getConnection(URL);
				PreparedStatement pst = con.prepareStatement("SELECT * FROM citas WHERE id=?");
				) {
			pst.setLong(1, id);
			
			try (ResultSet rs = pst.executeQuery()) {
				Optional<Cita> cita = Optional.empty();

				if (rs.next()) {
					cita = Optional.of(new Cita(rs.getLong("id"), rs.getString("texto"),
							rs.getTimestamp("inicio").toLocalDateTime(), rs.getTimestamp("fin").toLocalDateTime()));
				}

				return cita;
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Error al obtener el registro", e);
		}
	}

	@Override
	public Cita insertar(Cita cita) {
		try (Connection con = DriverManager.getConnection(URL);
				PreparedStatement pst = con.prepareStatement("INSERT INTO citas (texto, inicio, fin) VALUES (?,?,?)");
				) {
			pst.setString(1, cita.getTexto());
			pst.setTimestamp(2, Timestamp.valueOf(cita.getInicio()));
			pst.setTimestamp(3, Timestamp.valueOf(cita.getFin()));
			
			pst.executeUpdate();

			return cita;
		} catch (SQLException e) {
			throw new AccesoDatosException("Error al insertar el registro", e);
		}
	}

	@Override
	public Cita modificar(Cita cita) {
		try (Connection con = DriverManager.getConnection(URL);
				PreparedStatement pst = con.prepareStatement("UPDATE citas SET texto=?, inicio=?, fin=? WHERE id=?");
				) {
			pst.setString(1, cita.getTexto());
			pst.setTimestamp(2, Timestamp.valueOf(cita.getInicio()));
			pst.setTimestamp(3, Timestamp.valueOf(cita.getFin()));
			pst.setLong(4, cita.getId());
			
			pst.executeUpdate();

			return cita;
		} catch (SQLException e) {
			throw new AccesoDatosException("Error al modificar el registro", e);
		}
	}

	@Override
	public void borrar(Long id) {
		try (Connection con = DriverManager.getConnection(URL);
				PreparedStatement pst = con.prepareStatement("DELETE FROM citas WHERE id=?");
				) {
			pst.setLong(1, id);
			
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new AccesoDatosException("Error al borrar el registro", e);
		}
	}

	@Override
	public Iterable<Cita> buscarPorTexto(String texto) {
		try (Connection con = DriverManager.getConnection(URL);
				PreparedStatement pst = con.prepareStatement("SELECT * FROM citas WHERE texto LIKE ?");) {
			pst.setString(1, "%" + texto + "%");
			
			try (ResultSet rs = pst.executeQuery()) {
				ArrayList<Cita> citas = new ArrayList<>();

				while (rs.next()) {
					citas.add(new Cita(rs.getLong("id"), rs.getString("texto"), rs.getTimestamp("inicio").toLocalDateTime(),
							rs.getTimestamp("fin").toLocalDateTime()));
				}

				return citas;
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Error al obtener los registros por texto", e);
		}
	}

}
