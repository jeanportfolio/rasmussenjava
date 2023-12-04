package ci.inventory.services;

import java.util.List;

import ci.inventory.dao.InventorylogsdetailDao;
import ci.inventory.entity.Inventorylogsdetail;
import ci.inventory.services.interfaces.IInventorylogsdetailService;

public class InventorylogsdetailService implements IInventorylogsdetailService{

	//Properties
	private static InventorylogsdetailDao dao;
	static {
		dao = new InventorylogsdetailDao();
	}

	@Override
	public Inventorylogsdetail create(Inventorylogsdetail inventorylogsdetail) {

		return dao.create(inventorylogsdetail);
	}

	@Override
	public Inventorylogsdetail get(int id) {

		return dao.getById(id);
	}

	@Override
	public Inventorylogsdetail update(Inventorylogsdetail inventorylogsdetail) {

		return dao.update(inventorylogsdetail);
	}

	@Override
	public int delete(int id) {

		return dao.delete(id);
	}

	@Override
	public List<Inventorylogsdetail> getAll() {

		return dao.getAll();
	}
}
