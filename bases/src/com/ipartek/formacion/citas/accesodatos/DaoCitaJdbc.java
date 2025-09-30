package com.ipartek.formacion.citas.accesodatos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

import com.ipartek.formacion.bibliotecas.DaoJdbc;
import com.ipartek.formacion.citas.entidades.Cita;
import com.ipartek.formacion.citas.entidades.Usuario;

public class DaoCitaJdbc implements DaoCita {
	private DaoJdbc<Cita> dao = new DaoJdbc<Cita>("jdbc:sqlite:bdd/citas.db", "", "");

	@Override
	public Iterable<Cita> obtenerTodos() {
		return dao.ejecutarConsulta("""
				SELECT c.id, c.texto, c.inicio, c.fin, u.id as u_id, u.nombre as u_nombre 
				FROM citas c
				LEFT JOIN usuarios u ON c.usuario_id = u.id;
				""", DaoCitaJdbc::mapeadorCitas);
	}

	@Override
	public Optional<Cita> obtenerPorId(Long id) {
		return dao.ejecutarConsultaUno("""
				SELECT c.id, c.texto, c.inicio, c.fin, u.id as u_id, u.nombre as u_nombre 
				FROM citas c
				LEFT JOIN usuarios u ON c.usuario_id = u.id;
				""", DaoCitaJdbc::mapeadorCitas, id);
	}

	@Override
	public Cita insertar(Cita cita) {
		dao.ejecutarCambio("INSERT INTO citas (texto, inicio, fin) VALUES (?,?,?)", cita.getTexto(),
				Timestamp.valueOf(cita.getInicio()), Timestamp.valueOf(cita.getFin()));

		return cita;
	}

	@Override
	public Cita modificar(Cita cita) {
		dao.ejecutarCambio("UPDATE citas SET texto=?, inicio=?, fin=? WHERE id=?", cita.getTexto(),
				Timestamp.valueOf(cita.getInicio()), Timestamp.valueOf(cita.getFin()), cita.getId());

		return cita;
	}

	@Override
	public void borrar(Long id) {
		dao.ejecutarCambio("DELETE FROM citas WHERE id=?", id);
	}

	@Override
	public Iterable<Cita> buscarPorTexto(String texto) {
		return dao.ejecutarConsulta("SELECT * FROM citas WHERE texto LIKE ?", DaoCitaJdbc::mapeadorCitas,
				"%" + texto + "%");
	}

	private static Cita mapeadorCitas(ResultSet rs) throws SQLException {
		Usuario usuario = rs.getObject("u_id") != null ? new Usuario(rs.getLong("u_id"), rs.getString("u_nombre")): null;
		return new Cita(rs.getLong("id"), usuario, rs.getString("texto"), rs.getTimestamp("inicio").toLocalDateTime(),
				rs.getTimestamp("fin").toLocalDateTime());
	}
}
