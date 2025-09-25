package com.ipartek.formacion.pojos;

import java.util.ArrayList;

public class Impresora {
	private ArrayList<Imprimible> cola = new ArrayList<>();
	
	public void agregarImprimible(Imprimible imprimible) {
		cola.add(imprimible);
	}
	
	public void imprimir() {
		cola.stream().forEach((imprimible) -> System.out.println(imprimible.imprimir()));
		
//		for(Imprimible imprimible: cola) {
//			System.out.println(imprimible.imprimir());
//		}
		
//		Iterator<Imprimible> iterator = cola.iterator();
//		
//		while(iterator.hasNext()) {
//			Imprimible imprimible = iterator.next();
//			System.out.println(imprimible.imprimir());
//		}
		
		vaciarCola();
	}
	
	public void vaciarCola() {
		cola.clear();
	}
}
