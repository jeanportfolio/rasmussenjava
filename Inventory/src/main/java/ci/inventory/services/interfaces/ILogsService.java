package ci.inventory.services.interfaces;

import java.util.List;

import ci.inventory.entity.Logs;

public interface ILogsService {
	Logs create(Logs logs);
	Logs get(int id);
	Logs update(Logs logs);
	int delete(int id);
	List<Logs> getAll();
}
