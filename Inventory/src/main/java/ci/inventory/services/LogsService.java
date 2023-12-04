package ci.inventory.services;

import java.util.List;

import ci.inventory.dao.LogsDao;
import ci.inventory.entity.Logs;
import ci.inventory.services.interfaces.ILogsService;

public class LogsService implements ILogsService{

	//Properties
	private static LogsDao dao;
	static {
		dao = new LogsDao();
	}

	@Override
	public Logs create(Logs logs) {

		return dao.create(logs);
	}

	@Override
	public Logs get(int id) {

		return dao.getById(id);
	}

	@Override
	public Logs update(Logs logs) {

		return dao.update(logs);
	}

	@Override
	public int delete(int id) {

		return dao.delete(id);
	}

	@Override
	public List<Logs> getAll() {

		return dao.getAll();
	}
}
