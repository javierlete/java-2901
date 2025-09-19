package bases;

import java.util.Arrays;

public class ArraysEjemplo {
	public static void main(String[] args) {
		int tamano = 2;
		int[] arr = new int[tamano];
		
		arr[0] = 5;
		arr[1] = 6;
//		arr[2] = 7;
		
		System.out.println(arr[1]);
		
		System.out.println(arr.length);
		
		System.out.println(Arrays.toString(arr));
		
		for(int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
		
		for(int dato: arr) {
			System.out.println(dato);
		}
	}
}
