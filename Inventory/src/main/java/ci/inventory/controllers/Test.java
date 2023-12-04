package ci.inventory.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import ci.inventory.entity.Products;
import ci.inventory.entity.Stockinventory;
import ci.inventory.services.ProductsService;
import ci.inventory.services.StockinventoryService;

public class Test {

	public static void main(String[] args) {
		
		//Sample test to overview the basics functionalities of the application
		//
		// Create statically objects to save into the data base
		//Products occurrences
		// The id of the product are set to -1 because the IDs are auto-incremented and auto-generated
		Products product1 = new Products(-1, "Coka", "refreshing drink", new BigDecimal(1), new BigDecimal(1), 1, 1);
		Products product2 = new Products(-1, "Fanta", "refreshing drink", new BigDecimal(1), new BigDecimal(1), 1, 1);
		Products product3 = new Products(-1, "Sprite", "refreshing drink", new BigDecimal(1), new BigDecimal(1), 1, 1);
		Products product4 = new Products(-1, "Banana", "refreshing drink", new BigDecimal(1), new BigDecimal(1), 3, 1);
		
		// Stock occurrences
		// The id of the stock are set to -1 because the IDs are auto-incremented and auto-generated
		Stockinventory inventory1 = new Stockinventory(-1, 1, 0,"Stock Drink", 5, 100,1);
		Stockinventory inventory2 = new Stockinventory(-1, 1, 0,"Stock Vegetable", 10, 300,1);
		StockinventoryService inventoryservice = new StockinventoryService();
		ProductsService productservice = new ProductsService();
		
		// Save different objects in the database using their respective services
		productservice.create(product1);
		productservice.create(product2);
		productservice.create(product3);
		productservice.create(product4);
		
		
		// Get all the data save into the product and Stock table
		List<Stockinventory> list = inventoryservice.getAll();
		List<Products> listproduct = productservice.getAll();
		
		// Print all the Products from the database
		for (Products elmt : listproduct) {
			System.out.println(elmt);
		}
		
		// Print all the Stocks from the database
		for (Stockinventory elmt : list) {
			System.out.println(elmt);
		}
		
		Scanner sc = new Scanner(System.in);
		
		//Test of products suppression
		System.out.println("Enter a product id you want to delete ");
		int id = sc.nextInt();
		
		//Double check the willing of deleting a product by show the detail of the product
		System.out.println("Press 'Y' if you really want to delete product : "+ productservice.get(id)+ " \nesle press 'N'");
		char response = sc.next().toLowerCase().charAt(0);
		
		//If the response is 'Y' then delete the product from the database
		if(response == 'y')
			productservice.delete(id);
		
		// Print all the Products from the database
		listproduct = productservice.getAll();
		for (Products elmt : listproduct) {
			System.out.println(elmt);
		}
		
		sc.close();
		
	}

}
