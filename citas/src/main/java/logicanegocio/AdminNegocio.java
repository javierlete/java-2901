package logicanegocio;

import modelos.Cita;

public interface AdminNegocio extends UsuarioNegocio {
	Cita altaCita(Cita cita);
	Cita modificarCita(Cita cita);
	void bajaCita(Long id);
}
