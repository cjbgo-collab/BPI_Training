/**
 * 
 */
package com.bpi.helloworld.main;

/**
 * 
 */
public class Loop {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {

		int num = 0; 

		for (int i = 0; i <= 50; i++) { // initialize the loop unitil it equal to 50
			num = num + i; // num will add every increment
		}
		System.out.print("Sum = " + num); // print the sum
	}

}
