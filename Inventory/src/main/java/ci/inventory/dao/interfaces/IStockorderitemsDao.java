package ci.inventory.dao.interfaces;


import java.util.List;

import ci.inventory.entity.Stockorderitems;

public interface IStockorderitemsDao {
	
	//Definition Basics operations
	Stockorderitems create(Stockorderitems stockorderitems);
	Stockorderitems getById(int id);
	Stockorderitems update(Stockorderitems stockorderitems);
	int delete(int id);
	List<Stockorderitems> getAll();
}
