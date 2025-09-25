package com.ipartek.formacion.pruebas;

import java.util.function.BinaryOperator;

import com.ipartek.formacion.pojos.Calculadora;

public class CalculadoraPrueba {
	public static void main(String[] args) {
		Calculadora.setX(5);
		Calculadora.setOperacion('+');
		Calculadora.setY(6);
		
		System.out.println(Calculadora.calcular());
		
		Calculadora.setX(4);
		Calculadora.setOperacion((a, b) -> (int)Math.sqrt(a));
		
		System.out.println(Calculadora.calcular());
		
		BinaryOperator<Integer> calculadora;
		
		calculadora = (a, b) -> (int)Math.pow(a, b);
		
		System.out.println(calculadora.apply(2, 4));
	}
}
