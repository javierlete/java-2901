package com.ipartek.formacion.citas.entidades;

import java.util.Objects;

public class Usuario {
	private Long id;
	private String nombre;

	public Usuario(Long id, String nombre) {
		setId(id);
		setNombre(nombre);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return String.format("Usuario [id=%s, nombre=%s]", id, nombre);
	}

}
