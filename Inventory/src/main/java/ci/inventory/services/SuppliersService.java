package ci.inventory.services;

import java.util.List;

import ci.inventory.dao.SuppliersDao;
import ci.inventory.entity.Suppliers;
import ci.inventory.services.interfaces.ISuppliersService;

public class SuppliersService implements ISuppliersService{

	//Properties
	private static SuppliersDao dao;
	static {
		dao = new SuppliersDao();
	}

	@Override
	public Suppliers create(Suppliers suppliers) {

		return dao.create(suppliers);
	}

	@Override
	public Suppliers get(int id) {

		return dao.getById(id);
	}

	@Override
	public Suppliers update(Suppliers suppliers) {

		return dao.update(suppliers);
	}

	@Override
	public int delete(int id) {

		return dao.delete(id);
	}

	@Override
	public List<Suppliers> getAll() {

		return dao.getAll();
	}
}
