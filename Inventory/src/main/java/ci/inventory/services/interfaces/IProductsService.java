package ci.inventory.services.interfaces;

import java.util.List;

import ci.inventory.entity.Products;

public interface IProductsService {
	
	//Definition Basics operations
	Products create(Products products);
	Products get(int id);
	Products update(Products products);
	int delete(int id);
	List<Products> getAll();
}
