package com.ipartek.formacion.springcitas.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.springcitas.entidades.Cita;
import com.ipartek.formacion.springcitas.repositorios.CitaRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	@Autowired
	protected CitaRepository citaRepository;
	
	@Override
	public Iterable<Cita> listadoCitas() {
		return citaRepository.findAll();
	}

	@Override
	public Optional<Cita> detalleCita(Long id) {
		return citaRepository.findById(id);
	}

}
