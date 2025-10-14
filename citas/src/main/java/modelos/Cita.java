package modelos;

import java.time.LocalDateTime;

import bibliotecas.validaciones.FechaPosterior;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@FechaPosterior(fechaInicio = "inicio", fechaFin = "fin",
message = "La fecha de fin debe ser posterior a la fecha de inicio")
public class Cita {
	private Long id;
	
	@NotBlank
	@Size(min = 3, max = 50)
	private String texto;
	
	@NotNull
	private LocalDateTime inicio;
	
	@NotNull
	private LocalDateTime fin;
}
