package com.ipartek.formacion.citas.accesodatos;

public class Fabrica {
	public static DaoCita obtenerDao(String tipo) {
		return switch(tipo) {
		case "arraylist" -> new DaoCitaArrayList();
		case "treemap" -> new DaoCitaTreeMap();
		case "jdbc" -> new DaoCitaJdbc();
		default -> null;
		};
	}
}
