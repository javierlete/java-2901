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
		return ejecutarConsulta("SELECT * FROM citas");
	}

	@Override
	public Optional<Cita> obtenerPorId(Long id) {
		return ejecutarConsultaUno("SELECT * FROM citas WHERE id=?", id);
	}

	@Override
	public Cita insertar(Cita cita) {
		ejecutarConsulta("INSERT INTO citas (texto, inicio, fin) VALUES (?,?,?)", cita.getTexto(), Timestamp.valueOf(cita.getInicio()), Timestamp.valueOf(cita.getFin()) );
		
		return cita;
	}

	@Override
	public Cita modificar(Cita cita) {
		ejecutarConsulta("UPDATE citas SET texto=?, inicio=?, fin=? WHERE id=?", cita.getTexto(), Timestamp.valueOf(cita.getInicio()), Timestamp.valueOf(cita.getFin()), cita.getId() );
		return cita;
	}

	@Override
	public void borrar(Long id) {
		ejecutarConsulta("DELETE FROM citas WHERE id=?", id);
	}

	@Override
	public Iterable<Cita> buscarPorTexto(String texto) {
		return ejecutarConsulta("SELECT * FROM citas WHERE texto LIKE ?", "%"+ texto + "%");
	}

	private Optional<Cita> ejecutarConsultaUno(String sql, Object... args) {
		Iterable<Cita> citas = ejecutarConsulta(sql, args);
		
		if(citas.iterator().hasNext()) {
			return Optional.of(citas.iterator().next());
		} else {
			return Optional.empty();
		}
	}
	
	private Iterable<Cita> ejecutarConsulta(String sql, Object... args) {
		try (Connection con = DriverManager.getConnection(URL); PreparedStatement pst = con.prepareStatement(sql);) {

			int i = 1;

			for (var arg : args) {
				pst.setObject(i++, arg);
			}

			if (sql.startsWith("SELECT")) {
				try (ResultSet rs = pst.executeQuery()) {
					ArrayList<Cita> citas = new ArrayList<>();

					while (rs.next()) {
						citas.add(new Cita(rs.getLong("id"), rs.getString("texto"),
								rs.getTimestamp("inicio").toLocalDateTime(), rs.getTimestamp("fin").toLocalDateTime()));
					}

					return citas;
				}
			} else {
				pst.executeUpdate();
				
				return null;
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Error al obtener los registros por texto", e);
		}
	}

}
