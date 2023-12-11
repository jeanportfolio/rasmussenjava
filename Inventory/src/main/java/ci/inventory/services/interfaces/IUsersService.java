package ci.inventory.services.interfaces;


import java.util.List;

import ci.inventory.entity.Users;

public interface IUsersService {
	
	//Definition Basics operations
	Users create(Users users);
	Users get(int id);
	Users update(Users users);
	int delete(int id);
	List<Users> getAll();

	Users connect(String login, String encryptPassword);
	Users connect(String login);
}
