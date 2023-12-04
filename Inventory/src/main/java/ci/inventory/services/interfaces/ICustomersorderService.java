package ci.inventory.services.interfaces;

import java.util.List;

import ci.inventory.entity.Customersorder;

public interface ICustomersorderService {
	Customersorder create(Customersorder customerOrder);
	Customersorder get(int id);
	Customersorder update(Customersorder customerOrder);
	int delete(int id);
	List<Customersorder> getAll();
}
