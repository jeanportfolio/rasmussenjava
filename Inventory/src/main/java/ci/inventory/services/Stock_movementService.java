package ci.inventory.services;

import java.util.List;

import ci.inventory.dao.Stock_movementDao;
import ci.inventory.entity.Stock_movement;
import ci.inventory.services.interfaces.IStoct_movementService;

public class Stock_movementService implements IStoct_movementService{

	//Properties
	private static Stock_movementDao dao;
	static {
		dao = new Stock_movementDao();
	}

	@Override
	public Stock_movement create(Stock_movement stoct_movement) {

		return dao.create(stoct_movement);
	}

	@Override
	public Stock_movement get(int id) {

		return dao.getById(id);
	}

	@Override
	public Stock_movement update(Stock_movement stoct_movement) {

		return dao.update(stoct_movement);
	}

	@Override
	public int delete(int id) {

		return dao.delete(id);
	}

	@Override
	public List<Stock_movement> getAll() {

		return dao.getAll();
	}
}
