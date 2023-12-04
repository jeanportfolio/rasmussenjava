package ci.inventory.dao.interfaces;


import java.util.List;

import ci.inventory.entity.Categoryproduct;

public interface ICategoryproductDao {
	
	Categoryproduct create(Categoryproduct category);
	Categoryproduct getById(int id);
	Categoryproduct update(Categoryproduct category);
	int delete(int id);
	List<Categoryproduct> getAll();
}
