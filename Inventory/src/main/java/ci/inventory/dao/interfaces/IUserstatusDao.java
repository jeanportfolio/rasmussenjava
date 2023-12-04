package ci.inventory.dao.interfaces;


import java.util.List;

import ci.inventory.entity.Userstatus;

public interface IUserstatusDao {
	
	//Definition Basics operations
	Userstatus create(Userstatus userstatus);
	Userstatus getById(int id);
	Userstatus update(Userstatus userstatus);
	int delete(int id);
	List<Userstatus> getAll();
}
