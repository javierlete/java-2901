package modelos;

import jakarta.validation.constraints.Email;
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
public class Usuario {
	private Long id;
	
	@NotBlank
	@Email
	@Size(min = 3, max = 50)
	private String email;
	
	@NotBlank
	@Size(min = 3, max = 100)
	private String password;
	
	@NotBlank
	@Size(min = 3, max = 20)
	private String nombre;
	
	@NotNull
	private Rol rol;
}
