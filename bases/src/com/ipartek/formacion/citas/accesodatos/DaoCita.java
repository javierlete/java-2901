package com.ipartek.formacion.citas.accesodatos;

import com.ipartek.formacion.citas.entidades.Cita;

public interface DaoCita extends Dao<Cita> {
	Iterable<Cita> buscarPorTexto(String texto);
}
