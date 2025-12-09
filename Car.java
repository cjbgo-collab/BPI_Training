package M2_Activity6;

	public class Car extends Vehicle implements Refuable {

		Car(int getNumOfWheels, String brand) {
			super(getNumOfWheels, brand);
		}

		@Override
		public void refuel() {
			System.out.println(getBrand() + " with " + getNumOfWheels() + " Wheels" + " car is refueling....");
		}

		@Override
		void startEngine() {
			System.out.println(getBrand() + " with " + getNumOfWheels() + " Wheels" + " car is engine starting....");

		}
	}