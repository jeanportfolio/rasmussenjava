package ci.inventory.dao.interfaces;


import java.util.List;

import ci.inventory.entity.Suppliers;

public interface ISuppliersDao {
	
	//Definition Basics operations
	Suppliers create(Suppliers suppliers);
	Suppliers getById(int id);
	Suppliers update(Suppliers suppliers);
	int delete(int id);
	List<Suppliers> getAll();
}
