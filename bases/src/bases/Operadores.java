package bases;

public class Operadores {
	public static void main(String[] args) {
		System.out.println(5 ^ 2);
		System.out.println(Math.pow(5, 2));

		// System.out.println(5 == "5"); // No se puede porque java comprueba primero si
		// los tipos son comparables

		System.out.println(true ^ true);
		System.out.println(true ^ false);
		System.out.println(false ^ true);
		System.out.println(false ^ false);

		System.out.println();

		System.out.println(f1(false) & f2(true));
		System.out.println(f1(false) && f2(true));

		System.out.println(f1(true) | f2(false));
		System.out.println(f1(true) || f2(false));

		System.out.println(Integer.toBinaryString(0b101 & 0b011));

		int x;

		System.out.println(x = 5);

		System.out.println(x);

		int a, b, c;

		a = b = c = 5;

		System.out.printf("%s, %s, %s\n", a, b, c);

		System.out.println(a++);
		System.out.println(++b);

		boolean bool = false;

		System.out.println(bool ? "VERDADERO" : "FALSO");

		int valor = 3;

		System.out.println(valor == 1 ? "UNO" : valor == 2 ? "DOS" : "NO LO SE");

		System.out.println(switch (valor) {
		case 1 -> "UNO";
		case 2 -> "DOS";
		case 3, 4 -> "TRES O CUATRO";
		default -> "NO LO SE";
		});

//		switch (valor) {
//		case 1:
//			System.out.println("UNO");
//			break;
//		case 2:
//			System.out.println("DOS");
//			break;
//		case 3:
//		case 4:
//			System.out.println("TRES O CUATRO");
//			break;
//		default:
//			System.out.println("NO LO SE");
//		}
	}

	private static boolean f1(boolean b) {
		System.out.println("f1");
		return b;
	}

	private static boolean f2(boolean b) {
		System.out.println("f2");
		return b;
	}
}
