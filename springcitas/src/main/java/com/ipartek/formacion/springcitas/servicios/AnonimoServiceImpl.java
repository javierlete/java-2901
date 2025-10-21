package com.ipartek.formacion.springcitas.servicios;

import java.util.Optional;
import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.springcitas.entidades.Usuario;
import com.ipartek.formacion.springcitas.repositorios.UsuarioRepository;

import lombok.extern.java.Log;

@Log
@Service
public class AnonimoServiceImpl implements AnonimoService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public Optional<Usuario> autenticar(String email, String password) {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

		if(usuario.isEmpty()) {
			log.warning("USUARIO NO ENCONTRADO");
			return Optional.empty();
		}
		
		boolean passwordCorrecta = usuario.get().getPassword().equals(password);

		if(!passwordCorrecta) {
			log.log(Level.WARNING, "PASSWORD INCORRECTA para el usuario {0}", usuario.get().getEmail());
		}
		
		if (usuario.isPresent() && passwordCorrecta) {
			log.log(Level.FINEST,"Usuario logueado correctamente: {0}", usuario.get().getEmail());
			return usuario;
		}

		return Optional.empty();
	}

}
