package ci.inventory.services;

import java.util.List;

import ci.inventory.dao.StockorderDao;
import ci.inventory.entity.Stockorder;
import ci.inventory.entity.Stockorderitems;
import ci.inventory.services.interfaces.IStockorderService;

public class StockorderService implements IStockorderService{

	//Properties
	private static StockorderDao dao;
	static {
		dao = new StockorderDao();
	}

	@Override
	public Stockorder create(Stockorder stockorder) {

		return dao.create(stockorder);
	}

	@Override
	public Stockorder get(int id) {

		return dao.getById(id);
	}

	@Override
	public Stockorder update(Stockorder stockorder) {

		return dao.update(stockorder);
	}

	@Override
	public int delete(int id) {

		return dao.delete(id);
	}

	@Override
	public List<Stockorder> getAll() {

		return dao.getAll();
	}

	@Override
	public Stockorder create(Stockorder stockorder, List<Stockorderitems> liststockitems) {
		return dao.create(stockorder, liststockitems);
		
	}

	@Override
	public Stockorder update(Stockorder stockorder, List<Stockorderitems> liststockitems) {
		return dao.update(stockorder, liststockitems);
		
	}
}
