package logicanegocio;

import java.util.Optional;

import accesodatos.DaoCita;
import bibliotecas.Fabrica;
import modelos.Cita;

public class UsuarioNegocioImpl implements UsuarioNegocio {
	protected static final DaoCita DAO_CITA = (DaoCita) Fabrica.obtenerObjeto("dao.cita");
	
	@Override
	public Iterable<Cita> listadoCitas() {
		return DAO_CITA.obtenerTodos();
	}

	@Override
	public Optional<Cita> detalleCita(Long id) {
		return DAO_CITA.obtenerPorId(id);
	}

}
