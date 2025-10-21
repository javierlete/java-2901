package com.ipartek.formacion.springcitas.servicios;

import java.util.Optional;

import com.ipartek.formacion.springcitas.entidades.Usuario;

public interface AnonimoService {
	Optional<Usuario> autenticar(String email, String password);
}
