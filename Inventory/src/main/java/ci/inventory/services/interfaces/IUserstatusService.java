package ci.inventory.services.interfaces;


import java.util.List;

import ci.inventory.entity.Userstatus;

public interface IUserstatusService {
	
	//Definition Basics operations
	Userstatus create(Userstatus userstatus);
	Userstatus get(int id);
	Userstatus update(Userstatus userstatus);
	int delete(int id);
	List<Userstatus> getAll();
}
