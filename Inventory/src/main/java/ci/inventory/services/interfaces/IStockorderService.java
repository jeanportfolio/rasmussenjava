package ci.inventory.services.interfaces;

import java.util.List;

import ci.inventory.entity.Stockorder;

public interface IStockorderService {
	
	//Definition Basics operations
	Stockorder create(Stockorder stockorder);
	Stockorder get(int id);
	Stockorder update(Stockorder stockorder);
	int delete(int id);
	List<Stockorder> getAll();
}
