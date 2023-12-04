package ci.inventory.services.interfaces;


import java.util.List;

import ci.inventory.entity.Stockorderitems;

public interface IStockorderitemsService {
	
	//Definition Basics operations
	Stockorderitems create(Stockorderitems stockorderitems);
	Stockorderitems get(int id);
	Stockorderitems update(Stockorderitems stockorderitems);
	int delete(int id);
	List<Stockorderitems> getAll();
}
