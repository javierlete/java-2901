package com.ipartek.formacion.citas.accesodatos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.ipartek.formacion.bibliotecas.DaoJdbc;
import com.ipartek.formacion.citas.entidades.Usuario;

public class DaoUsuarioJdbc implements DaoUsuario {
	private DaoJdbc<Usuario> dao = new DaoJdbc<Usuario>("jdbc:sqlite:bdd/citas.db", "", "");

	@Override
	public Iterable<Usuario> obtenerTodos() {
		return dao.ejecutarConsulta("SELECT * FROM usuarios", DaoUsuarioJdbc::mapeador);
	}

	@Override
	public Optional<Usuario> obtenerPorId(Long id) {
		return dao.ejecutarConsultaUno("SELECT * FROM usuarios WHERE id=?", DaoUsuarioJdbc::mapeador, id);
	}

	@Override
	public Usuario insertar(Usuario usuario) {
		dao.ejecutarCambio("INSERT INTO usuarios (nombre) VALUES (?)", usuario.getNombre());
		return usuario;
	}

	@Override
	public Usuario modificar(Usuario usuario) {
		dao.ejecutarCambio("UPDATE usuarios SET nombre=? WHERE id=?", usuario.getNombre(), usuario.getId());
		return usuario;
	}

	@Override
	public void borrar(Long id) {
		dao.ejecutarCambio("DELETE FROM usuarios WHERE id=?", id);
	}

	private static Usuario mapeador(ResultSet rs) throws SQLException {
		return new Usuario(rs.getLong("id"), rs.getString("nombre"));
	}
}
