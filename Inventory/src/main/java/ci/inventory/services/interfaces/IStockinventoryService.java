package ci.inventory.services.interfaces;

import java.util.List;

import ci.inventory.entity.Stockinventory;

public interface IStockinventoryService {
	//Definition Basics operations
	Stockinventory create(Stockinventory stockinventory);
	Stockinventory get(int id);
	Stockinventory update(Stockinventory stockinventory);
	int delete(int id);
	List<Stockinventory> getAll();
}
