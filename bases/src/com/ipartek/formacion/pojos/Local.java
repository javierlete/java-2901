package com.ipartek.formacion.pojos;

import java.util.HashSet;

public class Local {
	private static final boolean PARA_ADULTOS_POR_DEFECTO = false;
	public static final boolean PARA_ADULTOS = true;
	
	private Long id;
	private String nombre;
	private Boolean paraAdultos = PARA_ADULTOS_POR_DEFECTO;
	
	private Persona encargado;

	private HashSet<Persona> visitas = new HashSet<>();

	public Local(Long id, String nombre, Persona encargado, Boolean paraAdultos) {
		setId(id);
		setNombre(nombre);
		setEncargado(encargado);
		setParaAdultos(paraAdultos);
	}

	public Local(String nombre, Persona encargado, Boolean paraAdultos) {
		this(null, nombre, encargado, paraAdultos);
	}
	
	public Local(String nombre, Persona encargado) {
		this(null, nombre, encargado, PARA_ADULTOS_POR_DEFECTO);
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

	public Persona getEncargado() {
		return encargado;
	}

	public void setEncargado(Persona encargado) {
		this.encargado = encargado;
	}

	public Boolean getParaAdultos() {
		return paraAdultos;
	}

	public void setParaAdultos(Boolean paraAdultos) {
		this.paraAdultos = paraAdultos;
	}

	public HashSet<Persona> getVisitas() {
		return visitas;
	}

	public void entrar(Persona visita) {
		if (visita == null) {
			throw new RuntimeException("No se admiten visitas nulas");
		}

		if (visita.isAnonimo()) { // Persona.NOMBRE_ANONIMO.equals(visita.getNombre())) {
			throw new RuntimeException("No se admiten visitas anónimas");
		}

		if(paraAdultos && !visita.isMayorDeEdad()) {
			throw new RuntimeException("No se admiten menores de edad en este local");
		}
		
		visitas.add(visita);
	}

	public void salir(Persona visita) {
		visitas.remove(visita);
	}

	@Override
	public String toString() {
		return String.format("Local [id=%s, nombre=%s, paraAdultos=%s, encargado=%s]", id, nombre, paraAdultos,
				encargado);
	}

}
