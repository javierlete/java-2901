package com.ipartek.formacion.bibliotecas;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.logging.Level;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.java.Log;

@Log
public class FechaPosteriorValidator implements ConstraintValidator<FechaPosterior, Object> {

	private String campoInicio;
	private String campoFin;

	@Override
	public void initialize(FechaPosterior constraintAnnotation) {
		this.campoInicio = constraintAnnotation.fechaInicio();
		this.campoFin = constraintAnnotation.fechaFin();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		try {
			Field fInicio = value.getClass().getDeclaredField(campoInicio);
			Field fFin = value.getClass().getDeclaredField(campoFin);

			fInicio.setAccessible(true);
			fFin.setAccessible(true);

			LocalDateTime inicio = (LocalDateTime) fInicio.get(value);
			LocalDateTime fin = (LocalDateTime) fFin.get(value);

			if (inicio == null || fin == null) {
				return true; // se deja a otras validaciones @NotNull
			}

			return fin.isAfter(inicio);

		} catch (Exception e) {
			log.log(Level.WARNING, "Error en validación de fecha posterior", e);
			return false;
		}
	}
}
