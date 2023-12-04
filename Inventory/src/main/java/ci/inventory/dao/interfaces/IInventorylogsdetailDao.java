package ci.inventory.dao.interfaces;


import java.util.List;

import ci.inventory.entity.Inventorylogsdetail;

public interface IInventorylogsdetailDao {
	Inventorylogsdetail create(Inventorylogsdetail inventorylogsdetail);
	Inventorylogsdetail getById(int id);
	Inventorylogsdetail update(Inventorylogsdetail inventorylogsdetail);
	int delete(int id);
	List<Inventorylogsdetail> getAll();
}
