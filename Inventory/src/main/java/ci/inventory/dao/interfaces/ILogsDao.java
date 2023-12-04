package ci.inventory.dao.interfaces;

import java.util.List;

import ci.inventory.entity.Logs;

public interface ILogsDao {
	Logs create(Logs logs);
	Logs getById(int id);
	Logs update(Logs logs);
	int delete(int id);
	List<Logs> getAll();
}
