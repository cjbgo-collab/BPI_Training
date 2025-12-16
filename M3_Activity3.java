/**
 * 
 */
package M3_Activity3;

/**
 * 
 */
import java.util.*;
public class M3_Activity3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// INITIAL MAP OF PRODUCTS
		
		Map<String, Integer> products = new HashMap<>();
		products.put("Laptop", 50000);
		products.put("Monitor", 7000);
		products.put("Mouse", 500);
		products.put("Keyboard", 1000);
		products.put("Printer", 6000);
		
		Scanner sc = new Scanner(System.in);
		int choice;
		
		// MENU LOOP
		
		do {
			System.out.println("\n Select an option: ");
			System.out.println("1. Search a product ");
			System.out.println("2. Add a product  ");
			System.out.println("3. Print all products and prices ");
			System.out.println("4. Find the cheapest product ");
			System.out.println("5. Exit ");
			System.out.print(" > ");
			
			choice = sc.nextInt();
			sc.nextLine();
			
			// SEARCH
			switch (choice) {
			case 1:
				System.out.print("Enter product name to search: ");
				String searchName = sc.nextLine();
				
				if (products.containsKey(searchName))
				{
					System.out.print("Product found! Price: " + products.get(searchName));
				}
				else
				{
					System.out.print("Product not found! ");
				}
				break;
			case 2:
				System.out.print("Enter product name to Add: ");
				String newName = sc.nextLine();
				
				System.out.print("Enter price: ");
				int newPrice = sc.nextInt();
				
				products.put(newName, newPrice);
				System.out.print("Product added: " + newName);
				break;
			case 3:
				// PRINT ALL 
				System.out.println("All Products and prices: ");
				int total = 0;
				
				for (String name : products.keySet()) {
					int price = products.get(name);
					System.out.println(name + " - " + price);
					total++;
				}
				System.out.print("Total products: " + total);
				break;
			case 4:
				// FIND CHEAPEST
				if (products.isEmpty()) {
					System.out.print("No products available.");
					break;
				}
				
				String cheapestProduct = null;
				int cheapestPrice = Integer.MAX_VALUE;
				
				for (String name : products.keySet()) {
					int price = products.get(name);
						if (price < cheapestPrice) {
							cheapestPrice = price;
							cheapestProduct = name;
						}
				}
				
				System.out.println("Cheapest product: "  +  cheapestProduct + " - " + cheapestPrice);
				break;
			case 5:
				// EXIT
				System.out.println("Exiting....");
				break;
				
			default:
					System.out.print("Invalid Option");
				}
		
			}
			while (choice != 5);
			
			sc.close();
		}
	}	
		
		
		

