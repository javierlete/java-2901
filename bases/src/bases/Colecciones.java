package bases;

import java.util.ArrayList;

public class Colecciones {
	public static void main(String[] args) {
		ArrayList<Integer> lista = new ArrayList<>();

		lista.add(5);
		lista.add(6);

		System.out.println(lista.get(1));

		for (int dato : lista) {
			System.out.println(dato);
		}

		lista.forEach(System.out::println);
	}
}
