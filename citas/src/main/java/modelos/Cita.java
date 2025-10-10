package modelos;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Cita {
	private Long id;
	private String texto;
	private LocalDateTime inicio;
	private LocalDateTime fin;

	private Map<String, String> errores = new HashMap<>();

	public Cita(Long id, String texto, LocalDateTime inicio, LocalDateTime fin) {
		setId(id);
		setTexto(texto);
		setInicio(inicio);
		setFin(fin);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		if (texto == null || texto.isBlank()) {
			errores.put("texto", "No se puede dejar en blanco");
		}

		this.texto = texto;
	}

	public LocalDateTime getInicio() {
		return inicio;
	}

	public void setInicio(LocalDateTime inicio) {
		if (inicio == null) {
			errores.put("inicio", "Se debe saber el inicio de una cita");
		}

		if (fin != null && inicio.isAfter(fin)) {
			errores.put("inicio", "La fecha de inicio no puede ser posterior a la de fin");
		}

		this.inicio = inicio;
	}

	public LocalDateTime getFin() {
		return fin;
	}

	public void setFin(LocalDateTime fin) {
		if (fin == null) {
			errores.put("fin", "Se debe saber el fin de una cita");
		}

		if (inicio != null && fin.isBefore(inicio)) {
			errores.put("fin", "La fecha de fin no puede ser anterior a la de inicio");
		}

		this.fin = fin;
	}

	public Map<String, String> getErrores() {
		return errores;
	}

	public boolean tieneErrores() {
		return errores.size() != 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fin, id, inicio, texto);
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
				&& Objects.equals(texto, other.texto);
	}

	@Override
	public String toString() {
		return String.format("Cita [id=%s, texto=%s, inicio=%s, fin=%s]", id, texto, inicio, fin);
	}

}
