/**
 * 
 */
package com.bpi.helloworld.main;

import java.util.Scanner;

/**
 * 
 */
public class Activity4 {

	public static void main(final String[] args) {

		final Scanner input = new Scanner(System.in);

		System.out.print("Enter your Age: ");
		final int age = input.nextInt();

		if (age < 18) {
			System.out.print("Minor");
		} else if (age >= 18 & age <= 59) {
			System.out.print("Adult");
		} else {
			System.out.print("Senior");

		}

	}

}
