package com.ipartek.formacion.springcitas.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ipartek.formacion.springcitas.dtos.UsuarioRolDto;
import com.ipartek.formacion.springcitas.entidades.Rol;
import com.ipartek.formacion.springcitas.entidades.Usuario;
import com.ipartek.formacion.springcitas.servicios.AdminService;

@RestController
@RequestMapping("/api/v2/usuarios")
public class UsuarioRestController {

	@Autowired
	private AdminService adminService;

	@GetMapping
	public Iterable<UsuarioRolDto> obtenerTodos() {
		return adminService.listadoUsuarios().stream().map(UsuarioRestController::usuarioAUsuarioRolDto).toList();
	}

	@GetMapping("{id}")
	public UsuarioRolDto obtenerPorId(@PathVariable Long id) {
		var usuario = adminService.usuarioPorId(id);

		if (usuario.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		return usuario.map(UsuarioRestController::usuarioAUsuarioRolDto).get();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioRolDto insertar(@RequestBody UsuarioRolDto usuarioRolDto) {
		return usuarioAUsuarioRolDto(adminService.altaUsuario(usuarioRolDtoAUsuario(usuarioRolDto)));
	}

	@PutMapping("{id}")
	public UsuarioRolDto modificar(@RequestBody UsuarioRolDto usuarioRolDto, @PathVariable Long id) {
		if (id != usuarioRolDto.id()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No puedes modificar el id");
		}

		return usuarioAUsuarioRolDto(adminService.modificacionUsuario(usuarioRolDtoAUsuario(usuarioRolDto)));
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void borrar(@PathVariable Long id) {
		adminService.bajaUsuario(id);
	}

	private static UsuarioRolDto usuarioAUsuarioRolDto(Usuario usuario) {
		return new UsuarioRolDto(usuario.getId(), usuario.getNombre(), usuario.getEmail(), null,
				usuario.getRol().getId(), usuario.getRol().getNombre());
	}

	private static Usuario usuarioRolDtoAUsuario(UsuarioRolDto usuarioRolDto) {
		return Usuario.builder().id(usuarioRolDto.id()).nombre(usuarioRolDto.nombre()).email(usuarioRolDto.email())
				.password(usuarioRolDto.password()).rol(Rol.builder().id(usuarioRolDto.idRol()).build()).build();
	}
}
