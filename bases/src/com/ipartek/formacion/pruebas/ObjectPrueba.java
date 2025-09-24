package com.ipartek.formacion.pruebas;

public class ObjectPrueba {
	public static void main(String[] args) {
		Object o = new Object();
		Object o2 = new Object();
		
		System.out.println(o.equals(o2));
		System.out.println(Integer.toHexString(o.hashCode()));
		System.out.println(o.toString());
		System.out.println(o.getClass().getName());
	}
}
