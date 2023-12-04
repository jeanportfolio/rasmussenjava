package ci.inventory.services;

import java.util.List;

import ci.inventory.dao.Event_occurDao;
import ci.inventory.entity.Event_occur;
import ci.inventory.services.interfaces.IEvent_occurService;

public class Event_occurService implements IEvent_occurService{

	//Properties
	private static Event_occurDao dao;
	static {
		dao = new Event_occurDao();
	}

	@Override
	public Event_occur create(Event_occur event_occur) {

		return dao.create(event_occur);
	}

	@Override
	public Event_occur get(int id) {

		return dao.getById(id);
	}

	@Override
	public Event_occur update(Event_occur event_occur) {

		return dao.update(event_occur);
	}

	@Override
	public int delete(int id) {

		return dao.delete(id);
	}

	@Override
	public List<Event_occur> getAll() {

		return dao.getAll();
	}
}
