package ci.inventory.services;

import java.util.List;

import ci.inventory.dao.StockinventoryDao;
import ci.inventory.entity.Stockinventory;
import ci.inventory.services.interfaces.IStockinventoryService;

public class StockinventoryService implements IStockinventoryService{

	//Properties
	private static StockinventoryDao dao;
	static {
		dao = new StockinventoryDao();
	}

	@Override
	public Stockinventory create(Stockinventory stockinventory) {

		return dao.create(stockinventory);
	}

	@Override
	public Stockinventory get(int id) {

		return dao.getById(id);
	}

	@Override
	public Stockinventory update(Stockinventory stockinventory) {

		return dao.update(stockinventory);
	}

	@Override
	public int delete(int id) {

		return dao.delete(id);
	}

	@Override
	public List<Stockinventory> getAll() {

		return dao.getAll();
	}

	public Stockinventory stockupdate(int idproduct, int quantity, int idusers) throws Exception {
		return dao.stockupdate(idproduct, quantity, idusers);
	}

	public Stockinventory getByIdProduct(int idproduct) {
		// TODO Auto-generated method stub
		return dao.getByIdProduct(idproduct);
	}
}
