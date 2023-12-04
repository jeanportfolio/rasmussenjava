package ci.inventory.services;

import java.util.List;

import ci.inventory.dao.Logs_detailDao;
import ci.inventory.entity.Logs_detail;
import ci.inventory.services.interfaces.ILogs_detailService;

public class Logs_detailService implements ILogs_detailService{

	//Properties
	private static Logs_detailDao dao;
	static {
		dao = new Logs_detailDao();
	}

	@Override
	public Logs_detail create(Logs_detail logs_detail) {

		return dao.create(logs_detail);
	}

	@Override
	public Logs_detail get(int id) {

		return dao.getById(id);
	}

	@Override
	public Logs_detail update(Logs_detail logs_detail) {

		return dao.update(logs_detail);
	}

	@Override
	public int delete(int id) {

		return dao.delete(id);
	}

	@Override
	public List<Logs_detail> getAll() {

		return dao.getAll();
	}
}
