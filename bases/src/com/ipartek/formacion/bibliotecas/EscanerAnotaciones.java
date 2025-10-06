package com.ipartek.formacion.bibliotecas;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class EscanerAnotaciones {

	/**
	 * Busca todas las clases de un paquete que estén anotadas con la anotación
	 * indicada.
	 *
	 * @param packageName nombre del paquete (ej: "com.ejemplo")
	 * @param annotation  anotación a buscar (ej: Controlador.class)
	 * @return lista de clases encontradas
	 */
	@SuppressWarnings("unchecked")
	public static List<Class<?>> findClassesWithAnnotation(String packageName, Class<?> annotation)
			throws IOException, ClassNotFoundException {

		List<Class<?>> classes = new ArrayList<>();
		String path = packageName.replace('.', '/');
		Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(path);

		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			String protocol = resource.getProtocol();

			if ("file".equals(protocol)) {
				File directory = new File(resource.getFile());
				classes.addAll(findClassesInDirectory(directory, packageName, annotation));
			} else if ("jar".equals(protocol)) {
				JarURLConnection conn = (JarURLConnection) resource.openConnection();
				try (JarFile jarFile = conn.getJarFile()) {
					Enumeration<JarEntry> entries = jarFile.entries();
					while (entries.hasMoreElements()) {
						JarEntry entry = entries.nextElement();
						String name = entry.getName();
						if (name.startsWith(path) && name.endsWith(".class") && !entry.isDirectory()) {
							String className = name.replace('/', '.').substring(0, name.length() - 6);
							Class<?> clazz = Class.forName(className);
							if (clazz.isAnnotationPresent((Class<? extends Annotation>) annotation)) {
								classes.add(clazz);
							}
						}
					}
				}
			}
		}
		return classes;
	}

	@SuppressWarnings("unchecked")
	private static List<Class<?>> findClassesInDirectory(File directory, String packageName, Class<?> annotation)
			throws ClassNotFoundException {
		List<Class<?>> classes = new ArrayList<>();
		if (!directory.exists())
			return classes;

		File[] files = directory.listFiles();
		if (files == null)
			return classes;

		for (File file : files) {
			if (file.isDirectory()) {
				classes.addAll(findClassesInDirectory(file, packageName + "." + file.getName(), annotation));
			} else if (file.getName().endsWith(".class")) {
				String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);
				Class<?> clazz = Class.forName(className);
				if (clazz.isAnnotationPresent((Class<? extends Annotation>) annotation)) {
					classes.add(clazz);
				}
			}
		}
		return classes;
	}
}
