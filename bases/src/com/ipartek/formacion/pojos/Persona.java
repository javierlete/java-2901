package com.ipartek.formacion.pojos;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class Persona {
	// CONSTANTES
	public static final String NOMBRE_ANONIMO = "Juan Nadie";
	private static final int MAYORIA_DE_EDAD = 18;

	// VARIABLES DE INSTANCIA
	protected Long id;
	protected String nombre;
	protected LocalDate fechaNacimiento;

	// CONSTRUCTORES
	public Persona(Long id, String nombre, LocalDate fechaNacimiento) {
		setId(id);
		setNombre(nombre);
		setFechaNacimiento(fechaNacimiento);
	}

	public Persona(String nombre, LocalDate fechaNacimiento) {
		this(null, nombre, fechaNacimiento);
	}

	public Persona(String nombre) {
		this(null, nombre, null);
	}

	public Persona() {
		this(null, NOMBRE_ANONIMO, null);
	}

	// Constructor de copia
	public Persona(Persona persona) {
		this(persona.getId(), persona.getNombre(), persona.getFechaNacimiento());
	}

	// GETTERS Y SETTERS (MÉTODOS DE ACCESO)
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
		if (nombre == null || nombre.isBlank()) {
			throw new RuntimeException("No se admiten nombres vacíos");
		}

		this.nombre = nombre.trim();
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		if (fechaNacimiento != null && fechaNacimiento.isAfter(LocalDate.now())) {
			throw new RuntimeException("No puedes nacer en el futuro");
		}

		this.fechaNacimiento = fechaNacimiento;
	}

	public boolean isAnonimo() {
		return NOMBRE_ANONIMO.equals(nombre);
	}

	public int getEdad() {
		if (fechaNacimiento == null) {
			throw new RuntimeException("No tiene fecha de nacimiento");
		}
		
		return Period.between(fechaNacimiento, LocalDate.now()).getYears();
	}

	public boolean isMayorDeEdad() {
		return getEdad() >= MAYORIA_DE_EDAD;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fechaNacimiento, id, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(fechaNacimiento, other.fechaNacimiento) && Objects.equals(id, other.id)
				&& Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", fechaNacimiento=" + fechaNacimiento + "]";
	}

}
