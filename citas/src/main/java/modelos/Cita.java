package modelos;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cita {
	private Long id;
	private String texto;
	private LocalDateTime inicio;
	private LocalDateTime fin;

	@Setter(AccessLevel.NONE)
	@Builder.Default
	private Map<String, String> errores = new HashMap<>();

	public Cita(Long id, String texto, LocalDateTime inicio, LocalDateTime fin) {
		setId(id);
		setTexto(texto);
		setInicio(inicio);
		setFin(fin);
	}

	public void setTexto(String texto) {
		if (texto == null || texto.isBlank()) {
			errores.put("texto", "No se puede dejar en blanco");
		}

		this.texto = texto;
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

	public void setFin(LocalDateTime fin) {
		if (fin == null) {
			errores.put("fin", "Se debe saber el fin de una cita");
		}

		if (inicio != null && fin.isBefore(inicio)) {
			errores.put("fin", "La fecha de fin no puede ser anterior a la de inicio");
		}

		this.fin = fin;
	}

	public boolean tieneErrores() {
		return errores.size() != 0;
	}
}
