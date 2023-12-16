package ci.inventory.services;

import java.time.LocalDate;
import java.util.List;

import ci.inventory.dao.InventorylogsDao;
import ci.inventory.entity.Inventorylogs;
import ci.inventory.services.interfaces.IInventorylogsService;

public class InventorylogsService implements IInventorylogsService{
	
	//Properties
	private static InventorylogsDao dao;
	static {
		dao = new InventorylogsDao();
	}
	@Override
	public Inventorylogs create(Inventorylogs inventorylogs) {
		// TODO Auto-generated method stub
		return dao.create(inventorylogs);
	}

	@Override
	public Inventorylogs get(int id) {
		// TODO Auto-generated method stub
		return dao.getById(id);
	}

	@Override
	public Inventorylogs update(Inventorylogs inventorylogs) {
		// TODO Auto-generated method stub
		return dao.update(inventorylogs);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return dao.delete(id);
	}

	@Override
	public List<Inventorylogs> getAll() {
		// TODO Auto-generated method stub
		return dao.getAll();
	}

	@Override
	public Inventorylogs getByDate(LocalDate now, int idusers) {
		// TODO Auto-generated method stub
		return dao.getByDate(now, idusers);
	}
	
}
