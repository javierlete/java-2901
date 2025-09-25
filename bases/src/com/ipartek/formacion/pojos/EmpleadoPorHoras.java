package com.ipartek.formacion.pojos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class EmpleadoPorHoras extends Empleado {
	private Integer numeroHoras;
	private BigDecimal precioHora;

	public EmpleadoPorHoras(Long id, String nombre, LocalDate fechaNacimiento, Integer numeroHoras,
			BigDecimal precioHora) {
		super(id, nombre, fechaNacimiento);
		this.numeroHoras = numeroHoras;
		this.precioHora = precioHora;
	}

	public EmpleadoPorHoras(String nombre, LocalDate fechaNacimiento, Integer numeroHoras, BigDecimal precioHora) {
		this(null, nombre, fechaNacimiento, numeroHoras, precioHora);
	}

	public Integer getNumeroHoras() {
		return numeroHoras;
	}

	public void setNumeroHoras(Integer numeroHoras) {
		this.numeroHoras = numeroHoras;
	}

	public BigDecimal getPrecioHora() {
		return precioHora;
	}

	public void setPrecioHora(BigDecimal precioHora) {
		this.precioHora = precioHora;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(numeroHoras, precioHora);
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
		EmpleadoPorHoras other = (EmpleadoPorHoras) obj;
		return Objects.equals(numeroHoras, other.numeroHoras) && Objects.equals(precioHora, other.precioHora);
	}

	@Override
	public String toString() {
		return String.format("EmpleadoPorHoras [numeroHoras=%s, precioHora=%s]", numeroHoras, precioHora);
	}

	@Override
	public BigDecimal getSueldoMensual() {
		return precioHora.multiply(new BigDecimal(numeroHoras));
	}

}
