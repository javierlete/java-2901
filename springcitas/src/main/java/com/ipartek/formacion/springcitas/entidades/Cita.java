package com.ipartek.formacion.springcitas.entidades;

import java.time.LocalDateTime;

import com.ipartek.formacion.bibliotecas.FechaPosterior;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

@Entity
@Table(name = "citas")

@FechaPosterior(fechaInicio = "inicio", fechaFin = "fin",
message = "La fecha de fin debe ser posterior a la fecha de inicio")
public class Cita {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(min = 3, max = 50)
	private String texto;
	
	@NotNull
	private LocalDateTime inicio;
	
	@NotNull
	private LocalDateTime fin;
}
