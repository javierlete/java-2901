package accesodatos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import bibliotecas.accesodatos.DaoJdbc;
import modelos.Cita;

public class DaoCitaSqlite implements DaoCita {
	// TODO Usar fábrica para la instanciación del DaoJdbc
	private DaoJdbc<Cita> dao = new DaoJdbc<>("jdbc:sqlite:bdd/citas.db", "", "", "org.sqlite.JDBC");

	@Override
	public Iterable<Cita> obtenerTodos() {
		return dao.ejecutarConsulta("SELECT * FROM citas", DaoCitaSqlite::mapeador);
	}

	@Override
	public Optional<Cita> obtenerPorId(Long id) {
		return dao.ejecutarConsultaUno("SELECT * FROM citas WHERE id=?", DaoCitaSqlite::mapeador, id);
	}

	@Override
	public Cita insertar(Cita cita) {
		dao.ejecutarCambio("INSERT INTO citas (texto, inicio, fin) VALUES (?,?,?)", cita.getTexto(), cita.getInicio(), cita.getFin());
		return cita;
	}

	@Override
	public Cita modificar(Cita cita) {
		dao.ejecutarCambio("UPDATE citas SET texto=?, inicio=?, fin=? WHERE id=?", cita.getTexto(), cita.getInicio(), cita.getFin(), cita.getId());
		return cita;
	}

	@Override
	public void borrar(Long id) {
		dao.ejecutarCambio("DELETE FROM citas WHERE id=?", id);
	}
	
	private static Cita mapeador(ResultSet rs) throws SQLException {
		return new Cita(rs.getLong("id"), rs.getString("texto"), rs.getTimestamp("inicio").toLocalDateTime(), rs.getTimestamp("fin").toLocalDateTime());
	}
}
