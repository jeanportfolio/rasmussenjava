package ci.inventory.services.interfaces;


import java.util.List;

import ci.inventory.entity.Orderitems;

public interface IOrderitemsService {
	
	//Definition Basics operations
	Orderitems create(Orderitems orderitems);
	Orderitems get(int id);
	Orderitems update(Orderitems orderitems);
	int delete(int id);
	List<Orderitems> getAll();
}
