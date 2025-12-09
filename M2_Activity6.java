package M2_Activity6;

public class M2_Activity6 {

	private static void destroyVehicle(Vehicle v) {
		v.destroy();
	}

	public static void main(String[] args) {
		Vehicle car = new Car(4, "Toyota");
		Vehicle truck = new Truck(6, "Isuzu");

		car.startEngine();
		car.refuel();

		truck.startEngine();
		truck.refuel();

		destroyVehicle(car);
		destroyVehicle(truck);

	}

}