package ci.inventory.services;

import java.util.List;

import ci.inventory.dao.OrderitemsDao;
import ci.inventory.entity.Orderitems;
import ci.inventory.services.interfaces.IOrderitemsService;

public class OrderitemsService implements IOrderitemsService{

	//Properties
	private static OrderitemsDao dao;
	static {
		dao = new OrderitemsDao();
	}

	@Override
	public Orderitems create(Orderitems orderitems) {

		return dao.create(orderitems);
	}

	@Override
	public Orderitems get(int id) {

		return dao.getById(id);
	}

	@Override
	public Orderitems update(Orderitems orderitems) {

		return dao.update(orderitems);
	}

	@Override
	public int delete(int id) {

		return dao.delete(id);
	}

	@Override
	public List<Orderitems> getAll() {

		return dao.getAll();
	}

	@Override
	public List<Orderitems> getAllByCustomerOrder(int idcustomerorder) {
		
		return dao.getAllByCustomerOrder(idcustomerorder);
	}
}
