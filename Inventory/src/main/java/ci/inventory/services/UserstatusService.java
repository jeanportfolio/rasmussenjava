package ci.inventory.services;

import java.util.List;

import ci.inventory.dao.UserstatusDao;
import ci.inventory.entity.Userstatus;
import ci.inventory.services.interfaces.IUserstatusService;

public class UserstatusService implements IUserstatusService{

	//Properties
	private static UserstatusDao dao;
	static {
		dao = new UserstatusDao();
	}

	@Override
	public Userstatus create(Userstatus userstatus) {

		return dao.create(userstatus);
	}

	@Override
	public Userstatus get(int id) {

		return dao.getById(id);
	}

	@Override
	public Userstatus update(Userstatus userstatus) {

		return dao.update(userstatus);
	}

	@Override
	public int delete(int id) {

		return dao.delete(id);
	}

	@Override
	public List<Userstatus> getAll() {

		return dao.getAll();
	}
}
