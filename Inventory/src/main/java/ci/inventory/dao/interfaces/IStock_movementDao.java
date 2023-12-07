package ci.inventory.dao.interfaces;


import java.util.List;

import ci.inventory.entity.Stock_movement;

public interface IStock_movementDao {
	
	//Definition Basics operations
	Stock_movement create(Stock_movement stoct_movement);
	Stock_movement getById(int id);
	Stock_movement update(Stock_movement stoct_movement);
	int delete(int id);
	List<Stock_movement> getAll();
}
