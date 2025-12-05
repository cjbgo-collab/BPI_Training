/**
 * 
 */
package com.bpi.helloworld.main;

public class Car {

	private String brand; // attribute
	private String color;
	private String name;

	public Car(String name, String brand, String color) {
		this.color = color;
		this.name = name;
		this.brand = brand;
	}

	public Car() {
		brand = "Unknown";
		name = "Unknown";
		color = "Unknown";
	}

	public String getname() {
		return name;
	}

	public String getbrand() {
		return brand;
	}

	public String getcolor() {
		return color;
	}

	public void setname(String name) {
		this.name = name;
	}

	public void setbrand(String brand) {
		this.brand = brand;
	}

	public void setcolor(String color) {
		this.color = color;
	}

	public void showDetails() {
		System.out.println("----------CAR DETAILS--------------");
		System.out.println("Brand: " + brand);
		System.out.println("Name: " + name);
		System.out.println("Color: " + color);
	}
}
