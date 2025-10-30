package com.ipartek.formacion.amazonia.config;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ipartek.formacion.amazonia.entidades.Usuario;

public class UsuarioSecurity extends Usuario implements UserDetails {

	private static final long serialVersionUID = -924664933086202559L;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(() -> "ROLE_" + getRol());
	}

	@Override
	public String getUsername() {
		return getEmail();
	}

}
