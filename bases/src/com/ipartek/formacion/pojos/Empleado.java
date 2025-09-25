package com.ipartek.formacion.pojos;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class Empleado extends Persona {

	public Empleado(Long id, String nombre, LocalDate fechaNacimiento) {
		super(id, nombre, fechaNacimiento);
	}

	public Empleado(String nombre, LocalDate fechaNacimiento) {
		this(null, nombre, fechaNacimiento);
	}

	public abstract BigDecimal getSueldoMensual();

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Empleado [id=%s, nombre=%s, fechaNacimiento=%s]", id,
				nombre, fechaNacimiento);
	}

}
