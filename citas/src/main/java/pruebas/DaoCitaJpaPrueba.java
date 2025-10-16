package pruebas;

import java.time.LocalDateTime;

import accesodatos.DaoCita;
import bibliotecas.Fabrica;
import modelos.Cita;

public class DaoCitaJpaPrueba {
	public static void main(String[] args) {
		DaoCita dao = (DaoCita) Fabrica.obtenerObjeto("dao.cita");

		dao.insertar(Cita.builder()
			    .texto("Reunión de planificación del proyecto")
			    .inicio(LocalDateTime.of(2025, 1, 2, 9, 30))
			    .fin(LocalDateTime.of(2025, 1, 2, 11, 0))
			    .build());

			dao.insertar(Cita.builder()
			    .texto("Consulta médica anual")
			    .inicio(LocalDateTime.of(2025, 1, 3, 16, 0))
			    .fin(LocalDateTime.of(2025, 1, 3, 16, 45))
			    .build());

			dao.insertar(Cita.builder()
			    .texto("Clase de yoga en el gimnasio")
			    .inicio(LocalDateTime.of(2025, 1, 4, 18, 0))
			    .fin(LocalDateTime.of(2025, 1, 4, 19, 15))
			    .build());

			dao.insertar(Cita.builder()
			    .texto("Cena con Marta y Luis")
			    .inicio(LocalDateTime.of(2025, 1, 5, 20, 30))
			    .fin(LocalDateTime.of(2025, 1, 5, 22, 30))
			    .build());

			dao.insertar(Cita.builder()
			    .texto("Presentación de resultados trimestrales")
			    .inicio(LocalDateTime.of(2025, 1, 6, 10, 0))
			    .fin(LocalDateTime.of(2025, 1, 6, 12, 0))
			    .build());

			dao.insertar(Cita.builder()
			    .texto("Café con antiguo compañero de trabajo")
			    .inicio(LocalDateTime.of(2025, 1, 7, 17, 0))
			    .fin(LocalDateTime.of(2025, 1, 7, 18, 0))
			    .build());

			dao.insertar(Cita.builder()
			    .texto("Taller de fotografía")
			    .inicio(LocalDateTime.of(2025, 1, 8, 15, 0))
			    .fin(LocalDateTime.of(2025, 1, 8, 18, 0))
			    .build());

			dao.insertar(Cita.builder()
			    .texto("Partido de fútbol con amigos")
			    .inicio(LocalDateTime.of(2025, 1, 9, 19, 0))
			    .fin(LocalDateTime.of(2025, 1, 9, 21, 0))
			    .build());

			dao.insertar(Cita.builder()
			    .texto("Revisión del coche en el taller")
			    .inicio(LocalDateTime.of(2025, 1, 10, 8, 0))
			    .fin(LocalDateTime.of(2025, 1, 10, 9, 30))
			    .build());

			dao.insertar(Cita.builder()
			    .texto("Excursión a la montaña")
			    .inicio(LocalDateTime.of(2025, 1, 11, 7, 0))
			    .fin(LocalDateTime.of(2025, 1, 11, 14, 0))
			    .build());

			dao.obtenerTodos().forEach(System.out::println);
	}
}
