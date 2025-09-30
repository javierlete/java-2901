package com.ipartek.formacion.citas.accesodatos;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Fabrica {
	private static final DaoCita dao;
	
	static {
		try {
			Properties props = new Properties();
			props.load(new FileReader("fabrica.properties"));
			
			String tipo = props.getProperty("dao");
			
			dao = switch(tipo) {
			case "arraylist" -> new DaoCitaArrayList();
			case "treemap" -> new DaoCitaTreeMap();
			case "jdbc" -> new DaoCitaJdbc();
			default -> null;
			};
		} catch (IOException e) {
			throw new AccesoDatosException("No se ha podido leer la configuración", e);
		}
	}
	
	public static DaoCita obtenerDao() {
		return dao;
	}
}
