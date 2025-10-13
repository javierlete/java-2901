package pruebas;

import java.time.LocalDateTime;

import modelos.Cita;

public class BuilderPrueba {
	public static void main(String[] args) {
//		var cita = new Cita(null, "texto", LocalDateTime.now(), LocalDateTime.now().minusHours(2));
		var cita = Cita.builder().fin(LocalDateTime.now().minusHours(1)).texto("Prueba").inicio(LocalDateTime.now()).build();
		
		if(cita.tieneErrores()) {
			System.out.println(cita.getErrores());
		} else {
			System.out.println(cita);
		}
	}
}
