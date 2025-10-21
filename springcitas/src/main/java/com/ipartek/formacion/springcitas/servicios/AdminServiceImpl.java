package com.ipartek.formacion.springcitas.servicios;

import org.springframework.stereotype.Service;

import com.ipartek.formacion.springcitas.entidades.Cita;

@Service
public class AdminServiceImpl extends UsuarioServiceImpl implements AdminService {
	@Override
	public Cita altaCita(Cita cita) {
		return citaRepository.save(cita);
	}

	@Override
	public Cita modificarCita(Cita cita) {
		return citaRepository.save(cita);
	}

	@Override
	public void bajaCita(Long id) {
		citaRepository.deleteById(id);
	}

}
