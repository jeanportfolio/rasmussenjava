package ci.inventory.services;


import java.util.List;

import ci.inventory.dao.CustomersDao;
import ci.inventory.entity.Customers;
import ci.inventory.services.interfaces.ICustomersService;

public class CustomersService implements ICustomersService{

	//Properties
	private static CustomersDao dao;
	static {
		dao = new CustomersDao();
	}

	@Override
	public Customers create(Customers customers) {

		return dao.create(customers);
	}

	@Override
	public Customers get(int id) {

		return dao.getById(id);
	}

	@Override
	public Customers update(Customers customers) {

		return dao.update(customers);
	}

	@Override
	public int delete(int id) {

		return dao.delete(id);
	}

	@Override
	public List<Customers> getAll() {

		return dao.getAll();
	}
}
