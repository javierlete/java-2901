package com.ipartek.formacion.pojos;

import java.time.LocalDate;

public class Persona {
	// VARIABLES DE INSTANCIA
	private Long id;
	private String nombre;
	private LocalDate fechaNacimiento;

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
		this(null, "ANÓNIMO", null);
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

	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", fechaNacimiento=" + fechaNacimiento + "]";
	}

}
