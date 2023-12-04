package ci.inventory.services;

import java.util.List;

import ci.inventory.dao.ProductsDao;
import ci.inventory.entity.Products;
import ci.inventory.services.interfaces.IProductsService;

public class ProductsService implements IProductsService{
	
	//Properties
	private static ProductsDao dao;
	static {
		 dao = new ProductsDao();
	}
	
	@Override
	public Products create(Products products) {
		
		return dao.create(products);
	}

	@Override
	public Products get(int id) {
	
		return dao.getById(id);
	}

	@Override
	public Products update(Products products) {
		
		return dao.update(products);
	}

	@Override
	public int delete(int id) {
		
		return dao.delete(id);
	}

	@Override
	public List<Products> getAll() {
		
		return dao.getAll();
	}

}
