package ci.inventory.services.interfaces;


import java.util.List;

import ci.inventory.entity.Inventorylogsdetail;

public interface IInventorylogsdetailService {
	Inventorylogsdetail create(Inventorylogsdetail inventorylogsdetail);
	Inventorylogsdetail get(int id);
	Inventorylogsdetail update(Inventorylogsdetail inventorylogsdetail);
	int delete(int id);
	List<Inventorylogsdetail> getAll();
}
