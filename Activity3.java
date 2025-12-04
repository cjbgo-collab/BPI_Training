/**
 * 
 */
package com.bpi.helloworld.main;

import java.util.Scanner;

/**
 * 
 */
public class Activity3 {

	static Scanner input = new Scanner(System.in);
	static int input1 = 0;
	static int input2 = 0;

	public static void get_difference(final int inputi, final int input2) {

		final int diff = input1 - input2;

		System.out.println("Difference:  " + diff);

	}

	public static void get_int1() {

		System.out.print("Enter first integer: ");
		input1 = input.nextInt();
	}

	public static void get_int2() {

		System.out.print("Enter second integer: ");

		input2 = input.nextInt();
	}

	public static void get_product(final int input1, final int input2) {

		final int prod = input1 * input2;

		System.out.println("Product:" + prod);
	}

	public static void get_sum(final int input1, final int input2) {

		final int total = input1 + input2;

		System.out.println("Sum:" + total);
	}

	public static void main(final String[] args) {

		get_int1();
		get_int2();
		get_sum(input1, input2);
		get_difference(input1, input2);
		get_product(input1, input2);

	}

}
