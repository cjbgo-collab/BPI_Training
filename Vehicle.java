package M2_Activity6;

public abstract class Vehicle {
	private int numOfWheels;
	private String brand;

	public Vehicle(int numOfWheels, String brand) {
		this.numOfWheels = numOfWheels;
		this.brand = brand;
	}

	public Vehicle() {

	}

	public int getNumOfWheels() {
		return numOfWheels;
	}

	public void setNumOfWheels(int numOfWheels) {
		this.numOfWheels = numOfWheels;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	abstract void startEngine();

	void destroy() {
		System.out.println(brand + " is now destroyed.");
	}

	protected abstract void refuel();

}
