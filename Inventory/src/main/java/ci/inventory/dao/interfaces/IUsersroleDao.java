package ci.inventory.dao.interfaces;


import java.util.List;

import ci.inventory.entity.Usersrole;

public interface IUsersroleDao {
	
	//Definition Basics operations
	Usersrole create(Usersrole usersrole);
	Usersrole getById(int id);
	Usersrole update(Usersrole usersrole);
	int delete(int id);
	List<Usersrole> getAll();
}
