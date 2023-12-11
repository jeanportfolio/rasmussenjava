package ci.inventory.dao.interfaces;


import java.util.List;

import ci.inventory.entity.Orderitems;

public interface IOrderitemsDao {
	
	//Definition Basics operations
	Orderitems create(Orderitems orderitems);
	Orderitems getById(int id);
	Orderitems update(Orderitems orderitems);
	int delete(int id);
	List<Orderitems> getAll();
	List<Orderitems> getAllByCustomerOrder(int idcustomerorder);
}
