package com.ipartek.formacion.springcitas.servicios;

import com.ipartek.formacion.springcitas.entidades.Cita;

public interface AdminService extends UsuarioService {
	Cita altaCita(Cita cita);
	Cita modificarCita(Cita cita);
	void bajaCita(Long id);
}
