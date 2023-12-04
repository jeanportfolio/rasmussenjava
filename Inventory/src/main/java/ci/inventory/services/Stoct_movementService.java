package ci.inventory.services;

import java.util.List;

import ci.inventory.dao.Stoct_movementDao;
import ci.inventory.entity.Stoct_movement;
import ci.inventory.services.interfaces.IStoct_movementService;

public class Stoct_movementService implements IStoct_movementService{

	//Properties
	private static Stoct_movementDao dao;
	static {
		dao = new Stoct_movementDao();
	}

	@Override
	public Stoct_movement create(Stoct_movement stoct_movement) {

		return dao.create(stoct_movement);
	}

	@Override
	public Stoct_movement get(int id) {

		return dao.getById(id);
	}

	@Override
	public Stoct_movement update(Stoct_movement stoct_movement) {

		return dao.update(stoct_movement);
	}

	@Override
	public int delete(int id) {

		return dao.delete(id);
	}

	@Override
	public List<Stoct_movement> getAll() {

		return dao.getAll();
	}
}
