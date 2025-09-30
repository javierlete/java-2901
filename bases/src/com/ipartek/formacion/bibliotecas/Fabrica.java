package com.ipartek.formacion.bibliotecas;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Properties;

public class Fabrica {
	private static final Properties props = new Properties();

	private static final HashMap<String, Object> objetos = new HashMap<>();
	
	static {
		try {
			props.load(new FileReader("fabrica.properties"));
		} catch (IOException e) {
			throw new ExceptionInInitializerError("No se ha podido leer la configuración: " + e.getMessage());
		}
	}
	
	public static Object obtenerObjeto(String tipo) {
		try {
			String strClase = props.getProperty(tipo);

			if(objetos.containsKey(strClase)) {
				return objetos.get(strClase);
			}
			
			Class<?> clase = Class.forName(strClase);
			Object objeto = clase.getConstructor().newInstance(); // new clase();
			
			objetos.put(strClase, objeto);
			
			return objeto;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new IllegalArgumentException("No se ha podido construir el objeto " + tipo, e);
		}
	}
}
