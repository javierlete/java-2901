package modelos;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "roles")
public class Rol {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private final Long id;
	
	@NotBlank
	@Size(min = 3, max = 20)
	private final String nombre;
	
	@Size(max = 2000)
	private final String descripcion;
	
	@Builder.Default
	@OneToMany(mappedBy = "rol")
	private List<Usuario> usuarios = new ArrayList<>();
}
