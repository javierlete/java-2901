package accesodatos;

import bibliotecas.accesodatos.DaoJpa;
import modelos.Cita;

public class DaoCitaJpa extends DaoJpa<Cita> implements DaoCita {

	public DaoCitaJpa() {
		super(Cita.class);
	}
	
}
