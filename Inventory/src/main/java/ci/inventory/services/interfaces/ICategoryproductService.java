package ci.inventory.services.interfaces;


import java.util.List;

import ci.inventory.entity.Categoryproduct;

public interface ICategoryproductService {
	
	Categoryproduct create(Categoryproduct category);
	Categoryproduct get(int id);
	Categoryproduct update(Categoryproduct category);
	int delete(int id);
	List<Categoryproduct> getAll();
}
