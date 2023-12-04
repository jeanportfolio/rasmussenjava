package ci.inventory.dao.interfaces;

import java.util.List;

import ci.inventory.entity.Products;

public interface IProductsDao {
	
	//Definition Basics operations
	Products create(Products products);
	Products getById(int id);
	Products update(Products products);
	int delete(int id);
	List<Products> getAll();
}
