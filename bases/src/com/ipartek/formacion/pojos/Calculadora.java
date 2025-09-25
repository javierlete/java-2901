package com.ipartek.formacion.pojos;

import java.util.function.BinaryOperator;

public class Calculadora {
	private static Integer x;
	private static Integer y;
	private static BinaryOperator<Integer> operacion;

	public static Integer getX() {
		return x;
	}

	public static void setX(Integer x) {
		Calculadora.x = x;
	}

	public static Integer getY() {
		return y;
	}

	public static void setY(Integer y) {
		Calculadora.y = y;
	}

	public static BinaryOperator<Integer> getOperacion() {
		return operacion;
	}

	public static void setOperacion(BinaryOperator<Integer> operacion) {
		Calculadora.operacion = operacion;
	}
	
	public static void setOperacion(char operacion) {
		setOperacion(switch(operacion) {
		case '+' -> (a, b) -> a + b;
		case '-' -> (a, b) -> a - b;
		case '*' -> (a, b) -> a * b;
		case '/' -> (a, b) -> a / b;
		default -> throw new RuntimeException("Operación desconocida");
		});
	}
	
	public static Integer calcular() {
		return operacion.apply(x, y);
	}

}
