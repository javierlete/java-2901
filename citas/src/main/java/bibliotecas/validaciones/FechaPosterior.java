package bibliotecas.validaciones;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FechaPosteriorValidator.class)
@Target({ ElementType.TYPE }) // Se aplica a nivel de clase
@Retention(RetentionPolicy.RUNTIME)
public @interface FechaPosterior {
    String message() default "La fecha final debe ser posterior a la fecha inicial";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String fechaInicio();
    String fechaFin();
}
