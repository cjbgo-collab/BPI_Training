package com.bpi.helloworld.main;

public class M2_Activity3 {

	public static void main(final String[] args) {
		// Using no - argument constructor

		Car car1 = new Car();
		car1.setbrand("Toyota");
		car1.setname("Vios");
		car1.setcolor("Black");

		System.out.println(car1.getbrand());

		// using parameterized constructor
		Car car2 = new Car("Sealion 6", "BYD", "Azure Blue");

		car1.showDetails();
		car2.showDetails();

	}

}