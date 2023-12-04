package ci.inventory.services;


import java.util.List;

import ci.inventory.dao.CustomersorderDao;
import ci.inventory.entity.Customersorder;
import ci.inventory.services.interfaces.ICustomersorderService;

public class CustomersorderService implements ICustomersorderService{

	//Properties
	private static CustomersorderDao dao;
	static {
		dao = new CustomersorderDao();
	}

	@Override
	public Customersorder create(Customersorder customersorder) {

		return dao.create(customersorder);
	}

	@Override
	public Customersorder get(int id) {

		return dao.getById(id);
	}

	@Override
	public Customersorder update(Customersorder customersorder) {

		return dao.update(customersorder);
	}

	@Override
	public int delete(int id) {

		return dao.delete(id);
	}

	@Override
	public List<Customersorder> getAll() {

		return dao.getAll();
	}
}
