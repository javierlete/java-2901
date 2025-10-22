package com.ipartek.formacion.springcitas.servicios;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.springcitas.entidades.Cita;
import com.ipartek.formacion.springcitas.entidades.Usuario;
import com.ipartek.formacion.springcitas.repositorios.UsuarioRepository;

@Service
public class AdminServiceImpl extends UsuarioServiceImpl implements AdminService {
	@Autowired
	private UsuarioRepository usuarioRepository;

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

	@Override
	public Collection<Usuario> listadoUsuarios() {
		return usuarioRepository.findAll();
	}

	@Override
	public Optional<Usuario> usuarioPorId(Long id) {
		return usuarioRepository.findById(id);
	}

	@Override
	public Usuario altaUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public Usuario modificacionUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public void bajaUsuario(Long id) {
		usuarioRepository.deleteById(id);
	}

}
