package ci.inventory.services.interfaces;

import java.util.List;

import ci.inventory.entity.Customersorder;
import ci.inventory.entity.Orderitems;

public interface ICustomersorderService {
	Customersorder create(Customersorder customerOrder);
	Customersorder get(int id);
	Customersorder update(Customersorder customerOrder);
	int delete(int id);
	List<Customersorder> getAll();
	Customersorder create(Customersorder customerorder, List<Orderitems> listorderitem);
	Customersorder update(Customersorder customerorder, List<Orderitems> listorderitem);
}
