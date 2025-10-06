package bibliotecas.accesodatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class DaoJdbc<T> {
	private String url;
	private String user;
	private String pass;

	public DaoJdbc(String url, String user, String pass, String driver) {
		this.url = url;
		this.user = user;
		this.pass = pass;
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new AccesoDatosException("No se ha encontrado el driver " + driver, e);
		}
	}

	public void ejecutarCambio(String sql, Object... args) {
		ejecutarConsulta(sql, null, args);
	}

	public Optional<T> ejecutarConsultaUno(String sql, Mapeador<T> mapeador, Object... args) {
		Iterable<T> objetos = ejecutarConsulta(sql, mapeador, args);

		if (objetos.iterator().hasNext()) {
			return Optional.of(objetos.iterator().next());
		} else {
			return Optional.empty();
		}
	}

	public Iterable<T> ejecutarConsulta(String sql, Mapeador<T> mapeador, Object... args) {
		try (Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement pst = con.prepareStatement(sql);) {

			int i = 1;

			for (var arg : args) {
				pst.setObject(i++, arg);
			}

			if (pst.execute()) {
				ResultSet rs = pst.getResultSet();
				ArrayList<T> objetos = new ArrayList<>();

				while (rs.next()) {
					objetos.add(mapeador.mapear(rs));
				}

				return objetos;
			} else {
				int updateCount = pst.getUpdateCount();
				
				if(updateCount != 1) {
					throw new AccesoDatosException("Se han modificado " + updateCount + " registros");
				}
				
				return null;
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Error al ejecutar la consulta", e);
		}
	}

	public interface Mapeador<T> {
		T mapear(ResultSet rs) throws SQLException;
	}
}
