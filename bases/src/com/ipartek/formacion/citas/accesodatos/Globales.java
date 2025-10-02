package com.ipartek.formacion.citas.accesodatos;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

class Globales {
	static String URL = "jdbc:sqlite:bdd/citas.db?foreign_keys=on";
	static String USER = "";
	static String PASS = "";
	
	static {
		try {
			Properties props = new Properties();
			
			props.load(new FileReader("fabrica.properties"));
			
			URL = props.getProperty("dao.url");
			USER = props.getProperty("dao.user");
			PASS = props.getProperty("dao.pass");
			
			String driver = props.getProperty("dao.driver");
			
			Class.forName(driver);
		} catch (ClassNotFoundException | IOException e) {
			throw new IllegalStateException("No se ha podido leer la configuración", e);
		}
	}
}
