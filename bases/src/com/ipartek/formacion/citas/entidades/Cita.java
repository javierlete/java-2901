package com.ipartek.formacion.citas.entidades;

import java.time.LocalDateTime;
import java.util.Objects;

public class Cita {
	private Long id;
	private Usuario usuario;
	private String texto;
	private LocalDateTime inicio;
	private LocalDateTime fin;

	public Cita(Long id, Usuario usuario, String texto, LocalDateTime inicio, LocalDateTime fin) {
		setId(id);
		setUsuario(usuario);
		setTexto(texto);
		setInicio(inicio);
		setFin(fin);
	}

	public Cita(Usuario usuario, String texto, LocalDateTime inicio, LocalDateTime fin) {
		this(null, usuario, texto, inicio, fin);
	}

	public Cita(Long id, String texto, LocalDateTime inicio, LocalDateTime fin) {
		this(id, null, texto, inicio, fin);
	}

	public Cita(String texto, LocalDateTime inicio, LocalDateTime fin) {
		this(null, null, texto, inicio, fin);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDateTime getInicio() {
		return inicio;
	}

	public void setInicio(LocalDateTime inicio) {
		this.inicio = inicio;
	}

	public LocalDateTime getFin() {
		return fin;
	}

	public void setFin(LocalDateTime fin) {
		this.fin = fin;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fin, id, inicio, texto, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cita other = (Cita) obj;
		return Objects.equals(fin, other.fin) && Objects.equals(id, other.id) && Objects.equals(inicio, other.inicio)
				&& Objects.equals(texto, other.texto) && Objects.equals(usuario, other.usuario);
	}

	@Override
	public String toString() {
		return String.format("Cita [id=%s, usuario=%s, texto=%s, inicio=%s, fin=%s]", id, usuario, texto, inicio, fin);
	}

}
