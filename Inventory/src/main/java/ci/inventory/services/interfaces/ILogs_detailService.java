package ci.inventory.services.interfaces;

import java.util.List;

import ci.inventory.entity.Logs_detail;

public interface ILogs_detailService {
	Logs_detail create(Logs_detail logs_detail);
	Logs_detail get(int id);
	Logs_detail update(Logs_detail logs_detail);
	int delete(int id);
	List<Logs_detail> getAll();
}
