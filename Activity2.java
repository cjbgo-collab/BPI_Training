/**
 * 
 */
package com.bpi.helloworld.main;
import java.util.Scanner;
/**
 * 
 */
public class Activity2 {

public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter your age");
		String intInput = input.next();
		
		int num = Integer.parseInt(intInput);
		double price = Double.parseDouble(intInput);
		
		System.out.println("Your age as int: " + num );
		System.out.println("Your age as double: " + price );

	}

}
