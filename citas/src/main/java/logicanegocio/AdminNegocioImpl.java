package logicanegocio;

import modelos.Cita;

public class AdminNegocioImpl extends UsuarioNegocioImpl implements AdminNegocio {

	@Override
	public Cita altaCita(Cita cita) {
		return DAO_CITA.insertar(cita);
	}

	@Override
	public Cita modificarCita(Cita cita) {
		return DAO_CITA.modificar(cita);
	}

	@Override
	public void bajaCita(Long id) {
		DAO_CITA.borrar(id);
	}

}
