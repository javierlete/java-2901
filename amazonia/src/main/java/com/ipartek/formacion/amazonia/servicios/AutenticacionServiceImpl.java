package com.ipartek.formacion.amazonia.servicios;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.amazonia.config.UsuarioSecurity;
import com.ipartek.formacion.amazonia.entidades.Usuario;
import com.ipartek.formacion.amazonia.repositorios.UsuarioRepository;

@Service
public class AutenticacionServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> oUsuario = usuarioRepository.findByEmail(username);
		
		if(oUsuario.isEmpty()) {
			throw new UsernameNotFoundException("No se ha encontrado el usuario");
		}
		
		Usuario usuario = oUsuario.get();
		
		UsuarioSecurity usuarioSecurity = new UsuarioSecurity();
		
		BeanUtils.copyProperties(usuario, usuarioSecurity);
		
		return usuarioSecurity;
	}

}
