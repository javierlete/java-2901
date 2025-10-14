package bibliotecas.validaciones;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.ConstraintViolation;

public class Errores {
	public static final <T> Map<String, String> constraintViolationsAErrores(Set<ConstraintViolation<T>> constraintViolations) {
		return constraintViolations.stream().collect(
				Collectors.toMap(cv -> cv.getPropertyPath().toString(), ConstraintViolation::getMessage, (m1, m2) -> m1 + ", " + m2));
	}
}
