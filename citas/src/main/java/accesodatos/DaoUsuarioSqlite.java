package accesodatos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import bibliotecas.accesodatos.DaoJdbc;
import modelos.Rol;
import modelos.Usuario;

public class DaoUsuarioSqlite implements DaoUsuario {
	private static final String USUARIOS_ROLES = """
			SELECT u.*, r.nombre as rol_nombre, r.descripcion as rol_descripcion
			FROM usuarios u
			JOIN roles r ON r.id = u.rol_id
			""";

	// TODO Usar fábrica para la instanciación del DaoJdbc
	private static final DaoJdbc<Usuario> DAO = new DaoJdbc<>("jdbc:sqlite:bdd/citas.db", "", "", "org.sqlite.JDBC");

	@Override
	public Iterable<Usuario> obtenerTodos() {
		return DAO.ejecutarConsulta(USUARIOS_ROLES, DaoUsuarioSqlite::mapeador);
	}

	@Override
	public Optional<Usuario> obtenerPorId(Long id) {
		return DAO.ejecutarConsultaUno(USUARIOS_ROLES + " WHERE id=?", DaoUsuarioSqlite::mapeador, id);
	}

	@Override
	public Usuario insertar(Usuario usuario) {
		DAO.ejecutarCambio("INSERT INTO usuarios (email, password, nombre) VALUES (?,?,?)", usuario.getEmail(),
				usuario.getPassword(), usuario.getNombre());
		return usuario;
	}

	@Override
	public Usuario modificar(Usuario usuario) {
		DAO.ejecutarCambio("UPDATE usuario SET email=?, password=?, nombre=? WHERE id=?", usuario.getEmail(),
				usuario.getPassword(), usuario.getNombre(), usuario.getId());
		return usuario;
	}

	@Override
	public void borrar(Long id) {
		DAO.ejecutarCambio("DELETE FROM usuario WHERE id=?", id);
	}

	private static Usuario mapeador(ResultSet rs) throws SQLException {
		Rol rol = new Rol(rs.getLong("rol_id"), rs.getString("rol_nombre"), rs.getString("rol_descripcion"));
		return new Usuario(rs.getLong("id"), rs.getString("email"), rs.getString("password"), rs.getString("nombre"),
				rol);
	}

	@Override
	public Optional<Usuario> obtenerPorEmail(String email) {
		return DAO.ejecutarConsultaUno(USUARIOS_ROLES + " WHERE email=?", DaoUsuarioSqlite::mapeador, email);
	}

}
