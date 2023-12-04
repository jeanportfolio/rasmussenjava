package ci.inventory.services.interfaces;


import java.util.List;

import ci.inventory.entity.Suppliers;

public interface ISuppliersService {
	
	//Definition Basics operations
	Suppliers create(Suppliers suppliers);
	Suppliers get(int id);
	Suppliers update(Suppliers suppliers);
	int delete(int id);
	List<Suppliers> getAll();
}
