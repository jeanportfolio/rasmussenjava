package ci.inventory.services.interfaces;


import java.util.List;

import ci.inventory.entity.Inventorylogs;

public interface IInventorylogsService {
	Inventorylogs create(Inventorylogs inventorylogs);
	Inventorylogs get(int id);
	Inventorylogs update(Inventorylogs inventorylogs);
	int delete(int id);
	List<Inventorylogs> getAll();
}
