package ci.inventory.services;

import java.util.List;

import ci.inventory.dao.CategoryproductDao;
import ci.inventory.entity.Categoryproduct;
import ci.inventory.services.interfaces.ICategoryproductService;

public class CategoryproductService implements ICategoryproductService{

	//Properties
	private static CategoryproductDao dao;
	static {
		dao = new CategoryproductDao();
	}

	@Override
	public Categoryproduct create(Categoryproduct categoryproduct) {

		return dao.create(categoryproduct);
	}

	@Override
	public Categoryproduct get(int id) {

		return dao.getById(id);
	}

	@Override
	public Categoryproduct update(Categoryproduct categoryproduct) {

		return dao.update(categoryproduct);
	}

	@Override
	public int delete(int id) {

		return dao.delete(id);
	}

	@Override
	public List<Categoryproduct> getAll() {

		return dao.getAll();
	}

}
