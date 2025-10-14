package modelos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rol {
	private Long id;
	
	@NotBlank
	@Size(min = 3, max = 10)
	private String nombre;
	
	@Size(max = 2000)
	private String descripcion;
}
