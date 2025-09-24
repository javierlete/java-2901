package com.ipartek.formacion.pojos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Empleado extends Persona {
	private BigDecimal sueldoMensual;

	public Empleado(Long id, String nombre, LocalDate fechaNacimiento, BigDecimal sueldoMensual) {
		super(id, nombre, fechaNacimiento);
		setSueldoMensual(sueldoMensual);
	}

	public Empleado(String nombre, LocalDate fechaNacimiento, BigDecimal sueldoMensual) {
		this(null, nombre, fechaNacimiento, sueldoMensual);
	}

	public BigDecimal getSueldoMensual() {
		return sueldoMensual;
	}

	public void setSueldoMensual(BigDecimal sueldoMensual) {
		this.sueldoMensual = sueldoMensual;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(sueldoMensual);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		return Objects.equals(sueldoMensual, other.sueldoMensual);
	}

	@Override
	public String toString() {
		return String.format("Empleado [id=%s, nombre=%s, fechaNacimiento=%s, sueldoMensual=%s]", id,
				nombre, fechaNacimiento, sueldoMensual);
	}

}
