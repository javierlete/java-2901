package logicanegocio;

import java.util.Optional;

import modelos.Cita;

public interface UsuarioNegocio {
	Iterable<Cita> listadoCitas();
	Optional<Cita> detalleCita(Long id);
}
