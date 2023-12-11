package ci.inventory.dao.interfaces;


import java.util.List;

import ci.inventory.entity.Users;

public interface IUsersDao {
	
	//Definition Basics operations
	Users create(Users users);
	Users getById(int id);
	Users update(Users users);
	int delete(int id);
	List<Users> getAll();
	
	//More functional features
	Users connectUser(String login);
	Users connectUser(String login, String encryptPassword);
}
