package ci.inventory.services.interfaces;


import java.util.List;

import ci.inventory.entity.Customers;

public interface ICustomersService {
	Customers create(Customers customer);
	Customers get(int id);
	Customers update(Customers customer);
	int delete(int id);
	List<Customers> getAll();
}
