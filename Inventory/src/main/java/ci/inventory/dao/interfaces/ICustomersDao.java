package ci.inventory.dao.interfaces;


import java.util.List;

import ci.inventory.entity.Customers;

public interface ICustomersDao {
	Customers create(Customers customer);
	Customers getById(int id);
	Customers update(Customers customer);
	int delete(int id);
	List<Customers> getAll();
}
