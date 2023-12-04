package ci.inventory.dao.interfaces;


import java.util.List;

import ci.inventory.entity.Inventorylogs;

public interface IInventorylogsDao {
	Inventorylogs create(Inventorylogs inventorylogs);
	Inventorylogs getById(int id);
	Inventorylogs update(Inventorylogs inventorylogs);
	int delete(int id);
	List<Inventorylogs> getAll();
}
