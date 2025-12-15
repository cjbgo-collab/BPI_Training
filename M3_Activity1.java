package M3_Activity1;
import java.util.*;
public class M3_Activity1 {
	
	public static void main(String[] args) {
		
		// Create a list of Porducts with 5 records
		List<String> products = new ArrayList<>();
		products.add("Laptop");
		products.add("Mouse");
		products.add("Keyboard");
		products.add("Monitor");
		products.add("Printer");
		
		// Print all the Products
		System.out.println("All Product List: ");
		 for (int i = 0; i < products.size(); i++) {
			 System.out.println((i + 1) + ". " + products.get((i)));
		 }
		
		// Add new product 
		products.add("Webcam");
		products.remove("Mouse");
		
		System.out.println("After adding and removing products: ");
		
		System.out.println("All Product List: ");
		 for (int i = 0; i < products.size(); i++) {
			 System.out.println((i + 1) + ". " + products.get((i)));
		 }
		
		// Search Product
		Scanner src = new Scanner(System.in);
		System.out.print("Enter product name to search: ");
		String search = src.nextLine();
		
		// Search using for-loop
		
		boolean found = false;
		for (String product : products) {
			if (product.equalsIgnoreCase(search)) {
				found = true;
				break;
			}
		}
		
		if (found) {
			
			System.out.println("Product found: " + search);
		}
		else {
			
			System.out.println("Product not found: " + search);
		}
		src.close();
	}
}
	
