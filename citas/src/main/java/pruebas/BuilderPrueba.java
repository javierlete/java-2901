package pruebas;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import modelos.Cita;

public class BuilderPrueba {
	private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private static Validator validator = factory.getValidator();

	public static void main(String[] args) {
//		var cita = new Cita(null, "texto", LocalDateTime.now(), LocalDateTime.now().minusHours(2));
		var cita = Cita.builder().fin(LocalDateTime.now().minusHours(1)).texto("").inicio(LocalDateTime.now())
				.build();

		Set<ConstraintViolation<Cita>> constraintViolations = validator.validate(cita);
		
		if (constraintViolations.isEmpty()) {
			System.out.println(cita);
		} else {
			System.out.println(constraintViolations);
		}
	}
}
