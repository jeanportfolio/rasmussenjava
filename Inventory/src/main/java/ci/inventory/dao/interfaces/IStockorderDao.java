package ci.inventory.dao.interfaces;

import java.util.List;

import ci.inventory.entity.Stockorder;

public interface IStockorderDao {
	
	//Definition Basics operations
	Stockorder create(Stockorder stockorder);
	Stockorder getById(int id);
	Stockorder update(Stockorder stockorder);
	int delete(int id);
	List<Stockorder> getAll();
}
