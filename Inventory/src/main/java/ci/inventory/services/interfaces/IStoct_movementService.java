package ci.inventory.services.interfaces;


import java.util.List;

import ci.inventory.entity.Stock_movement;

public interface IStoct_movementService {
	
	//Definition Basics operations
	Stock_movement create(Stock_movement stoct_movement);
	Stock_movement get(int id);
	Stock_movement update(Stock_movement stoct_movement);
	int delete(int id);
	List<Stock_movement> getAll();
}
