package bases;

import java.math.BigDecimal;

public class TiposDeDatos {
	public static void main(String[] args) {
		System.out.println("Podemos usar unicode para casi todo: ☺️");
		
		char año = 'à';
		
		System.out.println(año);
		
		double d1 = 0.1, d2 = 0.2;
		
		double suma = d1 + d2;
		
		System.out.printf("%.2f\n", suma);
		System.out.println(suma);
		
		System.out.println("Hola\r\n"
				+ "Qué tal");
		
		System.out.println("""
				Hola
				Qué tal
				""");
		
//		double dPrimitivo = null;
		Double dEncapsulado = null;
		
		System.out.println(dEncapsulado);
		
		String textoConNumero = "1234";
		
		int numero = Integer.parseInt(textoConNumero);
		
		System.out.println(numero * 2);
		
		BigDecimal bd1 = new BigDecimal("0.1");
		BigDecimal bd2 = new BigDecimal("0.2");
		
		System.out.println(bd1);
		System.out.println(bd2);
		
		BigDecimal bdSuma = bd1.add(bd2);
		
		System.out.println(bdSuma);
	}
}
