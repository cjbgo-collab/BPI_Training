package M2_Activity6;

	public class Truck extends Vehicle implements Refuable {

		Truck(int getNumOfWheels, String brand) {
			super(getNumOfWheels, brand);
		}

		@Override
		public void refuel() {
		
			System.out.println(getBrand() + " with " + getNumOfWheels() + " Wheels" + " Truck is refueling....");
		}

		@Override
		void startEngine() {
			System.out.println(getBrand() + " with " + getNumOfWheels() + " Wheels"  + " Truck is engine starting....");

		}
	}