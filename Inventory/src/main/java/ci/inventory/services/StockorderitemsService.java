package ci.inventory.services;

import java.util.List;

import ci.inventory.dao.StockorderitemsDao;
import ci.inventory.entity.Stockorderitems;
import ci.inventory.services.interfaces.IStockorderitemsService;

public class StockorderitemsService implements IStockorderitemsService{

	//Properties
	private static StockorderitemsDao dao;
	static {
		dao = new StockorderitemsDao();
	}

	@Override
	public Stockorderitems create(Stockorderitems stockorderitems) {

		return dao.create(stockorderitems);
	}

	@Override
	public Stockorderitems get(int id) {

		return dao.getById(id);
	}

	@Override
	public Stockorderitems update(Stockorderitems stockorderitems) {

		return dao.update(stockorderitems);
	}

	@Override
	public int delete(int id) {

		return dao.delete(id);
	}

	@Override
	public List<Stockorderitems> getAll() {

		return dao.getAll();
	}
}
