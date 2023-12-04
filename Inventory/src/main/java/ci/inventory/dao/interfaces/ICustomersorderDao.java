package ci.inventory.dao.interfaces;

import java.util.List;

import ci.inventory.entity.Customersorder;

public interface ICustomersorderDao {
	Customersorder create(Customersorder customerOrder);
	Customersorder getById(int id);
	Customersorder update(Customersorder customerOrder);
	int delete(int id);
	List<Customersorder> getAll();
}
