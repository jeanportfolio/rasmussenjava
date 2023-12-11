package ci.inventory.dao.interfaces;

import java.util.List;

import ci.inventory.entity.Stockorder;
import ci.inventory.entity.Stockorderitems;

public interface IStockorderDao {
	
	//Definition Basics operations
	Stockorder create(Stockorder stockorder);
	Stockorder getById(int id);
	Stockorder update(Stockorder stockorder);
	int delete(int id);
	List<Stockorder> getAll();
	Stockorder create(Stockorder stockorder, List<Stockorderitems> liststockorderitem);
	Stockorder update(Stockorder stockorder, List<Stockorderitems> liststockorderitem);
}
