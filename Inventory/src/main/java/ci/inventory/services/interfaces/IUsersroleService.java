package ci.inventory.services.interfaces;


import java.util.List;

import ci.inventory.entity.Usersrole;

public interface IUsersroleService {
	
	//Definition Basics operations
	Usersrole create(Usersrole usersrole);
	Usersrole get(int id);
	Usersrole update(Usersrole usersrole);
	int delete(int id);
	List<Usersrole> getAll();
}
