package ci.inventory.dao.interfaces;


import java.util.List;

import ci.inventory.entity.Stoct_movement;

public interface IStoct_movementDao {
	
	//Definition Basics operations
	Stoct_movement create(Stoct_movement stoct_movement);
	Stoct_movement getById(int id);
	Stoct_movement update(Stoct_movement stoct_movement);
	int delete(int id);
	List<Stoct_movement> getAll();
}
