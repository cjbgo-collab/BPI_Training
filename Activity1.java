/**
 * 
 */
package com.bpi.helloworld.main;

import java.util.Scanner;

/**
 * 
 */
public class Activity1 {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {

		try (Scanner input = new Scanner(System.in)) {
			System.out.println("What is your name?");
			final String intInput = input.next();

			System.out.println("Hello, " + intInput + "!");
		}

	}
}
