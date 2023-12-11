package ci.inventory.services;

import java.util.List;

import ci.inventory.dao.UsersDao;
import ci.inventory.entity.Users;
import ci.inventory.services.interfaces.IUsersService;

public class UsersService implements IUsersService{

	//Properties
	private static UsersDao dao;
	static {
		dao = new UsersDao();
	}

	@Override
	public Users create(Users users) {

		return dao.create(users);
	}

	@Override
	public Users get(int id) {

		return dao.getById(id);
	}

	@Override
	public Users update(Users users) {

		return dao.update(users);
	}

	@Override
	public int delete(int id) {

		return dao.delete(id);
	}

	@Override
	public List<Users> getAll() {

		return dao.getAll();
	}

	@Override
	public Users connect(String login) {

		return dao.connectUser(login);
	}
	@Override
	public Users connect(String login, String encryptPassword) {

		return dao.connectUser(login,encryptPassword);
	}
}
