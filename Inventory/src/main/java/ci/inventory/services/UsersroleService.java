package ci.inventory.services;

import java.util.List;

import ci.inventory.dao.UsersroleDao;
import ci.inventory.entity.Usersrole;
import ci.inventory.services.interfaces.IUsersroleService;

public class UsersroleService implements IUsersroleService{

	//Properties
	private static UsersroleDao dao;
	static {
		dao = new UsersroleDao();
	}

	@Override
	public Usersrole create(Usersrole usersrole) {

		return dao.create(usersrole);
	}

	@Override
	public Usersrole get(int id) {

		return dao.getById(id);
	}

	@Override
	public Usersrole update(Usersrole usersrole) {

		return dao.update(usersrole);
	}

	@Override
	public int delete(int id) {

		return dao.delete(id);
	}

	@Override
	public List<Usersrole> getAll() {

		return dao.getAll();
	}
}
